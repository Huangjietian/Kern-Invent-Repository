package cn.kerninventor.tools.poibox.data.datatable.initializer;

import cn.kerninventor.tools.poibox.BoxGadget;
import cn.kerninventor.tools.poibox.data.datatable.ExcelColumn;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.DataValidBuilder;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.array.dictionary.DictionaryValueInterpretor;
import cn.kerninventor.tools.poibox.data.utils.SupportedType;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @Title: DataColumn
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.data.datatable
 * @Author Kern
 * @Date 2019/12/9 15:52
 */
public class ExcelColumnInitializer implements Comparable<ExcelColumnInitializer> {

    private Field field;

    private String fieldName;

    private String titleName;

    private int columnIndex;

    private int columnWidth;

    private String regEx;

    private String dataFormatEx;

    private boolean mergeByContent;

    private Annotation validAnnotation;

    private DictionaryValueInterpretor interpretor;

    private ExcelColumnInitializer() {
    }

    static ExcelColumnInitializer getInstance(Field field, ExcelColumn excelColumn, int columnIndex){
        ExcelColumnInitializer column = new ExcelColumnInitializer();
        column.field = SupportedType.checkSupportability(field);
        column.fieldName = field.getName();
        column.titleName = excelColumn.value();
        column.columnIndex = columnIndex;
        column.columnWidth = excelColumn.columnWidth() == -1 ? -1 : BoxGadget.getExcelCellWidth(excelColumn.columnWidth());
        column.regEx = "".equals(excelColumn.regEx().trim()) ? null : excelColumn.regEx();
        column.dataFormatEx = "".equals(excelColumn.dataFormatEx().trim()) ? null : excelColumn.dataFormatEx();
        column.mergeByContent = excelColumn.mergeByContent();
        column.validAnnotation = DataValidBuilder.findAnnotationForm(field);
        column.interpretor = DictionaryValueInterpretor.newInstance(column.validAnnotation);
        return column;
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

    public DictionaryValueInterpretor getInterpretor() {
        return interpretor;
    }

    @Override
    public int compareTo(ExcelColumnInitializer o) {
        if (columnIndex > o.columnIndex){
            return 1;
        } else if (columnIndex < o.columnIndex){
            return -1;
        } else {
            throw new IllegalArgumentException("Column definition duplicate index! Field: " + o.getFieldName());
        }
    }

}
