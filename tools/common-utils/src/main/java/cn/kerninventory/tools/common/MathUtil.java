package cn.kerninventory.tools.common;

/**
 * @author Kern
 * @date 2020/6/8 16:12
 * @description  算数工具，欢迎多多补充
 */
public class MathUtil {

    /**
     * 根据指定的位数(个十百千)对参数进行四射五入
     * <p>
     *     限制条件参考roundDecimals(number: double);
     * </p>
     * @param number 需要四舍五入的小数
     * @param powersOfTen 位数（可以为负数）
     * @return
     */
    public static int roundOf(double number, int powersOfTen) {
        double d = Math.pow(10.0d, powersOfTen);
        number = number / d;
        int it = roundDecimals(number);
        if (powersOfTen >= 0) {
            it = (int) (it * d);
        } else {
            for (int i = 0 ; i > powersOfTen ; i --) {
                d = it / 10.0d;
                it = roundDecimals(d);
            }
        }
        return it;
    }

    /**
     * 小数的四舍五入
     * <p>
     *     当一个数的乘数导致个位数在自身乘积的基础上加 1 时，小数位必然大于或等于0.5，由此在乘2后补数1 去掉小数位，右移一位将得到四舍五入的结果。
     * </p>
     * <p>
     *     调用该方法时需要注意java中double类型的精度缺失，在小数位超过15位时，java将自动把double数值在最后一位进1，可能导致结果的错误。
     *     例1 double d = 14.9999999999999999; 在java中将得到 15.0,
     *     例2 double d = 14.999999999999999;  在java中将得到 14.999999999999998
     *     上述例1 将导致结果有误， 请注入使用时的参数不可违反该限制。
     * </p>
     *
     * @param number 需要四舍五入的小数
     * @return
     */
    public static int roundDecimals(double number) {
        return ((int) ((number * 2) + 1)) >> 1;
    }

}
