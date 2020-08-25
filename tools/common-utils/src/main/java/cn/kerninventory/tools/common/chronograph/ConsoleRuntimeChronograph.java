package cn.kerninventory.tools.common.chronograph;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <h1>中文注释</h1>
 * <p>
 *     具备控制台打印功能的代码运行时间计时器, 将所计算值打印到当前的控制台
 * </p>
 * @author Kern
 * @version 1.0
 */
public class ConsoleRuntimeChronograph implements Chronograph {

    private Chronograph chronograph;
    private SimpleDateFormat defaultDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:sss");

    public ConsoleRuntimeChronograph(Chronograph chronograph) {
        this.chronograph = chronograph;
    }

    public ConsoleRuntimeChronograph setDefaultDateFormat(SimpleDateFormat defaultDateFormat) {
        this.defaultDateFormat = defaultDateFormat;
        return this;
    }

    @Override
    public double click(String message) {
        return click(chronograph, message);
    }

    @Override
    public double calculate(String message) {
        return calculate(chronograph, message);
    }

    @Override
    public double calculate(String message, int firstTime, int lastTime) {
        return calculate(chronograph, message, firstTime, lastTime);
    }

    @Override
    public double click(Object marker, String message) {
        double result = chronograph.click(marker, message);
        System.out.printf("Click Time: %s, the nearest duration: %.3f(%s), marker: [%s], message: %s%n", defaultDateFormat.format(new Date()), result, getUnit().name(), marker.toString(), message);
        return result;
    }

    @Override
    public double calculate(Object marker, String message) {
        double result = chronograph.calculate(marker, message);
        System.out.printf("Calculate Time: %s, duration: %.3f(%s), marker: [%s], message: %s%n", defaultDateFormat.format(new Date()), result, getUnit().name(), marker.toString(), message);
        return result;
    }

    @Override
    public double calculate(Object marker, String message, int firstTime, int lastTime) {
        double result = chronograph.calculate(marker, message, firstTime, lastTime);
        System.out.printf("Calculate Time: %s, duration at (%d - %d)  : %.3f(%s), marker: [%s], message: %s%n", defaultDateFormat.format(new Date()), firstTime, lastTime, result, getUnit().name(), marker.toString(), message);
        return result;
    }

    @Override
    public void reset(Object marker) {
        chronograph.reset(marker);
    }

    @Override
    public void clear() {
        chronograph.clear();
    }

    @Override
    public RuntimeUnit getUnit() {
        return chronograph.getUnit();
    }
}
