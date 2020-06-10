package cn.kerninventory.tools.common;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * <h1>中文描述</h1>
 * <p>
 *     Js 引擎工具类
 * </p>
 * <p>
 *     jdk 提供了多种多样的shell脚本语言引擎，其中包含了js脚本引擎。<br/>
 *     通过js殷引擎实现的计算工具类，借助js语言弱类型检测的特点，在java环境下可以通过传入字符串形式的js语句给js引擎编译执行。<br/>
 *     由于java的比较符无法作为枚举值或者常量保存，在搭建架构或设计封装中间件时需要借由指定的tag实现比较计算的动态选择，<br/>
 *     如果使用该工具类，可以把比较值使用字符串的正确表示形式传入，获得比较结果，实现动态比较函数的功能。
 * </p>
 * @author Kern
 * @version 1.0
 */
public final class JavaScriptUtil {

//    public static void main(String[] args) {
//        //设想： 在java中使用脚本语言的简易工具。
//        //现在只想到这种使用场景， 就是当比较符号不确定的时候， 用js来运行java代码会比较方便，实际的应用场景可能有很多，有待扩展
//        //性能上面没有做完整的测试，目前看来能够满足普通业务场景的性能需求。
//
//        boolean b1 = JsMathUtil.compare(1.15, ">", 1.14);
//        System.out.println("1.15 > 1.14 ? " + b1);
//        Integer[] integers = {1,2,3,4,5,6,7,8,9,10};
//        boolean b2 = JsMathUtil.up2Standard(0, " > ", integers);
//        System.out.println("数组integers的数字都大于0 ？ " + b2);
//    }

    private static ScriptEngineManager engineManager = new ScriptEngineManager();

    private static ScriptEngine jsEngine = engineManager.getEngineByName("js");

    /**
     * <p>
     *     比较多个Number类型是否都满足某比较条件, 例如小于比较，该方法效果等同于 <br/>
     *     {@code return Arrays.stream(ts).allMatch(e -> e.doubleValue() < standard.doubleValue());}
     * </p>
     * @param standard 基准值
     * @param compare 比较符的字符串表示
     * @param ts {@link Number}数组对象
     * @param <T> {@link Number}
     * @throws IllegalArgumentException
     * @return boolean 比较结果
     */
    public static  <T extends Number> boolean up2Standard(T standard, String compare, T... ts) {
        if (ts.length == 0 || standard == null) {
            return false;
        }
        StringBuilder equation = new StringBuilder();
        for (T t : ts) {
            equation.append(t)
                    .append(" ")
                    .append(compare)
                    .append(" ")
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
     * <p>
     *     根据指定的比较符，获得两个数字的比较结果
     * </p>
     * @param t1 数值1
     * @param compare 比较符的字符串表示
     * @param t2 数值2
     * @param <T> {@link Number}
     * @throws IllegalArgumentException
     * @return boolean 比较结果
     */
    public static <T extends Number> boolean compare(T t1, String compare, T t2) {
        try {
            return (boolean) jsEngine.eval(t1 + " " + compare + " " + t2);
        } catch (ScriptException e) {
            throw new IllegalArgumentException("Js parse error" + e.getMessage() , e);
        }
    }

}
