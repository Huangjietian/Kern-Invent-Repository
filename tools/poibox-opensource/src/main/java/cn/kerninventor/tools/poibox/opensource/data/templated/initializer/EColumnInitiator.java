package cn.kerninventor.tools.poibox.opensource.data.templated.initializer;

import cn.kerninventor.tools.poibox.opensource.data.templated.ExcelColumn;
import cn.kerninventor.tools.poibox.opensource.data.templated.enums.SupportedDataType;
import cn.kerninventor.tools.poibox.opensource.data.templated.validation.DataValid;
import cn.kerninventor.tools.poibox.opensource.data.templated.validation.array.dictionary.DictionaryInterpretor;
import cn.kerninventor.tools.poibox.opensource.data.templated.validation.array.dictionary.DictionaryLibrary;
import cn.kerninventor.tools.poibox.opensource.data.templated.writer.col.ColWriter;
import cn.kerninventor.tools.poibox.opensource.exception.IllegalColumnConfigureException;
import cn.kerninventor.tools.poibox.opensource.utils.ReflectUtil;
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
    private CellStyle theadStyle;
    private CellStyle tbodyStyle;
    private Annotation validAnnotation;
    private DictionaryInterpretor interpretor;
    private ColWriter colWriter;

    public EColumnInitiator(Field field, ExcelColumn excelColumn, CellStyle theadStyle, CellStyle tbodyStyle) {
        this.field = field;
        this.fieldName = field.getName();
        this.titleName = excelColumn.value();
        this.columnWidth = excelColumn.columnWidth();
        this.dataFormatEx = excelColumn.dataFormatEx();
        this.formula = excelColumn.formula();
        this.columnSort = excelColumn.columnSort();
        this.theadStyle = theadStyle;
        this.tbodyStyle = tbodyStyle;
        this.validAnnotation = ReflectUtil.getFirstMarkedAnnotation(field, DataValid.class);
        //FIXME 重新实现翻译功能， 建议以外部添加的形式指定字段的翻译器。
        this.interpretor = DictionaryLibrary.getInterpretor(this.validAnnotation);
        try {
            this.colWriter = excelColumn.colWriter().getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new IllegalColumnConfigureException("ColWriter lack of parameterless constructors! field : " + fieldName);
        }
        //默认提供的GeneralColWriter 和 MergeAbleColWriter 仅支持部分数据类型，其他数据类型需指定实现了ColWriter接口的自定义实现。
        SupportedDataType.checkSupportability(field, colWriter);
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

    public ColWriter getColWriter() {
        return colWriter;
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
