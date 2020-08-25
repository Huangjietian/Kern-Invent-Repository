package cn.kerninventory.tools.common.chronograph;

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
     * @param message
     * @return
     */
    double click(String message);

    /**
     * <p>
     *     计算第一次点击至当前的总时长值(double),根据marker判断是否为相同对象的点击操作。
     * </p>
     * @param message
     * @return
     */
    double calculate(String message);

    /**
     * <p>
     *     计算firstTime - lastTime之间的总时长值(double),根据marker判断是否为相同对象的点击操作。
     * </p>
     * @param message
     * @param firstTime
     * @param lastTime
     * @return
     */
    double calculate(String message, int firstTime, int lastTime);


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
     *     重置所有时间
     * </p>
     */
    void clear();

    /**
     * <p>
     *     获取计时单位
     * </p>
     * @return
     */
    RuntimeUnit getUnit();

}