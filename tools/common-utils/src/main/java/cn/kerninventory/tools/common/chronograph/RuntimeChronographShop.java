package cn.kerninventory.tools.common.chronograph;

/**
 * @author Kern
 * @date 2020/6/3 16:05
 * @description
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
