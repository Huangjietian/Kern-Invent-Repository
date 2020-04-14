package cn.kerninventor.tools.poibox.data.templated.initializer;

import cn.kerninventor.tools.poibox.BoxBracket;
import cn.kerninventor.tools.poibox.POIBox;
import cn.kerninventor.tools.poibox.exception.IllegalSourceClassOfTabulationException;
import cn.kerninventor.tools.poibox.data.templated.ExcelBanner;
import cn.kerninventor.tools.poibox.data.templated.ExcelColumn;
import cn.kerninventor.tools.poibox.data.templated.ExcelTabulation;
import cn.kerninventor.tools.poibox.style.Styler;
import org.apache.poi.ss.usermodel.CellStyle;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author Kern
 * @Date 2019/12/9 15:25
 * @Description: excel 数据表解析处理类
 */
public class ExcelTabulationInitializer<T> extends BoxBracket {
    //表格定义类class
    private Class<T> tabulationClass;
    //表头风格
    private CellStyle theadStyle;
    //表体风格
    private CellStyle tbodyStyle;
    //起始行
    private int startRowIndex;
    //表头行坐标
    private int theadRowIndex;
    //表体行起始最表
    private int tbodyFirstRowIndex;
    //适用行数
    private int effectiveRows;
    //横幅集
    private List<ExcelBannerInitializer> bannerContainer;
    //列定义集
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

    public ExcelColumnInitializer getColumnInitializerByTitleName(String fieldName) {
        for (ExcelColumnInitializer columnInitializer : columnsContainer) {
            if (columnInitializer.getFieldName().equals(fieldName)) {
                return columnInitializer;
            }
        }
        return null;
    }

    public int getFirstColumnIndex() {
        return columnsContainer.get(0).getColumnIndex();
    }

    public int getLastColumnIndex() {
        return columnsContainer.get(columnsContainer.size() -1 ).getColumnIndex();
    }

    public ExcelTabulationInitializer(Class<T> tableClass, POIBox poiBox) {
        super(poiBox);
        this.tabulationClass = tableClass;
        ExcelTabulation excelTabulation = dataTabulationSourceClassValidate(tableClass);
        //初始化表格风格
        this.theadStyle = getParent().styler().generate(excelTabulation.theadStyle());
        this.tbodyStyle = getParent().styler().generate(excelTabulation.tbodyStyle());
        //初始化大标题
        this.bannerContainer = initialzeBanners(excelTabulation.banners());
        //初始化列
        this.columnsContainer = initialzeColumns(tableClass.getDeclaredFields(), excelTabulation.autoColumnIndex(), getTbodyStyle());
        //设置行列坐标信息
        this.startRowIndex = excelTabulation.startRowIndex();
        this.effectiveRows = excelTabulation.effectiveRows();
        this.theadRowIndex = getRowIndexIncrementsByBanners(this.bannerContainer) + startRowIndex;
        this.tbodyFirstRowIndex = theadRowIndex + 1;
    }

    private List<ExcelBannerInitializer> initialzeBanners(ExcelBanner... banners) {
        List<ExcelBannerInitializer> bannerContainer = new ArrayList<>(banners.length);
        for (ExcelBanner banner : banners) {
            ExcelBannerInitializer bannerInitializer = ExcelBannerInitializer.newInstance(this, banner);
            bannerContainer.add(bannerInitializer);

        }
        return bannerContainer;
    }

    private int getRowIndexIncrementsByBanners(List<ExcelBannerInitializer> bannerContainer) {
        int inherentIncrement = 1;
        int max = 0;
        for (ExcelBannerInitializer bannerInitializer : bannerContainer) {
            if (bannerInitializer.getLastRowIndex() > max) {
                max = bannerInitializer.getLastRowIndex();
            }
        }
        return max + inherentIncrement;
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
        Collections.sort(columnsContainer);
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
