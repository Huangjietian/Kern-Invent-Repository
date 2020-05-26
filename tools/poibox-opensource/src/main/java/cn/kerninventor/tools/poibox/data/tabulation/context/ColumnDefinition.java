package cn.kerninventor.tools.poibox.data.tabulation.context;

import cn.kerninventor.tools.poibox.data.tabulation.ExcelColumn;
import cn.kerninventor.tools.poibox.data.tabulation.enums.SupportedDataType;
import cn.kerninventor.tools.poibox.data.tabulation.translator.ColumnDataTranslate;
import cn.kerninventor.tools.poibox.data.tabulation.validation.DataValidationBuilder;
import cn.kerninventor.tools.poibox.exception.IllegalColumnConfigureException;
import cn.kerninventor.tools.poibox.data.tabulation.validation.DataValid;
import cn.kerninventor.tools.poibox.data.tabulation.validation.DataValidationBuilderFactory;
import cn.kerninventor.tools.poibox.data.tabulation.writer.tbody.col.ColWriter;
import cn.kerninventor.tools.poibox.utils.ReflectUtil;
import org.apache.poi.ss.usermodel.CellStyle;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @author Kern
 * @date 2019/12/9 15:52
 */
public class ColumnDefinition<T extends Object> implements Comparable<ColumnDefinition>, ColumnDefinitionModifier {

    private TableContext<T> tableContext;
    private Field field;
    private String fieldName;
    private String titleName;
    private volatile int columnIndex;
    private int columnSort;
    private int columnWidth;
    private String dataFormatEx;
    private String formula;
    private CellStyle theadStyle;
    private CellStyle tbodyStyle;
    private DataValidationBuilder dataValidationBuilder;
    private ColWriter colWriter;
    private ColumnDataTranslate columnDataTranslate;

    public ColumnDefinition(Field field, ExcelColumn excelColumn, TableContext<T> tableContext) {
        this.tableContext = tableContext;
        this.field = field;
        this.fieldName = field.getName();
        this.titleName = excelColumn.value();
        this.columnWidth = excelColumn.columnWidth();
        this.dataFormatEx = excelColumn.dataFormatEx();
        this.formula = excelColumn.formula();
        this.columnSort = excelColumn.columnSort();
        this.columnDataTranslate = excelColumn.dataTranslate();
        this.theadStyle = tableContext.getTheadStyleMap().get(excelColumn.theadStyleIndex());
        this.tbodyStyle = tableContext.getTbodyStyleMap().get(excelColumn.tbodyStyleIndex());
        Annotation annotation = ReflectUtil.getFirstMarkedAnnotation(field, DataValid.class);
        if (annotation != null) {
            this.dataValidationBuilder = DataValidationBuilderFactory.getInstance(annotation);
        }
        try {
            this.colWriter = excelColumn.colWriter().getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new IllegalColumnConfigureException("ColWriter lack of parameterless constructors! field : " + fieldName);
        }
        SupportedDataType.checkSupportability(field, colWriter);
    }

    public TableContext<T> getTableContext() {
        return tableContext;
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

    public DataValidationBuilder getDataValidationBuilder() {
        return dataValidationBuilder;
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

    public ColumnDataTranslate getColumnDataTranslate() {
        return columnDataTranslate;
    }

    @Override
    public String toString() {
        return titleName;
    }

    @Override
    public int compareTo(ColumnDefinition o) {
        if (this.columnSort > o.columnSort) {
            return 1;
        } else if (this.columnSort == o.columnSort) {
            return 0;
        } else {
            return -1;
        }
    }

    @Override
    public ColumnDefinitionModifier setTitleName(String titleName) {
        this.titleName = titleName;
        return this;
    }

    @Override
    public ColumnDefinitionModifier setTheadStyle(CellStyle cellStyle) {
        this.theadStyle = cellStyle;
        return this;
    }

    @Override
    public ColumnDefinitionModifier setTbodyStyle(CellStyle cellStyle) {
        this.tbodyStyle = cellStyle;
        return this;
    }

    @Override
    public ColumnDefinitionModifier setColumnWidth(int columnWidth) {
        this.columnWidth = columnWidth;
        return this;
    }

    @Override
    public ColumnDefinitionModifier setDataFormatExpreesion(String dataFormatExpreesion) {
        this.dataFormatEx = dataFormatExpreesion;
        return this;
    }

    @Override
    public ColumnDefinitionModifier setFormula(String formula) {
        this.formula = formula;
        return this;
    }


}
