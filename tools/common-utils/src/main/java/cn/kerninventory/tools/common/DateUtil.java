package cn.kerninventory.tools.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * <h1>中文注释</h1>
 * <p>
 *     日期工具类， 封装了java.util.Date类 及java.time包下的日期类的一些公共方法,<br/>
 *     如果你需要进行日期的比较，加减，转换，格式化等编码，可以尝试使用该工具包
 * </p>
 * @author Kern
 * @version 1.0
 */
public class DateUtil {

    /**
     * <p>
     *     字符串解析成Date
     * </p>
     * @param date
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static Date parseDate(String date, String pattern) throws ParseException {
        return new SimpleDateFormat(pattern).parse(date);
    }

    /**
     * <P>
     *     字符串解析成指定{@code Class<D extends {@link TemporalAccessor}>类型的{@link TemporalAccessor}类}
     * </P>
     * @param date
     * @param pattern
     * @param dClass
     * @param <D>
     * @return
     */
    public static <D extends TemporalAccessor> D parseTemporalAccessor(String date, String pattern, Class<D> dClass) {
        Objects.requireNonNull(dClass,"TemporalAccessor class cannot be null!");
        TemporalAccessor temporalAccessor = DateTimeFormatter.ofPattern(pattern).withZone(ZoneId.systemDefault()).parse(date);
        return asTemporalAccessor(temporalAccessor, dClass);
    }

    /**
     * <P>
     *     把Date格式化为指定格式的字符串
     * </P>
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * <P>
     *     把Temporal格式化为指定格式的字符串
     * </P>
     * @param temporal
     * @param pattern
     * @return
     */
    public static String format(Temporal temporal, String pattern) {
        return DateTimeFormatter.ofPattern(pattern).format(temporal);
    }

    /**
     * <P>
     *     从日期中获取年份
     * </P>
     * @param date
     * @return
     */
    public static int getYearOf(Date date) {
        return getTimeIntOf(date, Calendar.YEAR);
    }

    /**
     * <p>
     *     从日期中获取月份
     * </p>
     * @param date
     * @return
     */
    public static int getMonthOf(Date date) {
        return getTimeIntOf(date, Calendar.MONTH);
    }

    /**
     * <P>
     *     从日期中获取月份中的天数
     * </P>
     * @param date
     * @return
     */
    public static int getDayOfMonthOf(Date date) {
        return getTimeIntOf(date, Calendar.DAY_OF_MONTH);
    }

    /**
     * <P>
     *     从日期中获取周的天数
     * </P>
     * @param date
     * @return
     */
    public static int getDayOfWeekOf(Date date) {
        return getTimeIntOf(date, Calendar.DAY_OF_WEEK);
    }

