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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        this.bannerContainer = initialzeBanners(excelTabulation.banners());
        this.columnsContainer = initialzeColumns(tableClass.getDeclaredFields(), excelTabulation.autoColumnIndex(), getTbodyStyle());
        this.theadRowIndex = getRowIndexIncrementsByBanners(this.bannerContainer) + startRowIndex;
        this.tbodyFirstRowIndex = theadRowIndex + 1;
    }

    private int startRowIndexNotMinus(int startRowIndex) {
        if (startRowIndex < 0)
            throw new IllegalTabulationConfigureException("startRowIndex must be positive integer!");
        return startRowIndex;
    }

    private int effectiveRowsNotLessThan1(int effectiveRows) {
        if (effectiveRows < 1)
            throw new IllegalTabulationConfigureException("effectiveRows must be great than 1!");
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

    private List<ExcelColumnInitializer> initialzeColumns(Field[] fields, boolean autoColumnIndex, CellStyle tbodyStyle){
        Styler styler = getParent().styler();
        List<ExcelColumnInitializer> columnsContainer = new ArrayList(fields.length);
        ExcelColumn excelColumn;
        if (autoColumnIndex){
            int index = 0 ;
            for (Field field : fields){
                if ((excelColumn = field.getDeclaredAnnotation(ExcelColumn.class)) != null) {
                    CellStyle columnStyle = excelColumn.styleEffictive() ? styler.generate(excelColumn.columnStyle()) : styler.copyStyle(tbodyStyle) ;
                    columnsContainer.add(ExcelColumnInitializer.newInstance(field, excelColumn, index++, columnStyle));
                }
            }
        } else {
            for (Field field : fields){
                if ((excelColumn = field.getDeclaredAnnotation(ExcelColumn.class)) != null) {
                    CellStyle columnStyle = excelColumn.styleEffictive() ? styler.generate(excelColumn.columnStyle()) : styler.copyStyle(tbodyStyle) ;
                    columnsContainer.add(ExcelColumnInitializer.newInstance(field, excelColumn, excelColumn.columnIndex(), columnStyle));
                }
            }
        }
        if (columnsContainer.size() == 0){
            throw new IllegalSourceClassOfTabulationException("Data table lack column definition, you should use @ExcelColumn to annotate object's fields!");
        }

        columnsContainer.stream().sorted((o1, o2) -> {
            if (o1.getColumnIndex() > o2.getColumnIndex()) {
                return 1;
            } else if (o1.getColumnIndex() == o2.getColumnIndex()) {
                return 0;
            } else {
                return -1;
            }
        });
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
