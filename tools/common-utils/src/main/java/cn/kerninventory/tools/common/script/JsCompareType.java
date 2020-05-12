package cn.kerninventory.tools.common.script;

/**
 * @author Kern
 * @date 2020/5/11 15:50
 * @description
 */
public enum JsCompareType {

    equal(" == "),
    greaterThan(" > "),
    lessThan(" < "),
    greaterThanOrEqualTo(" >= "),
    lessThanOrEqualTo(" <= ")
    ;

    JsCompareType(String expression) {
        this.expression = expression;
    }

    private String expression;

    public String getExpression() {
        return expression;
    }

}
