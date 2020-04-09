package cn.kerninventor.tools.poibox.data.templatedtable.initializer;

import cn.kerninventor.tools.poibox.BoxGadget;
import cn.kerninventor.tools.poibox.data.templatedtable.ExcelColumn;
import cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.DataValid;
import cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.array.dictionary.DictionaryInterpretor;
import cn.kerninventor.tools.poibox.data.utils.SupportedDataType;
import cn.kerninventor.tools.poibox.developer.ReadyToDevelop;
import cn.kerninventor.tools.poibox.utils.ReflectUtil;
import org.apache.poi.ss.usermodel.CellStyle;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @author Kern
 * @date 2019/12/9 15:52
 */
public class ExcelColumnInitializer<T extends Object> implements Comparable<ExcelColumnInitializer> {

    private Field field;

    private Class<T> fieldType;

    private String fieldName;

    private String titleName;

    private int columnIndex;

    private int columnWidth;

    private String dataFormatEx;

    private boolean mergeByContent;

    private Annotation validAnnotation;

    private DictionaryInterpretor interpretor;

    private CellStyle columnStyle;

    @ReadyToDevelop("删减列")
    private boolean removable;

    private ExcelColumnInitializer(Class<T> fieldType) {
        this.fieldType = fieldType;
    }

    static ExcelColumnInitializer getInstance(Field field, ExcelColumn excelColumn, int columnIndex, CellStyle columnStyle){
        ExcelColumnInitializer column = new ExcelColumnInitializer(field.getType());
        column.field = SupportedDataType.checkSupportability(field);
        column.fieldName = field.getName();
        column.titleName = excelColumn.value();
        column.columnIndex = columnIndex;
        column.columnWidth = excelColumn.columnWidth() == -1 ? -1 : BoxGadget.adjustCellWidth(excelColumn.columnWidth());
        column.dataFormatEx = "".equals(excelColumn.dataFormatEx().trim()) ? null : excelColumn.dataFormatEx();
        column.mergeByContent = excelColumn.mergeByContent();
        column.validAnnotation = ReflectUtil.getFirstAnnotated(field, DataValid.class);
        column.interpretor = DictionaryInterpretor.newInstance(column.validAnnotation);
        column.columnStyle = columnStyle;
        return column;
    }

    public void setColumnIndex(int columnIndex){
        this.columnIndex = columnIndex;
    }

    public void remove(){
        this.removable = true;
    }

    public Field getField() {
        return field;
    }

    public Class getFieldType() {
        return fieldType;
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

    public String getDataFormatEx() {
        return dataFormatEx;
    }

    public boolean isMergeByContent() {
        return mergeByContent;
    }

    public Annotation getValidAnnotation() {
        return validAnnotation;
    }

    public DictionaryInterpretor getInterpretor() {
        return interpretor;
    }

    public CellStyle getColumnStyle() {
        return columnStyle;
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

    @Override
    public String toString() {
        return titleName;
    }
}
