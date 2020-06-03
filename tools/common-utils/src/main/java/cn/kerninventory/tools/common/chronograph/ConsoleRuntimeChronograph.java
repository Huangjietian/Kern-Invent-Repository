package cn.kerninventory.tools.common.chronograph;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Kern
 * @date 2020/6/3 16:19
 * @description  具备控制台打印功能的代码运行时间计时器
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
    public RuntimeUnit getUnit() {
        return chronograph.getUnit();
    }
}
