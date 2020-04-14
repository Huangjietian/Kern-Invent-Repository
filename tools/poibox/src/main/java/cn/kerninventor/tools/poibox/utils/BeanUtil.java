package cn.kerninventor.tools.poibox.utils;

import java.util.Collection;

/**
 * @author Kern
 * @date 2020/4/14 23:37
 */
public class BeanUtil {

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }
    public static boolean isNotEmpty(Collection collection) {
        return !isEmpty(collection);
    }

    public static boolean isEmpty(String str) {
        return str == null || "".equals(str.trim());
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean isNull(Object obj) {
        return obj == null;
    }
}
