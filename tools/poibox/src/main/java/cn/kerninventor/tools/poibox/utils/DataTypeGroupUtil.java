package cn.kerninventor.tools.poibox.utils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Title: DataTypeGroupUtil
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.utils
 * @Author Kern
 * @Date 2019/12/18 10:14
 * @Description: TODO
 */
public class DataTypeGroupUtil {

    public static boolean isMemberOfNumberType(Class clz) {
        return Number.class.isAssignableFrom(clz);
    }

    public static boolean isMemberOfIntType(Class clz) {
        if (isMemberOfNumberType(clz)) {
            return (clz == Byte.class || clz == Short.class || clz == Integer.class || clz == Long.class);
        }
        return false;
    }

    public static boolean isMemberOfDecimal(Class clz) {
        if (isMemberOfNumberType(clz)) {
            return (clz == Float.class || clz == Double.class || clz == BigDecimal.class);
        }
        return false;
    }

    public static boolean isMemberOfCharacter(Class clz) {
        return (clz == String.class
                || clz == Character.class);
    }

    public static boolean isMemberOfDateType(Class clz) {
        return Date.class.isAssignableFrom(clz);
    }

}
