package cn.kerninventory.tools.common;

import java.util.regex.Pattern;

/**
 * <h1>中文注释</h1>
 * <p>
 *     正则工具类<br/>
 *     收录了一些较为通用得正则校验方法，待完善。
 * </p>
 * @author Kern
 * @version 1.0
 */
public class RegularUtil {

    public static boolean match(String str, String regex) {
        return Pattern.compile(regex).matcher(str).matches();
    }

    /**
     * <p>
     *     校验字符串是否为邮箱地址
     * </p>
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        String regex = "^([a-z0-9_.-]+)@([\\da-z.-]+)\\.([a-z.]{2,6})$";
        return match(email, regex);
    }

    /**
     * <p>
     *     校验字符串是否为手机号码
     * </p>
     * @param phone
     * @return
     */
    public static boolean isMobileNumber(String phone) {
        String regex = "^1([3-8])\\d{9}$";
        return match(phone, regex);
    }

    /**
     * <p>
     *     校验字符串是否为固定电话号码
     * </p>
     * @param tel
     * @return
     */
    public static boolean isTelPhone(String tel) {
        String regex = "^(0[0-9]{2,3}-)?([2-9][0-9]{6,7})+(-[0-9]{1,4})?$";
        return match(tel, regex);
    }

    /**
     * <p>
     *     校验字符串是否为邮政编码
     * </p>
     * @param postCode
     * @return
     */
    public static boolean isPostCode(String postCode) {
        String regex = "^\\d{6}$";
        return match(postCode, regex);
    }

    /**
     * <p>
     *      校验字符串是否为数字
     * </p>
     * @param number
     * @return
     */
    public static boolean isNumber(String number) {
        String regex = "^(-?\\d+)(\\.\\d+)?$";
        return match(number, regex);
    }

    /**
     * <p>
     *     校验字符串是否为中华人民共和国的公民身份证号码
     * </p>
     * @param identityCard
     * @return
     */
    public static boolean isZhIdentityCard(String identityCard) {
        String regex = "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|" +
                "(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";
        boolean matchs = match(identityCard, regex);
        if (matchs) {
            if (StringUtil.matchLength(identityCard, 18)) {
                try {
                    char[] charArray = identityCard.toCharArray();
                    int[] idCardWi = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
                    String[] idCardY = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
                    int sum = 0;
                    for (int i = 0; i < idCardWi.length; i++) {
                        int current = Integer.parseInt(String.valueOf(charArray[i]));
                        int count = current * idCardWi[i];
                        sum += count;
                    }
                    char idCardLast = charArray[17];
                    int idCardMod = sum % 11;
                    if (idCardY[idCardMod].toUpperCase().equals(String.valueOf(idCardLast).toUpperCase())) {
                        return true;
                    } else {
                        return false;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return matchs;
    }

    /**
     * <p>
     *     校验字符串是否为银行卡号
     * </p>
     * @param bankCard
     * @return
     */
    public static boolean isBankCard(String bankCard) {
        if(bankCard.length() < 15 || bankCard.length() > 19) {
            return false;
        }
        String nonCheckCodeBankCard = bankCard.substring(0, bankCard.length() - 1);
        if(nonCheckCodeBankCard == null
                || nonCheckCodeBankCard.trim().length() == 0
                || !nonCheckCodeBankCard.matches("\\d+")) {
            return false;
        }
        char[] chs = nonCheckCodeBankCard.trim().toCharArray();
        int luhmSum = 0;
        for(int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if(j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        char bit = (luhmSum % 10 == 0) ? '0' : (char)((10 - luhmSum % 10) + '0');
        return bankCard.charAt(bankCard.length() - 1) == bit;
    }

    /**
     * <p>
     *     使用模10法校验字符串是否为银行卡号
     * </p>
     * @param bankCard
     * @return
     */
    public static boolean isBankCardLuhn(String bankCard) {
        LuhnUtil luhnUtil = new LuhnUtil(bankCard);
        return luhnUtil.check();
    }

    /**
     * <p>
     *     校验字符串是否为一个网络网址
     * </p>
     * @param httpUrl
     * @return
     */
    public static boolean isHttpUrl(String httpUrl) {
        String regex = "(((https|http)?://)?([a-z0-9]+[.])|(www.))"
                + "\\w+[.|\\/]([a-z0-9]{0,})?[[.]([a-z0-9]{0,})]+((/[\\S&&[^,;\u4E00-\u9FA5]]+)+)?([.][a-z0-9]{0,}+|/?)";
        return match(httpUrl, regex);
    }
}
