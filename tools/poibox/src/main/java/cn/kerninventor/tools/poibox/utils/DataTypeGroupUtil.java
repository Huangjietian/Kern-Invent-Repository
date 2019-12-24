package cn.kerninventor.tools.poibox.utils;

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

    public static boolean isMemberOfIntType(Class clz) {
        return (clz == Short.class || clz == Integer.class || clz == Long.class);
    }

    public static boolean isMemberOfNumberType(Class clz) {
        return Number.class.isAssignableFrom(clz);
    }

    public static boolean isMemberOfDateType(Class clz) {
        return Date.class.isAssignableFrom(clz);
    }

    public static boolean siMemberOfCharacter(Class clz) {
        return (clz == String.class
                || clz == Character.class);
    }
}
