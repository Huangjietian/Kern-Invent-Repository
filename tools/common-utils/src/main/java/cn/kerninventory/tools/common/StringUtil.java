package cn.kerninventory.tools.common;

/**
 * <h1</h1>
 * <p>
 *     字符串工具类
 * </p>
 * @author Kern
 * @version 1.0
 */
public class StringUtil {

    /**
     * <h1>中文注释</h1>
     * <p>
     *     截取下标函数
     * </p>
     */
    @FunctionalInterface
    private interface SubIndex {
        int index();
    }

    /**
     * <p>
     *     截取指定坐标之前的字符串
     * </p>
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
     * <p>
     *     截取指定坐标之后的字符串
     * </p>
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
     * <p>
     *     截取指定字符第一次出现位置之前的字符
     * </p>
     * @param str
     * @param symbol
     * @return
     */
    public static String subFrontByIndexOf(String str, String symbol) {
        return subStrFrontByIndexOf(str, () -> str.indexOf(symbol));
    }

    /**
     * <p>
     *     截取指定字符第一次出现位置之后的字符
     * </p>
     * @param str
     * @param symbol
     * @return
     */
    public static String subBackByIndexOf(String str, String symbol) {
        return subStrBackByIndexOf(str, () -> str.indexOf(symbol));
    }


    /**
     * <p>
     *     截取指定字符最后一次出现位置之前的字符
     * </p>
     * @param str
     * @param symbol
     * @return
     */
    public static String subFrontByLastIndexOf(String str, String symbol) {
        return subStrFrontByIndexOf(str, () -> str.lastIndexOf(symbol));
    }

    /**
     * <p>
     *     截取指定字符最后一次出现位置之后的字符
     * </p>
     * @param str
     * @param symbol
     * @return
     */
    public static String subBackByLastIndexOf(String str, String symbol) {
        return subStrBackByIndexOf(str, () -> str.lastIndexOf(symbol));
    }

    /**
     * <p>
     *     字符长度匹配
     * </p>
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
     * <p>
     *     字符串 null 判断
     * </p>
     * @param str
     * @return
     */
    public static boolean isNull(String str) {
        return str == null;
    }

    /**
     * <p>
     *     字符串非null和非空串判断
     * </p>
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }

    /**
     * <p>
     *     字符串非null和非空集判断
     * </p>
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        return str == null || "".equals(str.trim());
    }

    /**
     * <p>
     *     字符串空则置为null <br/>
     *     example：
     *     str == null                                      ： null; <br/>
     *     "".equals(str.trim()) == true                    :  null; <br/>
     *     othler case                                      : str.trim().
     * </p>
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
     * <p>
     *     字符串空则置为空串 <br/>
     *     example：
     *     str == null                                      ： ""; <br/>
     *     othler case                                      : str.trim().
     * </p>
     * @param str
     * @return
     */
    public static String trim2Empty(String str) {
        return isNull(str) ? "" : str.trim();
    }

}
