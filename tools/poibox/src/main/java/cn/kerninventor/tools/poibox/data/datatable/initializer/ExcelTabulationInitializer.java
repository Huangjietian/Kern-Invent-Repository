package cn.kerninventor.tools.poibox.data.datatable.initializer;

import cn.kerninventor.tools.poibox.data.datatable.ExcelColumn;
import cn.kerninventor.tools.poibox.data.datatable.ExcelTabulation;
import cn.kerninventor.tools.poibox.data.exception.IllegalSourceClassOfTabulationException;
import cn.kerninventor.tools.poibox.data.utils.CellValueUtil;
import cn.kerninventor.tools.poibox.style.TabulationStyle;
import cn.kerninventor.tools.poibox.utils.ReflectUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

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
     * The number of lines in the body.
     */
    private int textRowNum;
    /**
     * Default value is true, poibox adopt auto index in default.
     */
    private boolean autoColumnIndex;
    /**
     * NullAble, no styles are added to the cells when this attribution is null.
     */
    private TabulationStyle tabulationStyle;
    /**
     * NotNull
     */
    private List<ExcelColumnInitializer> columnsContainer;


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

    public TabulationStyle getTabulationStyle() {
        return tabulationStyle;
    }

    public List<ExcelColumnInitializer> getColumnsContainer() {
        return columnsContainer;
    }

    /**
     * Annotation way
     * @param tableClass
     */
    public ExcelTabulationInitializer(Class<T> tableClass) {
        dataTabulationSourceClassValidate(tableClass);
        ExcelTabulation excelTabulation = tableClass.getAnnotation(ExcelTabulation.class);
        tabulationClass = tableClass;

        //row index calc and headline set value.
        headline = "".equals(excelTabulation.headline().trim()) ? null : excelTabulation.headline();
        startRowIndex = excelTabulation.startRowIndex();
        headlineRdx = headline == null ? startRowIndex - 1 : startRowIndex;
        tableHeadRdx = headlineRdx + 1;
        tableTextRdx = tableHeadRdx + 1;
        textRowNum = excelTabulation.textRowNum();

        autoColumnIndex = excelTabulation.autoColumnIndex();
        try {
            tabulationStyle = excelTabulation.style().newInstance();
        } catch (Exception e) {
            throw new IllegalSourceClassOfTabulationException("An explicit, parameterless constructor is required in Tabulation.style()");
        }
        initialzeColumns(tableClass.getDeclaredFields());
    }

    private void initialzeColumns(Field[] fields){
        columnsContainer = new ArrayList(fields.length);
        ExcelColumn excelColumn;
        if (autoColumnIndex){
            int index = 0 ;
            for (Field field : fields){
                if ((excelColumn = field.getDeclaredAnnotation(ExcelColumn.class)) != null) {
                    columnsContainer.add(ExcelColumnInitializer.getInstance(field, excelColumn, index++));
                }
            }
        } else {
            for (Field field : fields){
                if ((excelColumn = field.getDeclaredAnnotation(ExcelColumn.class)) != null) {
                    columnsContainer.add(ExcelColumnInitializer.getInstance(field, excelColumn,excelColumn.columnIndex()));
                }
            }
        }
        if (columnsContainer.size() == 0){
            throw new IllegalSourceClassOfTabulationException("Data table lack column definition, you should use @ExcelColumn to annotate object's fields!");
        }
        Collections.sort(columnsContainer);
    }

    /**
     * TODO 数据校验
     * @param sheet
     * @return
     */
    public List<T> readFrom(Sheet sheet) {
        List<T> list = new ArrayList();
        for (int i = this.getTableTextRdx(); i <= sheet.getLastRowNum() ; i ++) {
            T t = null;
            try {
                t = (T) ReflectUtil.newInstance(this.getTabulationClass());
            } catch (Exception e) {
                throw new IllegalSourceClassOfTabulationException("The tabulation Class Missing parameterless constructor! Class: " + this.getTabulationClass());
            }
            Row row = sheet.getRow(i);
            if (row == null){
                continue;
            }
            int nullCount = 0;
            for (ExcelColumnInitializer column : this.getColumnsContainer()){
                Cell cell = row.getCell(column.getColumnIndex());
                if (cell == null){
                    nullCount++;
                    continue;
                }
                Object value = null;
                //翻译
                if (column.getInterpretor().isInterpretable()) {
                    value = column.getInterpretor().getMetaDataFrom(cell);
                } else {
                    value = CellValueUtil.getCellValueBySpecifiedType(cell, column.getFieldType());
                }
                if (null == value) {
                    nullCount++;
                }
                try {
                    ReflectUtil.setFieldValue(column.getField(), t, value);
                } catch (IllegalAccessException e) {
                    throw new IllegalArgumentException("Set value to Field error! Field: " + column.getFieldName());
                }
            }
            if (nullCount < this.getColumnsContainer().size()){
                list.add(t);
            }
        }
        return list;
    }


    public static void dataTabulationSourceClassValidate(Class tabulationClass) throws IllegalSourceClassOfTabulationException {
        if (tabulationClass.getAnnotation(ExcelTabulation.class) == null){
            throw new IllegalSourceClassOfTabulationException("Data tabulation POJO need to annotated @ExcelTabulation annotation!");
        }
    }

}
