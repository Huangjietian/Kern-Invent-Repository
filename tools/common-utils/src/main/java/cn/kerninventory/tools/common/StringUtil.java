package cn.kerninventory.tools.common;

/**
 * @author Kern
 * @date 2020/5/11 18:32
 * @description
 */
public class StringUtil {

    /**
     * 字符串截取=========================================================================
     */
    @FunctionalInterface
    private interface SubIndex {
        int index();
    }

    /**
     * 截取指定坐标之前的字符串
     * @param str
     * @param subIndex
     * @return
     */
    public static String subStrFrontByIndexOf(String str, SubIndex subIndex) {
        int index = subIndex.index();
        if (-1 == index) {
            return str;
        }
        return str.substring(index);
    }

    /**
     * 截取指定坐标之后的字符串
     * @param str
     * @param subIndex
     * @return
     */
    public static String subStrBackByIndexOf(String str, SubIndex subIndex) {
        int index = subIndex.index();
        if (-1 == index) {
            return str;
        }
        return str.substring(0, index);
    }

    /**
     * 截取指定字符第一次出现位置之前的字符
     * @param str
     * @param symbol
     * @return
     */
    public static String subFrontByIndexOf(String str, String symbol) {
        return subStrFrontByIndexOf(str, () -> str.indexOf(symbol));
    }

    /**
     * 截取指定字符第一次出现位置之后的字符
     * @param str
     * @param symbol
     * @return
     */
    public static String subBackByIndexOf(String str, String symbol) {
        return subStrBackByIndexOf(str, () -> str.indexOf(symbol));
    }


    /**
     * 截取指定字符最后一次出现位置之前的字符
     * @param str
     * @param symbol
     * @return
     */
    public static String subFrontByLastIndexOf(String str, String symbol) {
        return subStrFrontByIndexOf(str, () -> str.lastIndexOf(symbol));
    }

    /**
     * 截取指定字符最后一次出现位置之后的字符
     * @param str
     * @param symbol
     * @return
     */
    public static String subBackByLastIndexOf(String str, String symbol) {
        return subStrBackByIndexOf(str, () -> str.lastIndexOf(symbol));
    }

    /**
     * 字符长度匹配
     * @param str
     * @param len
     * @return
     */
    public static boolean matchLength(String str, int len) {
        if (str == null) {
            return false;
        }
        return str.length() == len;
    }

    /**
     *
     * @param str
     * @return
     */
    public static boolean isNull(String str) {
        return str == null;
    }

    /**
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }

    /**
     *
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        return str == null || "".equals(str.trim());
    }

    /**
     * @param str
     * @return
     */
    public static String trim2Null(String str) {
        if (str == null) {
            return str;
        }
        return isBlank(str) ? null : str.trim();
    }

    /**
     * @param str
     * @return
     */
    public static String trim2Empty(String str) {
        return isNull(str) ? "" : str.trim();
    }
}
