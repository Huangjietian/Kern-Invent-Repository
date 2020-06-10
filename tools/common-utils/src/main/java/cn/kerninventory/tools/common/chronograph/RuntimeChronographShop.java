package cn.kerninventory.tools.common.chronograph;

/**
 * <h1>中文注释</h1>
 * <p>
 *     代码计时器工厂类<br/>
 *     基于 Simple Factory Pattern 和 Decorator Pattern 构思和实现
 * </p>
 * @author Kern
 * @version 1.0
 */
public class RuntimeChronographShop {

    public static Chronograph cheapie(RuntimeUnit runtimeUnit) {
        return new RuntimeChronograph(runtimeUnit);
    }

    public static Chronograph console(RuntimeUnit runtimeUnit) {
        return new ConsoleRuntimeChronograph(new RuntimeChronograph(runtimeUnit));
    }

    public static Chronograph slf4jLogger(RuntimeUnit runtimeUnit) {
        StackTraceElement e = Thread.currentThread().getStackTrace()[1];
        String className = e.getClassName();
        return new LogApiSlf4jRuntimeChronograph(new RuntimeChronograph(runtimeUnit), className);
    }

}
