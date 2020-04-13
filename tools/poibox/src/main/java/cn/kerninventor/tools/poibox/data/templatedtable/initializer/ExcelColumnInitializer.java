package cn.kerninventor.tools.poibox.data.templatedtable.initializer;

import cn.kerninventor.tools.poibox.data.templatedtable.ExcelColumn;
import cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.DataValid;
import cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.array.dictionary.DictionaryInterpretor;
import cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.array.dictionary.DictionaryLibrary;
import cn.kerninventor.tools.poibox.data.utils.SupportedDataType;
import cn.kerninventor.tools.poibox.utils.ReflectUtil;
import org.apache.poi.ss.usermodel.CellStyle;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @author Kern
 * @date 2019/12/9 15:52
 */
public class ExcelColumnInitializer<T extends Object> implements Comparable<ExcelColumnInitializer> {
    //列字段
    private Field field;
    //
    private String fieldName;

    private String titleName;

    private int columnIndex;

    private int columnWidth;

    private String dataFormatEx;

    private boolean mergeByContent;

    private CellStyle columnStyle;

    private Annotation validAnnotation;

    private DictionaryInterpretor interpretor;

    private ExcelColumnInitializer(Field field, ExcelColumn excelColumn, int columnIndex, CellStyle columnStyle) {
        this.field = SupportedDataType.checkSupportability(field);
        this.fieldName = field.getName();
        this.titleName = excelColumn.value();
        this.columnIndex = columnIndex;
        this.columnWidth = excelColumn.columnWidth();
        this.dataFormatEx = "".equals(excelColumn.dataFormatEx().trim()) ? null : excelColumn.dataFormatEx().trim();
        this.mergeByContent = excelColumn.mergeByContent();
        this.columnStyle = columnStyle;
        this.validAnnotation = ReflectUtil.getFirstAnnotated(field, DataValid.class);
        this.interpretor = DictionaryLibrary.getInterpretor(this.validAnnotation);
    }

    static ExcelColumnInitializer newInstance(Field field, ExcelColumn excelColumn, int columnIndex, CellStyle columnStyle){
        return new ExcelColumnInitializer(field, excelColumn, columnIndex, columnStyle);
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
