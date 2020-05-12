package cn.kerninventory.tools.common.script;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author Kern
 * @date 2020/5/7 10:40
 */
public final class JsMathUtil {

    private static ScriptEngineManager engineManager = new ScriptEngineManager();
    private static ScriptEngine jsEngine = engineManager.getEngineByName("js");

    /**
     * 比较多个Number类型是否都满足某比较条件， 比较条件为 JsCompareType + standard
     * @param standard
     * @param compare
     * @param ts
     * @param <T>
     * @return
     */
    public static  <T extends Number> boolean up2Standard(T standard, JsCompareType compare, T... ts) {
        if (ts.length == 0 || standard == null) {
            return false;
        }
        StringBuilder equation = new StringBuilder();
        for (T t : ts) {
            equation.append(t)
                    .append(compare.getExpression())
                    .append(standard)
                    .append(" && ");
        }
        equation.append("true");
        try {
            return (boolean) jsEngine.eval(equation.toString());
        } catch (ScriptException e) {
            throw new IllegalArgumentException("Js parse error" + e.getMessage() , e);
        }
    }

    /**
     * 比较两个Number类型的大小
     * @param t1
     * @param compare
     * @param t2
     * @param <T>
     * @return
     */
    public static <T extends Number> boolean compare(T t1, JsCompareType compare, T t2) {
        try {
            return (boolean) jsEngine.eval(t1 + compare.getExpression() + t2);
        } catch (ScriptException e) {
            throw new IllegalArgumentException("Js parse error" + e.getMessage() , e);
        }
    }

}
