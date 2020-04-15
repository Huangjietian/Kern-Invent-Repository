package cn.kerninventor.tools.poibox.data.templated.initializer;

import cn.kerninventor.tools.poibox.BoxBracket;
import cn.kerninventor.tools.poibox.POIBox;
import cn.kerninventor.tools.poibox.data.templated.ExcelBanner;
import cn.kerninventor.tools.poibox.data.templated.ExcelColumn;
import cn.kerninventor.tools.poibox.data.templated.ExcelTabulation;
import cn.kerninventor.tools.poibox.exception.IllegalSourceClassOfTabulationException;
import cn.kerninventor.tools.poibox.exception.IllegalTabulationConfigureException;
import cn.kerninventor.tools.poibox.style.Styler;
import cn.kerninventor.tools.poibox.utils.BeanUtil;
import org.apache.poi.ss.usermodel.CellStyle;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author Kern
 * @Date 2019/12/9 15:25
 * @Description: excel 数据表解析处理类
 */
public class ExcelTabulationInitializer<T> extends BoxBracket {

    private Class<T> tabulationClass;
    private CellStyle theadStyle;
    private CellStyle tbodyStyle;
    private float theadRowHeight;
    private float tbodyRowHeight;
    private int startRowIndex;
    private int theadRowIndex;
    private int tbodyFirstRowIndex;
    private int effectiveRows;
    private int minimumColumnsWidth;
//    private boolean autoColumnIndex;
    private List<ExcelBannerInitializer> bannerContainer;
    private List<ExcelColumnInitializer> columnsContainer;

    public Class<T> getTabulationClass() {
        return tabulationClass;
    }

    public CellStyle getTheadStyle() {
        return theadStyle;
    }

    public CellStyle getTbodyStyle() {
        return tbodyStyle;
    }

    public float getTheadRowHeight() {
        return theadRowHeight;
    }

    public float getTbodyRowHeight() {
        return tbodyRowHeight;
    }

    public int getStartRowIndex() {
        return startRowIndex;
    }

    public int getEffectiveRows() {
        return effectiveRows;
    }

    public int getMinimumColumnsWidth() {
        return minimumColumnsWidth;
    }

    public int getTheadRowIndex() {
        return theadRowIndex;
    }

    public int getTbodyFirstRowIndex(){
        return tbodyFirstRowIndex;
    }

    public List<ExcelBannerInitializer> getBannerContainer() {
        return bannerContainer;
    }

    public List<ExcelColumnInitializer> getColumnsContainer() {
        return columnsContainer;
    }

    public ExcelColumnInitializer getColumnInitializerByTitleName(final String fieldName) {
        return columnsContainer.stream().filter(e -> e.getFieldName().equals(fieldName)).findFirst().get();
    }

    public int getFirstColumnIndex() {
        return columnsContainer.get(0).getColumnIndex();
    }

    public int getLastColumnIndex() {
        return columnsContainer.get(columnsContainer.size() -1 ).getColumnIndex();
    }

    public ExcelTabulationInitializer(Class<T> tableClass, POIBox poiBox) {
        super(poiBox);
        ExcelTabulation excelTabulation = dataTabulationSourceClassValidate(tableClass);
        this.tabulationClass = tableClass;
        this.theadStyle = getParent().styler().generate(excelTabulation.theadStyle());
        this.tbodyStyle = getParent().styler().generate(excelTabulation.tbodyStyle());
        this.theadRowHeight = excelTabulation.theadRowHeight();
        this.tbodyRowHeight = excelTabulation.tbodyRowHeight();
        this.startRowIndex = startRowIndexNotMinus(excelTabulation.startRowIndex());
        this.effectiveRows = effectiveRowsNotLessThan1(excelTabulation.effectiveRows());
        this.minimumColumnsWidth = excelTabulation.minimumColumnsWidth();
        this.bannerContainer = initialzeBanners(excelTabulation.banners());
        this.columnsContainer = columnsTitleNameCannotRepeat(initialzeColumns(tableClass.getDeclaredFields(), getTbodyStyle()));
        this.theadRowIndex = getRowIndexIncrementsByBanners(bannerContainer) + startRowIndex;
        this.tbodyFirstRowIndex = theadRowIndex + 1;
    }

    private List<ExcelColumnInitializer> columnsTitleNameCannotRepeat(List<ExcelColumnInitializer> columnsContainer) {
        int size = columnsContainer.stream().map(e -> e.getTitleName().trim()).collect(Collectors.toSet()).size();
        if (size != columnsContainer.size()) {
            throw new IllegalTabulationConfigureException("Column title name must be unique!");
        }
        return columnsContainer;
    }

    private int startRowIndexNotMinus(int startRowIndex) {
        if (startRowIndex < 0)
            throw new IllegalTabulationConfigureException("StartRowIndex must be positive integer!");
        return startRowIndex;
    }

    private int effectiveRowsNotLessThan1(int effectiveRows) {
        if (effectiveRows < 1)
            throw new IllegalTabulationConfigureException("EffectiveRows must be great than 1!");
        return effectiveRows;
    }

    private List<ExcelBannerInitializer> initialzeBanners(ExcelBanner... banners) {
        return Arrays.stream(banners).map(e -> ExcelBannerInitializer.newInstance(this, e)).collect(Collectors.toList());
    }

    private int getRowIndexIncrementsByBanners(List<ExcelBannerInitializer> bannerContainer) {
        if (BeanUtil.isEmpty(bannerContainer)) {
            return 0;
        }
        int incremental = 1;
        for (ExcelBannerInitializer bannerInitializer : bannerContainer) {
            if (bannerInitializer.getLastRowIndex() >= incremental) {
                incremental = bannerInitializer.getLastRowIndex() + 1;
            }
        }
        return incremental;
    }

    private List<ExcelColumnInitializer> initialzeColumns(Field[] fields, CellStyle tbodyStyle){
        Styler styler = getParent().styler();
        List<ExcelColumnInitializer> columnsContainer = new ArrayList(fields.length);
        ExcelColumn excelColumn;
        for (Field field : fields){
            if ((excelColumn = field.getDeclaredAnnotation(ExcelColumn.class)) != null) {
                CellStyle columnStyle = excelColumn.styleEffictive() ? styler.generate(excelColumn.columnStyle()) : tbodyStyle;
                columnsContainer.add(ExcelColumnInitializer.newInstance(field, excelColumn, columnStyle));
            }
        }
        if (columnsContainer.size() == 0){
            throw new IllegalSourceClassOfTabulationException("Data table lack column definition, you should use @ExcelColumn to annotate object's fields!");
        }
        Collections.sort(columnsContainer);
        return reIndex(columnsContainer);
    }

    public List<ExcelColumnInitializer> reIndex(List<ExcelColumnInitializer> columnsContainer) {
        for (int i = 0 ; i < columnsContainer.size() ; i ++) {
            columnsContainer.get(i).setColumnIndex(i);
        }
        return columnsContainer;
    }

    public static ExcelTabulation dataTabulationSourceClassValidate(Class tabulationClass) throws IllegalSourceClassOfTabulationException {
        ExcelTabulation excelTabulation;
        if ((excelTabulation = (ExcelTabulation) tabulationClass.getAnnotation(ExcelTabulation.class)) == null){
            throw new IllegalSourceClassOfTabulationException("Data tabulation POJO need to annotated @ExcelTabulation annotation!");
        }
        return excelTabulation;
    }

}
