package cn.kerninventory.tools.common.chronograph;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <h1>中文注释</h1>
 * <p>
 *     具备SLF4J日志输出功能的代码运行时间计时器, 将所计算值由当前系统的SLF4J日志框架进行输出<br/>
 *     注： 系统需要引入SLF4J日志API框架和及实现
 * </p>
 * @author Kern
 * @version 1.0
 */
public class LogApiSlf4jRuntimeChronograph implements Chronograph {

    private SimpleDateFormat defaultDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:sss");
    private Logger logger;
    private Chronograph chronograph;

    public LogApiSlf4jRuntimeChronograph(Chronograph chronograph, String logName) {
        this.chronograph = chronograph;
        this.logger = LoggerFactory.getLogger(logName);
    }

    /**
     * 参考 {@link Chronograph}
     * @param marker
     * @param message
     * @return
     */
    @Override
    public double click(Object marker, String message) {
        double result = chronograph.click(marker, message);
        logger.info("Click Time: {}, the nearest duration: {}({}), marker: [{}], message: {}", defaultDateFormat.format(new Date()), result, getUnit().name(), marker.toString(), message);
        return result;
    }

    /**
     * 参考 {@link Chronograph}
     * @param marker
     * @param message
     * @return
     */
    @Override
    public double calculate(Object marker, String message) {
        double result = chronograph.calculate(marker, message);
        logger.info("Calculate Time: {}, duration: {}({}), marker: [{}], message: {}", defaultDateFormat.format(new Date()), result, getUnit().name(), marker.toString(), message);
        return result;
    }

    /**
     * 参考 {@link Chronograph}
     * @param marker
     * @param message
     * @param firstTime
     * @param lastTime
     * @return
     */
    @Override
    public double calculate(Object marker, String message, int firstTime, int lastTime) {
        double result = chronograph.calculate(marker, message, firstTime, lastTime);
        logger.info("Calculate Time: {}, duration at ({} - {}): {}({}), marker: [{}], message: {}", defaultDateFormat.format(new Date()), firstTime, lastTime, result, getUnit().name(), marker.toString(), message);
        return result;
    }

    /**
     * 参考 {@link Chronograph}
     * @param marker
     * @return
     */
    @Override
    public void reset(Object marker) {
        chronograph.reset(marker);
    }

    /**
     * 参考 {@link Chronograph}
     * @return
     */
    @Override
    public RuntimeUnit getUnit() {
        return chronograph.getUnit();
    }
}
