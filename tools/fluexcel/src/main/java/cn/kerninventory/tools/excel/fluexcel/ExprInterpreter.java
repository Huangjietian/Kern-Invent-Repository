package cn.kerninventory.tools.excel.fluexcel;

import java.util.Map;
import java.util.Objects;

/**
 * <p>一句话描述</p>
 *
 * @author Kern
 */
public class ExprInterpreter {

    private Map<String, String> keyValueMatcher;

    public String interpret(String expression) {
        expression = Objects.requireNonNull(expression,"Expression is null!").trim();
        if (expression.startsWith("#")) {
            return keyValueMatcher.get(expression.substring(1));
        } else if (expression.startsWith("%")) {

        }
        return null;
    }


}
