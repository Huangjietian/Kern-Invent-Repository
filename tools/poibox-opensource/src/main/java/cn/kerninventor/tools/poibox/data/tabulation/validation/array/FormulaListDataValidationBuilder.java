package cn.kerninventor.tools.poibox.data.tabulation.validation.array;

import cn.kerninventor.tools.poibox.BoxGadget;
import cn.kerninventor.tools.poibox.data.tabulation.definition.ColumnDefinition;
import cn.kerninventor.tools.poibox.data.tabulation.definition.TabulationDefinition;
import cn.kerninventor.tools.poibox.data.tabulation.validation.AbstractDvBuilder;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;

/**
 * @author Kern
 * @version 1.0
 */
public class FormulaListDataValidationBuilder extends AbstractDvBuilder<FormulaListDataValid> {

    private TabulationDefinition tabulationDefinition;

    public FormulaListDataValidationBuilder(FormulaListDataValid formulaListDataValid) {
        super(formulaListDataValid);
    }

    @Override
    protected String getPromptBoxMessage() {
        return getAnnotation().promptMessage();
    }

    @Override
    protected String getErrorBoxMessage() {
        return getAnnotation().errorMessage();
    }

    @Override
    protected DataValidationConstraint createDvConstraint(DataValidationHelper dvHelper) {
        FormulaListDataValid formulaListDataValid = getAnnotation();
        String value = formulaListDataValid.value();
        DataValidationConstraint dvConstraint;
        if (value.startsWith(FormulaListDataValid.CASECADE_TAG)){
            String fieldName = value.replace(FormulaListDataValid.CASECADE_TAG,"");
            List<ColumnDefinition> columnDefinitions = tabulationDefinition.getColumnDefinitions();
            ColumnDefinition columnDefinition = columnDefinitions.stream().filter(e -> e.getFieldName().equalsIgnoreCase(fieldName)).findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("No column field named" + fieldName + " was found, cannot set formulaList(casecade)"));
            int columnIndex = columnDefinition.getColumnIndex() + 1;
            String casecadeExpression = getCasecadeExpression(columnIndex);
            dvConstraint = dvHelper.createFormulaListConstraint(casecadeExpression);
        } else {
            dvConstraint = dvHelper.createFormulaListConstraint(FormulaListDataValid.NAME_PRIFIIX + formulaListDataValid.value());
        }
        return dvConstraint;
    }

    private String getCasecadeExpression(int columnIndex) {
        String columnEnIndex = BoxGadget.transferExcelColumnIndex(columnIndex);
        StringBuilder formulaListExpressionBuilder = new StringBuilder();
        formulaListExpressionBuilder
                .append("INDIRECT(CONCATENATE(\"")
                .append(FormulaListDataValid.NAME_PRIFIIX)
                .append("\",INDIRECT(CONCATENATE(\"")
                .append("$")
                .append(columnEnIndex)
                .append("$\"")
                .append(",ROW()))))");
        return formulaListExpressionBuilder.toString();
    }

    @Override
    public void setDataValidation(TabulationDefinition tabulationDefinition, ColumnDefinition columnDefinition, Sheet sheet) {
        this.tabulationDefinition = tabulationDefinition;
        super.setDataValidation(tabulationDefinition, columnDefinition, sheet);
    }
}
