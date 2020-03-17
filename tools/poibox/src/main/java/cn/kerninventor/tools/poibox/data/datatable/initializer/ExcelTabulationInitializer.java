package cn.kerninventor.tools.poibox.data.datatable.initializer;

import cn.kerninventor.tools.poibox.POIBox;
import cn.kerninventor.tools.poibox.data.datatable.ExcelColumn;
import cn.kerninventor.tools.poibox.data.datatable.ExcelTabulation;
import cn.kerninventor.tools.poibox.data.exception.IllegalSourceClassOfTabulationException;
import cn.kerninventor.tools.poibox.data.datatable.cellstyle.TabulationStyle;
import cn.kerninventor.tools.poibox.style.Styler;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Title: DataTable
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.data.datatable
 * @Author Kern
 * @Date 2019/12/9 15:25
 * @Description: excel 数据表解析处理类
 * TODO 对象层级，  Tabulator { DataProcessor：解析注解注释的表格类， 持有该表格初始化的所有对象 }
 */
public class ExcelTabulationInitializer<T> {

    /**
     *
     */
    private POIBox parent;


    /**
     * data tabulation class
     */
    private Class<T> tabulationClass;


    /**
     * Default headline content, you can set value to this attribution in initialize.
     */
    private String headline;


    /**
     * Table start row index, default 0.
     */
    private int startRowIndex;


    /**
     * headline / table head/ table text row index.
     */
    private int headlineRdx, tableHeadRdx, tableTextRdx;


    /**
     * styles
     */
    private CellStyle headlineStyle, tableHeadStyle, tableTextStyle;


    /**
     * The number of lines in the body.
     */
    private int textRowNum;


    /**
     * Default value is true, poibox adopt auto index in default.
     */
    private boolean autoColumnIndex;


    /**
     * NotNull columns definition
     */
    private List<ExcelColumnInitializer> columnsContainer;





    public POIBox getParent() {
        return parent;
    }

    public int getFirstColumnIndex() {
        return columnsContainer.get(0).getColumnIndex();
    }

    public int getLastColumnIndex() {
        return columnsContainer.get(columnsContainer.size() -1 ).getColumnIndex();
    }

    public int getColumnSize() {
        return columnsContainer.size();
    }

    public Class getTabulationClass() {
        return tabulationClass;
    }

    public String getHeadline() {
        return headline;
    }

    public int getStartRowIndex() {
        return startRowIndex;
    }

    public int getHeadlineRdx() {
        return headlineRdx;
    }

    public void setHeadlineRdx(int headlineRdx) {
        this.headlineRdx = headlineRdx;
    }

    public int getTableHeadRdx() {
        return tableHeadRdx;
    }

    public void setTableHeadRdx(int tableHeadRdx) {
        this.tableHeadRdx = tableHeadRdx;
    }

    public int getTableTextRdx() {
        return tableTextRdx;
    }

    public void setTableTextRdx(int tableTextRdx) {
        this.tableTextRdx = tableTextRdx;
    }

    public int getTextRowNum() {
        return textRowNum;
    }

    public void setTextRowNum(int textRowNum) {
        this.textRowNum = textRowNum;
    }

    public boolean isAutoColumnIndex() {
        return autoColumnIndex;
    }

    public CellStyle getHeadlineStyle() {
        return headlineStyle;
    }

    public CellStyle getTableHeadStyle() {
        return tableHeadStyle;
    }

    public CellStyle getTableTextStyle() {
        return tableTextStyle;
    }

    public List<ExcelColumnInitializer> getColumnsContainer() {
        return columnsContainer;
    }

    /**
     * Annotation way
     * @param tableClass
     */
    public ExcelTabulationInitializer(Class<T> tableClass, POIBox poiBox) {
        parent = poiBox;
        /**
         * resolve table class.
         */
        dataTabulationSourceClassValidate(tableClass);
        ExcelTabulation excelTabulation = tableClass.getAnnotation(ExcelTabulation.class);
        tabulationClass = tableClass;
        //row index calc and headline set value.
        headline = "".equals(excelTabulation.headline().trim()) ? null : excelTabulation.headline();
        startRowIndex = excelTabulation.startRowIndex();
        //rdx init
        headlineRdx = headline == null ? startRowIndex - 1 : startRowIndex;
        tableHeadRdx = headlineRdx + 1;
        tableTextRdx = headlineRdx + 2;
        textRowNum = excelTabulation.textRowNum();
        autoColumnIndex = excelTabulation.autoColumnIndex();
        //style init
        TabulationStyle tabulationStyle = TabulationStyle.newInstance(excelTabulation.style());
        Workbook workbook = poiBox.workbook();
        headlineStyle = Styler.cloneStyle(workbook, tabulationStyle.getHeadLineStyle());
        tableHeadStyle = Styler.cloneStyle(workbook, tabulationStyle.getTableHeadStyle());
        tableTextStyle = Styler.cloneStyle(workbook, tabulationStyle.getTextStyle());
        /**
         * resolve columns
         */
        initialzeColumns(tableClass.getDeclaredFields());
        if (columnsContainer.size() == 0){
            throw new IllegalSourceClassOfTabulationException("Data table lack column definition, you should use @ExcelColumn to annotate object's fields!");
        }
    }

    private void initialzeColumns(Field[] fields){
        columnsContainer = new ArrayList(fields.length);
        ExcelColumn excelColumn;
        if (autoColumnIndex){
            int index = 0 ;
            for (Field field : fields){
                if ((excelColumn = field.getDeclaredAnnotation(ExcelColumn.class)) != null) {
                    columnsContainer.add(ExcelColumnInitializer.getInstance(field, excelColumn, index++, Styler.cloneStyle(parent.workbook(), tableTextStyle)));
                }
            }
        } else {
            for (Field field : fields){
                if ((excelColumn = field.getDeclaredAnnotation(ExcelColumn.class)) != null) {
                    columnsContainer.add(ExcelColumnInitializer.getInstance(field, excelColumn, excelColumn.columnIndex(), Styler.cloneStyle(parent.workbook(), tableTextStyle)));
                }
            }
        }
        Collections.sort(columnsContainer);
    }

    public static void dataTabulationSourceClassValidate(Class tabulationClass) throws IllegalSourceClassOfTabulationException {
        if (tabulationClass.getAnnotation(ExcelTabulation.class) == null){
            throw new IllegalSourceClassOfTabulationException("Data tabulation POJO need to annotated @ExcelTabulation annotation!");
        }
    }

}
