package cn.kerninventor.tools.poibox.data.datatable;

import cn.kerninventor.tools.poibox.style.TabulationStyle;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @Title: DataTable
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.data.datatable
 * @Author Kern
 * @Date 2019/12/9 15:25
 * @Description: TODO
 */
public class DataTabulation {

    /**
     * data tabulation class
     */
    private Class tabulationClass;
    /**
     * Default headline content, you can set value to this attribution in initialize.
     */
    private String headline;
    /**
     * Table start row index, default 0.
     */
    private int startRowIndex = 0;
    /**
     * The first body row index.
     */
    private int startTextRowIndex;
    /**
     * The number of lines in the body.
     */
    private int textRowNum = 20;
    /**
     * Default value is true, poibox adopt auto index in default.
     */
    private boolean autoColumnIndex = true;
    /**
     * NullAble, no styles are added to the cells when this attribution is null.
     */
    private TabulationStyle tabulationStyle;
    /**
     * NotNull
     */
    private List<DataColumn> columnsContainer;


    public int getFirstColumnIndex() {
        return columnsContainer.get(0).getColumnIndex();
    }

    public int getLastColumnIndex() {
        return columnsContainer.get(columnsContainer.size()-1).getColumnIndex();
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

    public int getStartTextRowIndex() {
        return startTextRowIndex;
    }

    public int getTextRowNum() {
        return textRowNum;
    }

    public boolean isAutoColumnIndex() {
        return autoColumnIndex;
    }

    public TabulationStyle getTabulationStyle() {
        return tabulationStyle;
    }

    public List<DataColumn> getColumnsContainer() {
        return columnsContainer;
    }

    /**
     * Annotation way
     * @param tableClass
     */
    public DataTabulation(Class tableClass) {
        ExcelTabulation excelTabulation;
        if ((excelTabulation = (ExcelTabulation) tableClass.getAnnotation(ExcelTabulation.class)) != null){
            tabulationClass = tableClass;
            headline = "".equals(excelTabulation.headline().trim()) ? null : excelTabulation.headline();
            startRowIndex = excelTabulation.startRowIndex();
            startTextRowIndex = headline == null ? startRowIndex + 1 : startRowIndex + 2;
            textRowNum = excelTabulation.textRowNum();
            autoColumnIndex = excelTabulation.autoColumnIndex();
            try {
                tabulationStyle = excelTabulation.style().newInstance();
            } catch (Exception e) {
                throw new IllegalArgumentException("An explicit, parameterless constructor is required in Tabulation.style()");
            }
            columnsContainer = resolveTable(tableClass);
        } else {
            throw new IllegalArgumentException("Data table object need to annotated @ExcelTabulation annotation!");
        }
    }

    private List<DataColumn> resolveTable(Class tableClass){
        List<DataColumn> dataColumns = new ArrayList();
        Field[] fields = tableClass.getDeclaredFields();
        ExcelColumn excelColumn;
        /**
         * auto index schema, the first column index is 0
         */
        if (autoColumnIndex){
            int index = 0 ;
            for (Field field : fields){
                if ((excelColumn = field.getDeclaredAnnotation(ExcelColumn.class)) != null
                        && excelColumn.value() != null
                        && !"".equals(excelColumn.value().trim())) {
                    DataColumn dataColumn = DataColumn.getInstance(
                            field,
                            field.getName(),
                            excelColumn.value(),
                            index++,
                            excelColumn.columnWidth(),
                            excelColumn.regEx(),
                            excelColumn.dateFormat()
                    );
                    dataColumns.add(dataColumn);
                }
            }
        } else {
            for (Field field : fields){
                if ((excelColumn = field.getDeclaredAnnotation(ExcelColumn.class)) != null
                        && excelColumn.value() != null
                        && !"".equals(excelColumn.value().trim())) {
                    DataColumn dataColumn = DataColumn.getInstance(
                            field,
                            field.getName(),
                            excelColumn.value(),
                            excelColumn.columnIndex(),
                            excelColumn.columnWidth(),
                            excelColumn.regEx(),
                            excelColumn.dateFormat()
                    );
                    dataColumns.add(dataColumn);
                }
            }
        }
        if (dataColumns.size() == 0){
            throw new IllegalArgumentException("Data table missing column definition, you should use @ExcelColumn to annotate object's fields!");
        }
        Collections.sort(dataColumns);
        return dataColumns;
    }



}
