package cn.kerninventor.tools.poibox.data.datatable.validation;

/**
 * @Title: CompareType
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.data.datatable.validation
 * @Author Kern
 * @Date 2019/12/13 11:17
 * @Description: TODO
 */
public enum CompareType {
    BET(" <> ",0, true),
    NOBET(" >< ",1, true),
    ET(" == ",2, false),
    NOET(" != ",3, false),
    GT(" > ", 4, false),
    LT(" < ",5, false),
    GTE(" >= ",6, false),
    LTE(" <= ",7, false),
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
