package cn.kerninventory.tools.common;

/**
 * <h1>堆栈跟踪工具类</h1>
 * <p>
 *      通过jdk提供堆栈跟踪元素实现的堆栈跟踪工具。
 * </p>
 * @author Kern
 * @version 1.0
 */
public class StackTraceUtil {

    /**
     * <p>
     *     获取类名
     * </p>
     * @param fetcher
     * @return
     */
    public static String getClassName(Fetcher fetcher) {
        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
        StackTraceElement e = stacktrace[fetcher.getStacktraceIndex()];
        String className = e.getClassName();
        return className;
    }

    /**
     * <p>
     *     获取方法名
     * </p>
     * @param fetcher
     * @return
     */
    public static String getMethodName(Fetcher fetcher) {
        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
        StackTraceElement e = stacktrace[fetcher.getStacktraceIndex()];
        String methodName = e.getMethodName();
        return methodName;
    }

    /**
     * <p>
     *     获取类文件名
     * </p>
     * @param fetcher
     * @return
     */
    public static String getFileName(Fetcher fetcher) {
        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
        StackTraceElement e = stacktrace[fetcher.getStacktraceIndex()];
        String methodName = e.getFileName();
        return methodName;
    }

    /**
     * <p>
     *     获取当前行号
     * </p>
     * @param fetcher
     * @return
     */
    public static int getLineNumber(Fetcher fetcher) {
        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
        StackTraceElement e = stacktrace[fetcher.getStacktraceIndex()];
        int line = e.getLineNumber();
        return line;
    }

    /**
     * <h1>主要访问体</h1>
     * <p>
     *     关于Fetcher的说明:
     *     为了防止不了解该固定形式的调用者传参错误导致可能出现的程序错误，以枚举类的形式进行参数的约束。<br/>
     *     有两个枚举值<br/>
     *     Caller 表示调用类， 调用类的信息存储于StackTrace的第3个元素中。<br/>
     *     Current 表示当前类， 当前类的信息存储于StackTrace的第2个元素中。
     * </p>
     */
    public enum Fetcher {

        Caller(3),
        Current(2),
        ;

        private int stacktraceIndex;

        Fetcher(int stacktraceIndex) {
            this.stacktraceIndex = stacktraceIndex;
        }

        public int getStacktraceIndex() {
            return stacktraceIndex;
        }
    }



}