    /**
     * <P>
     *     根据timeIndex获取指定日期的指定表示形式<br/>
     *     timeIndex 参考 {@link Calendar} 的常量枚举
     * </P>
     * @param date
     * @param timeIndex
     * @return
     */
    public static int getTimeIntOf(Date date, int timeIndex) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(timeIndex);
    }

    /**
     * <P>
     *     根据calendarTimeEnum获取指定日期的指定表示形式<br/>
     *     calendarTimeEnum 参考 {@link CalendarTimeEnum}
     * </P>
     * @param date
     * @param calendarTimeEnum
     * @return
     */
    public static int getTimeIntOf(Date date, CalendarTimeEnum calendarTimeEnum) {
        return getTimeIntOf(date, calendarTimeEnum.getIndex());
    }

    /**
     * <P>
     *     Calendar仅提供了包含int参数的接口<br/>
     *     为了让使用者更清楚参数的含义，copy了Calendar的常量作枚举类。
     * </P>
     */
    public enum CalendarTimeEnum {

        ERA(0),
        YEAR(1),
        MONTH(2),
        WEEK_OF_YEAR(3),
        WEEK_OF_MONTH(4),
        DATE(5),
        DAY_OF_MONTH(5),
        DAY_OF_YEAR(6),
        DAY_OF_WEEK(7),
        DAY_OF_WEEK_IN_MONTH(8),
        AM_PM(9),
        HOUR(10),
        HOUR_OF_DAY(11),
        MINUTE(12),
        SECOND(13),
        MILLISECOND(14),
        ZONE_OFFSET(15),
        DST_OFFSET(16),
        FIELD_COUNT(17),
        ;

        private int index;

        CalendarTimeEnum(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }
    }

    /**
     * <P>
     *     比较两个日期的大小<br/>
     *     返回int值为{0，1，-1} 参考{@link java.util.Comparator} || {@link Comparable}
     * </P>
     * @param date1
     * @param date2
     * @return
     */
    public static int compare(Date date1, Date date2) {
        if (date1.before(date2)) {
            return 1;
        } else if (date1.after(date2)) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * <P>
     *     获取一个日期数组中最早的日期
     * </P>
     * @param dates
     * @return
     */
    public static Date earliest(Date... dates) {
        return Arrays.stream(dates).max((d1, d2) -> compare(d1, d2)).get();
    }

    /**
     * <P>
     *     获取一个日期数组中最晚的日期
     * </P>
     * @param dates
     * @return
     */
    public static Date latest(Date... dates) {
        return Arrays.stream(dates).min((d1, d2) -> compare(d1, d2)).get();
    }

    /**
     * <P>
     *     比较两个日期的大小<br/>
     *     返回int值为{0，1，-1} 参考{@link java.util.Comparator} || {@link Comparable}
     * </P>
     * @param t1
     * @param t2
     * @return
     */
    public static int compare(LocalDate t1, LocalDate t2) {
        if (t1.isBefore(t2)) {
            return 1;
        } else if (t1.isAfter(t2)) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * <P>
     *     获取一个日期数组中最早的日期
     * </P>
     * @param dates
     * @return
     */
    public static LocalDate earliest(LocalDate... dates) {
        return Arrays.stream(dates).max((d1, d2) -> compare(d1, d2)).get();
    }

    /**
     * <P>
     *     获取一个日期数组中最晚的日期
     * </P>
     * @param dates
     * @return
     */
    public static LocalDate latest(LocalDate... dates) {
        return Arrays.stream(dates).min((d1, d2) -> compare(d1, d2)).get();
    }

    /**
     * <P>
     *     比较两个日期的大小<br/>
     *     返回int值为{0，1，-1} 参考{@link java.util.Comparator} || {@link Comparable}
     * </P>
     * @param t1
     * @param t2
     * @return
     */
    public static int compare(LocalDateTime t1, LocalDateTime t2) {
        if (t1.isBefore(t2)) {
            return 1;
        } else if (t1.isAfter(t2)) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * <P>
     *     获取一个日期数组中最早的日期
     * </P>
     * @param dates
     * @return
     */
    public static LocalDateTime earliest(LocalDateTime... dates) {
        return Arrays.stream(dates).max((d1, d2) -> compare(d1, d2)).get();
    }

    /**
     * <P>
     *     获取一个日期数组中最晚的日期
     * </P>
     * @param dates
     * @return
     */
    public static LocalDateTime latest(LocalDateTime... dates) {
        return Arrays.stream(dates).min((d1, d2) -> compare(d1, d2)).get();
    }

    /**
     * <p>
     *     在{@link Date}类的一个实例上增加指定整数的年份
     * </p>
     * @param source
     * @param year
     * @return Date 返回计算后的日期
     */
    public static Date plusYear(Date source, int year) {
        return plus(source, Calendar.YEAR, year);
    }

    /**
     * <p>
     *     在{@link Date}类的一个实例上增加指定整数的月份
     * </p>
     * @param source
     * @param month
     * @return Date 返回计算后的日期
     */
    public static Date plusMonth(Date source, int month) {
        return plus(source, Calendar.MONTH, month);
    }

    /**
     * <p>
     *     在{@link Date}类的一个实例上增加指定整数的天数
     * </p>
     * @param source
     * @param day
     * @return Date 返回计算后的日期
     */
    public static Date plusDay(Date source, int day) {
        return plus(source, Calendar.DAY_OF_YEAR, day);
    }

    /**
     * <p>
     *     在{@link Date}类的一个实例上增加指定整数的小时
     * </p>
     * @param source
     * @param hour
     * @return Date 返回计算后的日期
     */
    public static Date plusHour(Date source, int hour) {
        return plus(source, Calendar.HOUR, hour);
    }

    /**
     * <p>
     *     在{@link Date}类的一个实例上增加指定整数的分钟数
     * </p>
     * @param source
     * @param minute
     * @return Date 返回计算后的日期
     */
    public static Date plusMinute(Date source, int minute) {
        return plus(source, Calendar.MINUTE, minute);
    }

    /**
     * <p>
     *     在{@link Date}类的一个实例上增加指定整数的日期,<br/>
     *     这个日期的单元可以通过timeIndex指定，参考{@link Calendar}的常量枚举
     * </p>
     * @param source 源日期
     * @param timeIndex {@link Calendar}的常量枚举
     * @param increment 增量
     * @return Date 返回计算后的日期
     */
    public static Date plus(Date source, int timeIndex, int increment) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(source);
        calendar.roll(timeIndex, increment);
        return calendar.getTime();
    }

    /**
     * <p>
     *     在{@link Date}类的一个实例上增加指定整数的日期,<br/>
     *     这个日期的单元可以通过枚举类{@link CalendarTimeEnum}指定
     * </p>
     * @param source 源日期
     * @param calendarTimeEnum {@link CalendarTimeEnum}
     * @param increment 增量
     * @return Date 返回计算后的日期
     */
    public static Date plus(Date source, CalendarTimeEnum calendarTimeEnum, int increment) {
        return plus(source, calendarTimeEnum.getIndex(), increment);
    }

    /**
     * <p>
     *     为{@link java.time}包下的日期实例来增加指定整数的年份
     * </p>
     * <p>
     *     这里更推荐使用java.util.Date 类型的plus**方法，因为这个类型的实现是非常完善的，<br/>
     *     如果你一定要使用java.time 包下的 Temporal plus方法，可能会因为类型的不匹配导致异常。<br/>
     *     例如你把一个{@link Month}类传入plusYear()方法，将导致异常。因为Month类本身不支持plus year的操作。
     * </p>
     * @param source
     * @param year
     * @param <T>
     * @return T 返回计算后的日期
     */
    public static <T extends Temporal> T plusYear(T source, int year) {
        return plus(source, ChronoUnit.YEARS, year);
    }

    /**
     * <p>
     *     为{@link java.time}包下的日期实例来增加指定整数的月份
     * </p>
     * <p>
     *     这里更推荐使用java.util.Date 类型的plus**方法，因为这个类型的实现是非常完善的，<br/>
     *     如果你一定要使用java.time 包下的 Temporal plus方法，可能会因为类型的不匹配导致异常。<br/>
     *     例如你把一个{@link Month}类传入plusYear()方法，将导致异常。因为Month类本身不支持plus year的操作。
     * </p>
     * @param source
     * @param month
     * @param <T>
     * @return T 返回计算后的日期
     */
    public static <T extends Temporal> T plusMonth(T source, int month) {
        return plus(source, ChronoUnit.MONTHS, month);
    }

    /**
     * <p>
     *     为{@link java.time}包下的日期实例来增加指定整数的天数
     * </p>
     * <p>
     *     这里更推荐使用java.util.Date 类型的plus**方法，因为这个类型的实现是非常完善的，<br/>
     *     如果你一定要使用java.time 包下的 Temporal plus方法，可能会因为类型的不匹配导致异常。<br/>
     *     例如你把一个{@link Month}类传入plusYear()方法，将导致异常。因为Month类本身不支持plus year的操作。
     * </p>
     * @param source
     * @param day
     * @param <T>
     * @return T 返回计算后的日期
     */
    public static <T extends Temporal> T plusDay(T source, int day) {
        return plus(source, ChronoUnit.DAYS, day);
    }

    /**
     * <p>
     *     为{@link java.time}包下的日期实例来增加指定整数的小时数
     * </p>
     * <p>
     *     这里更推荐使用java.util.Date 类型的plus**方法，因为这个类型的实现是非常完善的，<br/>
     *     如果你一定要使用java.time 包下的 Temporal plus方法，可能会因为类型的不匹配导致异常。<br/>
     *     例如你把一个{@link Month}类传入plusYear()方法，将导致异常。因为Month类本身不支持plus year的操作。
     * </p>
     * @param source
     * @param hour
     * @param <T>
     * @return T 返回计算后的日期
     */
    public static <T extends Temporal> T plusHour(T source, int hour) {
        return plus(source, ChronoUnit.HOURS, hour);
    }

    /**
     * <p>
     *     为{@link java.time}包下的日期实例来增加指定整数的分钟数
     * </p>
     * <p>
     *     这里更推荐使用java.util.Date 类型的plus**方法，因为这个类型的实现是非常完善的，<br/>
     *     如果你一定要使用java.time 包下的 Temporal plus方法，可能会因为类型的不匹配导致异常。<br/>
     *     例如你把一个{@link Month}类传入plusYear()方法，将导致异常。因为Month类本身不支持plus year的操作。
     * </p>
     * @param source
     * @param minute
     * @param <T>
     * @return T 返回计算后的日期
     */
    public static <T extends Temporal> T plusMinute(T source, int minute) {
        return plus(source, ChronoUnit.MINUTES, minute);
    }

    /**
     * <p>
     *     为{@link java.time}包下的日期实例来增加指定整数指定日期单位{@link ChronoUnit}的日期
     * </p>
     * <p>
     *     这里更推荐使用java.util.Date 类型的plus**方法，因为这个类型的实现是非常完善的，<br/>
     *     如果你一定要使用java.time 包下的 Temporal plus方法，可能会因为类型的不匹配导致异常。<br/>
     *     例如你把一个{@link Month}类传入plusYear()方法，将导致异常。因为Month类本身不支持plus year的操作。
     * </p>
     * @param source
     * @param chronoUnit
     * @param increment
     * @param <T>
     * @return T 返回计算后的日期
     */
    public static <T extends Temporal> T plus(T source, ChronoUnit chronoUnit, int increment) {
        return (T) source.plus(increment,chronoUnit);
    }

    /**
     * <p>
     *     通过输入整数型的 年月份 参数获得一个{@link Date}类的实例
     * </p>
     * @param year
     * @param month
     * @param day
     * @return Date
     */
    public static Date asDate(int year, int month, int day) {
        LocalDate localDate = LocalDate.of(year, month, day);
        Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    /**
     * <p>
     *     通过输入整数型的 年月份小时分钟秒 参数获得一个{@link Date}类的实例
     * </p>
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @param seccond
     * @return Date
     */
    public static Date asDate(int year, int month, int day, int hour, int minute, int seccond) {
        LocalDateTime localDateTime = LocalDateTime.of(year, month, day, hour, minute, seccond);
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    /**
     * <p>
     *     通过{@link LocalDate}类实例获得一个等同日期值的{@link Date}类实例
     * </p>
     * @param localDate
     * @return Date
     */
    public static Date asDate(LocalDate localDate) {
        Objects.requireNonNull(localDate, "LocalDate cannot be null!");
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * <p>
     *     通过{@link LocalDateTime}类实例获得一个等同日期值的{@link Date}类实例
     * </p>
     * @param localDateTime
     * @return Date
     */
    public static Date asDate(LocalDateTime localDateTime) {
        Objects.requireNonNull(localDateTime, "LocalDateTime cannot be null!");
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * <p>
     *     通过{@link Date}类实例获得一个等同日期值的{@link TemporalAccessor}类实例
     * </p>
     * @param date 源日期
     * @param dClass TemporalAccessor的子类类型：LocalDateTime LocalDate Year Month 等的class参数
     * @return D 参数dClass类型的实例
     */
    public static <D extends TemporalAccessor> D asTemporalAccessor(Date date, Class<D> dClass) {
        Objects.requireNonNull(dClass,"TemporalAccessor class cannot be null!");
        ZonedDateTime zonedDateTime = Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault());
        return asTemporalAccessor(zonedDateTime, dClass);
    }

    /**
     * <p>
     *     转换java.time包下的日期类型的通用接口。实际业务开发中如果确定了日期类型，建议参考该方法中的实现，直接进行转换。<br/>
     *     例如你需要把一个{@link LocalDateTime}实例转换为一个{@link Year}实例的话，<br/>
     *     可以参考该方法，直接调用其中的 {@code Year.from(localDatetime)},可以避免if判断
     * </p>
     * <p>
     *     这个方法必须遵循 TemporalAccessor 信息的完整性， 当从较为完整的信息转换为较为不完整的信息时，这个方法是可行的。<br/>
     *     反之则会报出异常。
     * </p>
     *
     *
     * @param temporalAccessor
     * @param dClass
     * @param <D>
     * @return
     */
    private static <D extends TemporalAccessor> D asTemporalAccessor(TemporalAccessor temporalAccessor, Class<D> dClass) {
        if (dClass == Year.class) {
            temporalAccessor = Year.from(temporalAccessor);
        } else if (dClass == Month.class) {
            temporalAccessor = Month.from(temporalAccessor);
        } else if (dClass == YearMonth.class) {
            temporalAccessor =  YearMonth.from(temporalAccessor);
        } else if (dClass == MonthDay.class) {
            temporalAccessor = MonthDay.from(temporalAccessor);
        } else if (dClass == DayOfWeek.class) {
            temporalAccessor = DayOfWeek.from(temporalAccessor);
        } else if (dClass == LocalDate.class) {
            temporalAccessor = LocalDate.from(temporalAccessor);
        } else if (dClass == LocalDateTime.class) {
            temporalAccessor = LocalDateTime.from(temporalAccessor);
        } else {
            throw new DateTimeException("Unsupported type: " + dClass);
        }
        return (D) temporalAccessor;
    }

}
