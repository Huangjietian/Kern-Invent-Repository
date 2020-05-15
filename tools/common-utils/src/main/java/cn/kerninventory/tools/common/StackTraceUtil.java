package cn.kerninventory.tools.common;

/**
 * @author Kern
 * @date 2020/5/11 16:08
 * @description 栈追踪工具
 */
public class StackTraceUtil {

    public static String getClassName() {
        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
        StackTraceElement e = stacktrace[2];
        String className = e.getClassName();
        return className;
    }

    public static String getMethodName() {
        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
        StackTraceElement e = stacktrace[2];
        String methodName = e.getMethodName();
        return methodName;
    }

    public static String getFileName() {
        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
        StackTraceElement e = stacktrace[2];
        String methodName = e.getFileName();
        return methodName;
    }

    public static int getLineNumber() {
        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
        StackTraceElement e = stacktrace[2];
        int line = e.getLineNumber();
        return line;
    }

}