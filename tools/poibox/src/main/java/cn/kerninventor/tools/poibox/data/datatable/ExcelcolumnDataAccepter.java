package cn.kerninventor.tools.poibox.data.datatable;

import cn.kerninventor.tools.poibox.POIGadget;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;

/**
 * @Title: DataColumn
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.data.datatable
 * @Author Kern
 * @Date 2019/12/9 15:52
 */
public class ExcelcolumnDataAccepter implements Comparable<ExcelcolumnDataAccepter> {

    private Field field;

    private String fieldName;

    private String titleName;

    private int columnIndex;

    private int columnWidth;

    private String regEx;

    private String dateFormat;

    private SimpleDateFormat simpleDateFormat;

    private Annotation validAnnotation;

    private ExcelcolumnDataAccepter() {
    }

    public static ExcelcolumnDataAccepter getInstance(Field field, String fieldName, String titleName, int columnIndex, int columnWidth, String regEx, String dateFormat, Annotation annotation) {
        ExcelcolumnDataAccepter excelcolumnDataAccepter = new ExcelcolumnDataAccepter();
        excelcolumnDataAccepter.field = field;
        excelcolumnDataAccepter.fieldName = fieldName;
        excelcolumnDataAccepter.titleName = titleName;
        excelcolumnDataAccepter.columnIndex = columnIndex;
        excelcolumnDataAccepter.columnWidth = columnWidth == -1 ? -1 : POIGadget.getExcelCellWidth(columnWidth);
        excelcolumnDataAccepter.regEx = "".equals(regEx.trim()) ? null : regEx;
        excelcolumnDataAccepter.dateFormat = dateFormat;
        excelcolumnDataAccepter.simpleDateFormat = new SimpleDateFormat(dateFormat);
        excelcolumnDataAccepter.validAnnotation = annotation;
        return excelcolumnDataAccepter;
    }

    public Field getField() {
        return field;
    }

    public Class getFieldType() {
        return field.getType();
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

    public String getRegEx() {
        return regEx;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public SimpleDateFormat getSimpleDateFormat() {
        return simpleDateFormat;
    }

    public Annotation getValidAnnotation() {
        return validAnnotation;
    }

    @Override
    public int compareTo(ExcelcolumnDataAccepter o) {
        if (columnIndex > o.columnIndex){
            return 1;
        } else if (columnIndex < o.columnIndex){
            return -1;
        } else {
            throw new IllegalArgumentException("Column definition duplicate index! Field: " + o.getFieldName());
        }
    }

}
