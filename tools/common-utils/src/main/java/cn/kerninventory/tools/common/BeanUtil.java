package cn.kerninventory.tools.common;

import java.util.Collection;
import java.util.Map;

/**
 * @author Kern
 * @date 2020/5/11 16:08
 * @description Bean判空工具
 */
public class BeanUtil {

    @FunctionalInterface
    public interface Condition {
        boolean judge();
    }

    private static void judgment(Condition condittion, String manifesto) {
        if (condittion.judge()) {
            throw new RuntimeException(manifesto);
        }
    }

    private static void judgment(Condition condittion, String manifesto, Throwable throwable) {
        if (condittion.judge()) {
            throw new RuntimeException(manifesto, throwable);
        }
    }

    private static void judgment(Condition condittion, RuntimeException e) {
        if (condittion.judge()) {
            throw e;
        }
    }

    /**
     * Object判空=========================================================================
     */

    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static boolean nonNull(Object obj) {
        return obj != null;
    }

    public static <T> T notNull(T t) {
        return notNull(t, t.getClass() + " cannot be null!");
    }

    public static <T> T notNull(T t, String errorMsg) {
        judgment(() -> t == null , new NullPointerException(errorMsg));
        return t;
    }

    public static <T> boolean isEmpty(T t) {
        if (t instanceof String) {
            return isEmpty((String)t);
        } else if (t instanceof Collection) {
            return isEmpty((Collection)t);
        } else if (t.getClass().isArray()) {
            return isEmpty((Object[]) t);
        } else if (t instanceof Map) {
            return isEmpty((Map) t);
        } else {
            return isNull(t);
        }
    }

    public static <T> boolean nonEmpty(T t) {
        return !isEmpty(t);
    }

    public static <T extends Object> T notEmpty(T t) {
        return notEmpty(t, t.getClass() + " cannot be empty!");
    }

    public static <T extends Object> T notEmpty(T t, String errorMsg) {
        judgment(() -> isEmpty(t), new IllegalArgumentException(errorMsg));
        return t;
    }

    public static <T extends Object> T notEmpty(T t, Condition condittion) {
        return notEmpty(t, condittion, t.getClass() + " cannot be empty!");
    }

    public static <T extends Object> T notEmpty(T t, Condition condittion, String errorMsg) {
        return notEmpty(t, condittion, new IllegalArgumentException(errorMsg));
    }

    public static <T extends Object> T notEmpty(T t, Condition condittion, RuntimeException exc) {
        judgment(condittion, exc);
        return t;
    }

    /**
     * 字符串判空=========================================================================
     */

    public static boolean nonEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }

    public static boolean nonBlank(String str) {
        return !isBlank(str);
    }

    public static boolean isBlank(String str) {
        String temporary = new String(str);
        return temporary == null || "".equals(temporary.trim());
    }

    public static String notEmpty(String str) {
        return notEmpty(str, "The string cannot be empty!");
    }

    public static String notEmpty(String str, String errorMsg) {
        judgment(() -> isEmpty(str), new IllegalArgumentException(errorMsg));
        return str;
    }

    public static String notBlank(String str) {
        return notBlank(str, "The string cannot be blank!");
    }

    public static String notBlank(String str, String errorMsg) {
        judgment(() -> isBlank(str), new IllegalArgumentException(errorMsg));
        return str;
    }

    /**
     * Collection判空=========================================================================
     */
    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean nonEmpty(Collection collection) {
        return !isEmpty(collection);
    }

    public static <T extends Collection> T notEmpty(T t) {
        return notEmpty(t, t.getClass() + " Cannot be empty!");
    }

    public static <T extends Collection> T notEmpty(T t, String errorMsg) {
        judgment(() -> isEmpty(t), new IllegalArgumentException(errorMsg));
        return t;
    }

    /**
     * 数组判空=========================================================================
     */

    public static boolean isEmpty(Object[] arrays) {
        return arrays == null || arrays.length == 0;
    }

    public static boolean nonEmpty(Object[] arrays) {
        return !isEmpty(arrays);
    }

    public static <T> T[] notEmpty(T[] arrays) {
        return notEmpty(arrays, "The arrays cannot be empty!");
    }

    public static <T> T[] notEmpty(T[] arrays, String errorMsg) {
        judgment(() -> isEmpty(arrays), new IllegalArgumentException(errorMsg));
        return arrays;
    }

    /**
     * Map判空=========================================================================
     */
    public static boolean isEmpty(Map map) {
        return map == null || map.isEmpty();
    }

    public static boolean nonEmpty(Map map) {
        return !isEmpty(map);
    }

    public static <K, V> Map<K, V> notEmpty(Map<K, V> map) {
        return notEmpty(map, map.getClass() + " cannot be empty!");
    }

    public static <K, V> Map<K, V> notEmpty(Map<K, V> map, String errorMsg) {
        judgment(() -> isEmpty(map), new IllegalArgumentException(errorMsg));
        return map;
    }

    @FunctionalInterface
    public interface AfterAction<T> {
        T action();
    }

    public static <T> T nonNull2Do(Object arg, AfterAction<T> succeedAction, AfterAction<T> failureAction) {
        if (nonNull(arg)) {
            return succeedAction.action();
        }
        return failureAction.action();
    }

    public static <T> T branchAction(Condition condition, AfterAction<T> succeedAction, AfterAction<T> failureAction) {
        if (condition.judge()) {
            return succeedAction.action();
        }
        return failureAction.action();
    }

}
