package cn.kerninventory.tools.common;

/**
 * @author Kern
 * @date 2020/5/11 18:32
 * @description
 */
public class StringUtil {

//    public static void main(String[] args) {
//        String str = "3245313..5346";
//        System.out.println(StringUtil.subFrontByLastIndexOf(str, "."));
//    }

    /**
     * 字符串截取=========================================================================
     */
    @FunctionalInterface
    private interface SubStringFunction {
        int sub();
    }

    private static String subStrBtIndexOf(String str, SubStringFunction function, boolean front) {
        int index = function.sub();
        if (-1 == index) {
            return str;
        }
        if (front) {
            return str.substring(index);
        } else {
            return str.substring(0, index);
        }
    }

    public static String subFrontByIndexOf(String str, String symbol) {
        return subStrBtIndexOf(str, () -> str.indexOf(symbol), true);
    }

    public static String subBackByIndexOf(String str, String symbol) {
        return subStrBtIndexOf(str, () -> str.indexOf(symbol), false);
    }

    public static String subFrontByLastIndexOf(String str, String symbol) {
        return subStrBtIndexOf(str, () -> str.lastIndexOf(symbol), true);
    }

    public static String subBackByLastIndexOf(String str, String symbol) {
        return subStrBtIndexOf(str, () -> str.lastIndexOf(symbol), false);
    }

    public static boolean matchLength(String str, int len) {
        if (str == null) {
            return false;
        }
        return str.length() == len;
    }

    public static String trim2Null(String str) {
        if (str == null) {
            return str;
        }
        return "".equals(str.trim()) ? null : str.trim();
    }

    public static String trim2Empty(String str) {
        return str == null ? "" : str.trim();
    }
}
