package cn.kerninventor.tools.poibox.data.datatable.datavalidation;

import org.apache.poi.ss.usermodel.DataValidationConstraint;

/**
 * @Title: CompareType
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.data.datatable.validation
 * @Author Kern
 * @Date 2019/12/13 11:17
 * @Description: TODO
 */
public enum CompareType {
    BET(" <> ", DataValidationConstraint.OperatorType.BETWEEN, true),
    NOBET(" >< ",DataValidationConstraint.OperatorType.NOT_BETWEEN, true),
    ET(" == ",DataValidationConstraint.OperatorType.EQUAL, false),
    NOET(" != ",DataValidationConstraint.OperatorType.NOT_BETWEEN, false),
    GT(" > ", DataValidationConstraint.OperatorType.GREATER_THAN, false),
    LT(" < ",DataValidationConstraint.OperatorType.LESS_THAN, false),
    GTE(" >= ",DataValidationConstraint.OperatorType.GREATER_OR_EQUAL, false),
    LTE(" <= ",DataValidationConstraint.OperatorType.LESS_OR_EQUAL, false),
    ;

    private String expression;
    private int code;
    private boolean optionalValueValidity;

    CompareType(String expression, int code, boolean optionalValueValidity) {
        this.expression = expression;
        this.code = code;
        this.optionalValueValidity = optionalValueValidity;
    }

    public boolean isOptionalValueValidity() {
        return optionalValueValidity;
    }

    public int getCode() {
        return code;
    }

    public String getExpression() {
        return expression;
    }

}
