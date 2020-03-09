package cn.kerninventor.tools.poibox.data.datatable;

import cn.kerninventor.tools.poibox.BoxGadget;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.DataValidHandler;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.ExcelValid;
import org.apache.poi.ss.usermodel.DataFormat;

import javax.swing.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Objects;

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

    private String dataFormatEx;

    private boolean mergeByContent;

    private Annotation validAnnotation;

    private ExcelcolumnDataAccepter() {
    }

    public static ExcelcolumnDataAccepter getInstance(Field field, ExcelColumn excelColumn, int columnIndex){
        ExcelcolumnDataAccepter accepter = new ExcelcolumnDataAccepter();
        accepter.field = field;
        accepter.fieldName = field.getName();
        accepter.titleName = excelColumn.value();
        accepter.columnIndex = columnIndex;
        accepter.columnWidth = excelColumn.columnWidth() == -1 ? -1 : BoxGadget.getExcelCellWidth(excelColumn.columnWidth());
        accepter.regEx = "".equals(excelColumn.regEx().trim()) ? null : excelColumn.regEx();
        accepter.dataFormatEx = "".equals(excelColumn.dataFormatEx().trim()) ? null : excelColumn.dataFormatEx();
        accepter.mergeByContent = excelColumn.mergeByContent();
        accepter.validAnnotation = DataValidHandler.findAnnotationForm(field);
        return accepter;
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

    public String getDataFormatEx() {
        return dataFormatEx;
    }

    public boolean isMergeByContent() {
        return mergeByContent;
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
