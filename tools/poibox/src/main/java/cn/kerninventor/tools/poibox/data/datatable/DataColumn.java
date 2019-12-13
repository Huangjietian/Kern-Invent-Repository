package cn.kerninventor.tools.poibox.data.datatable;

import java.lang.reflect.Field;

/**
 * @Title: DataColumn
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.data.datatable
 * @Author Kern
 * @Date 2019/12/9 15:52
 */
public class DataColumn implements Comparable<DataColumn> {

    private Field field;

    private String fieldName;

    private String titleName;

    private int columnIndex;

    private int columnWidth;

    private DataColumn() {
    }

    public static DataColumn getInstance(Field field, String fieldName, String titleName, int columnIndex, int columnWidth) {
        DataColumn dataColumn = new DataColumn();
        dataColumn.field = field;
        dataColumn.fieldName = fieldName;
        dataColumn.titleName = titleName;
        dataColumn.columnIndex = columnIndex;
        dataColumn.columnWidth = columnWidth;
        return dataColumn;
    }

    public Field getField() {
        return field;
    }

    public Class getFieldClass() {
        return field.getClass();
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getTitleName() {
        return titleName;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public int getColumnWidth() {
        return columnWidth;
    }

    @Override
    public int compareTo(DataColumn o) {
        if (columnIndex > o.columnIndex){
            return 1;
        } else if (columnIndex < o.columnIndex){
            return -1;
        } else {
            throw new IllegalArgumentException("duplicate index!");
        }
    }

}
