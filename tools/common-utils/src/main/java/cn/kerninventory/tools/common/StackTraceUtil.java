package cn.kerninventory.tools.common;

/**
 * @author Kern
 * @date 2020/5/11 16:08
 * @description 栈追踪工具
 */
public class StackTraceUtil {

    /**
     * 获取类名
     * @param mainBody
     * @return
     */
    public static String getClassName(MainBody mainBody) {
        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
        StackTraceElement e = stacktrace[mainBody.getStacktraceIndex()];
        String className = e.getClassName();
        return className;
    }

    /**
     * 获取方法名
     * @param mainBody
     * @return
     */
    public static String getMethodName(MainBody mainBody) {
        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
        StackTraceElement e = stacktrace[mainBody.getStacktraceIndex()];
        String methodName = e.getMethodName();
        return methodName;
    }

    /**
     * 获取类文件名
     * @param mainBody
     * @return
     */
    public static String getFileName(MainBody mainBody) {
        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
        StackTraceElement e = stacktrace[mainBody.getStacktraceIndex()];
        String methodName = e.getFileName();
        return methodName;
    }

    /**
     * 获取当前行号
     * @param mainBody
     * @return
     */
    public static int getLineNumber(MainBody mainBody) {
        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
        StackTraceElement e = stacktrace[mainBody.getStacktraceIndex()];
        int line = e.getLineNumber();
        return line;
    }

    /**
     * 关于MainBody的说明
     * 主体枚举 有两个枚举值
     * caller 表示调用类， 调用类的信息存储于StackTrace的第一个元素中。
     * Current 表示当前类， 当前类的信息存储于StackTrace的第二个元素中。
     * 为了防止不了解该固定形式的调用者传参错误导致可能出现的程序错误，以枚举类的形式进行参数的约束。
     *
     */
    public enum MainBody {

        Caller(1),
        Current(2),
        ;

        private int stacktraceIndex;

        MainBody(int stacktraceIndex) {
            this.stacktraceIndex = stacktraceIndex;
        }

        public int getStacktraceIndex() {
            return stacktraceIndex;
        }
    }

}