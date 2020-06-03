package cn.kerninventory.tools.common.chronograph;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Kern
 * @date 2020/6/3 16:30
 * @description   具备SLF4J日志输出功能的代码运行时间计时器
 */
public class LogApiSlf4jRuntimeChronograph implements Chronograph {

    private SimpleDateFormat defaultDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:sss");
    private Logger logger;
    private Chronograph chronograph;

    public LogApiSlf4jRuntimeChronograph(Chronograph chronograph, String logName) {
        this.chronograph = chronograph;
        this.logger = LoggerFactory.getLogger(logName);
    }

    @Override
    public double click(Object marker, String message) {
        double result = chronograph.click(marker, message);
        logger.info("Click Time: {}, the nearest duration: {}({}), marker: [{}], message: {}", defaultDateFormat.format(new Date()), result, getUnit().name(), marker.toString(), message);
        return result;
    }

    @Override
    public double calculate(Object marker, String message) {
        double result = chronograph.calculate(marker, message);
        logger.info("Calculate Time: {}, duration: {}({}), marker: [{}], message: {}", defaultDateFormat.format(new Date()), result, getUnit().name(), marker.toString(), message);
        return result;
    }

    @Override
    public double calculate(Object marker, String message, int firstTime, int lastTime) {
        double result = chronograph.calculate(marker, message, firstTime, lastTime);
        logger.info("Calculate Time: {}, duration at ({} - {}): {}({}), marker: [{}], message: {}", defaultDateFormat.format(new Date()), firstTime, lastTime, result, getUnit().name(), marker.toString(), message);
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
