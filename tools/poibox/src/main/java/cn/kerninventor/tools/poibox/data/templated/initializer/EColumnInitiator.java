package cn.kerninventor.tools.poibox.data.templated.initializer;

import cn.kerninventor.tools.poibox.data.templated.ExcelColumn;
import cn.kerninventor.tools.poibox.data.templated.enums.SupportedDataType;
import cn.kerninventor.tools.poibox.data.templated.validation.DataValid;
import cn.kerninventor.tools.poibox.data.templated.validation.array.dictionary.DictionaryInterpretor;
import cn.kerninventor.tools.poibox.data.templated.validation.array.dictionary.DictionaryLibrary;
import cn.kerninventor.tools.poibox.utils.ReflectUtil;
import org.apache.poi.ss.usermodel.CellStyle;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @author Kern
 * @date 2019/12/9 15:52
 */
public class EColumnInitiator<T extends Object> implements Comparable<EColumnInitiator>{

    private Field field;
    private String fieldName;
    private String titleName;
    private int columnIndex;
    private int columnSort;
    private int columnWidth;
    private String dataFormatEx;
    private String formula;
    private boolean mergeByContent;
    private CellStyle theadStyle;
    private CellStyle tbodyStyle;
    private Annotation validAnnotation;
    private DictionaryInterpretor interpretor;

    public EColumnInitiator(Field field, ExcelColumn excelColumn, CellStyle theadStyle, CellStyle tbodyStyle) {
        this.field = SupportedDataType.checkSupportability(field);
        this.fieldName = field.getName();
        this.titleName = excelColumn.value();
        this.columnWidth = excelColumn.columnWidth();
        this.dataFormatEx = excelColumn.dataFormatEx();
        this.formula = excelColumn.formula();
        this.mergeByContent = excelColumn.mergeByContent();
        this.columnSort = excelColumn.columnSort();
        this.theadStyle = theadStyle;
        this.tbodyStyle = tbodyStyle;
        this.validAnnotation = ReflectUtil.getFirstAnnotated(field, DataValid.class);
        this.interpretor = DictionaryLibrary.getInterpretor(this.validAnnotation);
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
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

    public String getFormula() {
        return formula;
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

    public CellStyle getTheadStyle() {
        return theadStyle;
    }

    public CellStyle getTbodyStyle() {
        return tbodyStyle;
    }

    @Override
    public String toString() {
        return titleName;
    }

    @Override
    public int compareTo(EColumnInitiator o) {
        if (this.columnSort > o.columnSort) {
            return 1;
        } else if (this.columnSort == o.columnSort) {
            return 0;
        } else {
            return -1;
        }
    }
}
