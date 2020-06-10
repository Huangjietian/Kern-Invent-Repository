package cn.kerninventory.tools.common.chronograph;

import com.sun.istack.internal.NotNull;

/**
 * <h1>中文注释</h1>
 * <p>
 *     代码计时器接口
 * </p>
 * @author Kern
 * @version 1.0
 */
public interface Chronograph {

    /**
     * <p>
     *     点击动作，每次点击将得到距离上一次点击的时长值(double),根据marker判断是否为相同对象的点击操作。
     * </p>
     * @param marker
     * @param message
     * @return
     */
    double click(Object marker, String message);

    /**
     * <p>
     *     计算第一次点击至当前的总时长值(double),根据marker判断是否为相同对象的点击操作。
     * </p>
     * @param marker
     * @param message
     * @return
     */
    double calculate(Object marker, String message);

    /**
     * <p>
     *     计算firstTime - lastTime之间的总时长值(double),根据marker判断是否为相同对象的点击操作。
     * </p>
     * @param marker
     * @param message
     * @param firstTime
     * @param lastTime
     * @return
     */
    double calculate(Object marker, String message, int firstTime, int lastTime);

    /**
     * <p>
     *     重置时间
     * </p>
     * @param marker
     */
    void reset(Object marker);

    /**
     * <p>
     *     获取计时单位
     * </p>
     * @return
     */
    RuntimeUnit getUnit();

    /**
     * <p>
     *     代码块时长计算
     * </p>
     * @param runnable
     * @return
     */
    default double blockCount(@NotNull Runnable runnable) {
        return runningNanoTime(runnable).doubleValue() / getUnit().getProduct();
    }

    /**
     * <p>
     *     代码块纳秒计算
     * </p>
     * @param runnable
     * @return
     */
    default Long runningNanoTime(@NotNull Runnable runnable) {
        long nano1 = System.nanoTime();
        runnable.run();
        long nano2 = System.nanoTime();
        return nano2 - nano1;
    }
}