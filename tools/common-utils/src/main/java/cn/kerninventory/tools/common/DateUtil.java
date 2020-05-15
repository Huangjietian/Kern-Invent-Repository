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
 * @author Kern
 * @date 2020/5/15 8:29
 * @description
 */
public class DateUtil {

    /**
     * 日期格式解析和格式化  ================================================================
     */

    public static Date parseDate(String date, String pattern) throws ParseException {
        return new SimpleDateFormat(pattern).parse(date);
    }

    public static <D extends TemporalAccessor> D parseTemporalAccessor(String date, String pattern, Class<D> dClass) {
        Objects.requireNonNull(dClass,"TemporalAccessor class cannot be null!");
        TemporalAccessor temporalAccessor = DateTimeFormatter.ofPattern(pattern).withZone(ZoneId.systemDefault()).parse(date);
        return asTemporalAccessor(temporalAccessor, dClass);
    }

    public static String format(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

    public static String format(Temporal temporal, String pattern) {
        return DateTimeFormatter.ofPattern(pattern).format(temporal);
    }

    public static int getYearOf(Date date) {
        return getTimeIntOf(date, Calendar.YEAR);
    }

    public static int getMonthOf(Date date) {
        return getTimeIntOf(date, Calendar.MONTH);
    }

    public static int getDayOfMonthOf(Date date) {
        return getTimeIntOf(date, Calendar.DAY_OF_MONTH);
    }

    public static int getDayOfWeekOf(Date date) {
        return getTimeIntOf(date, Calendar.DAY_OF_WEEK);
    }

    public static int getTimeIntOf(Date date, int timeIndex) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(timeIndex);
    }

    public static int getTimeIntOf(Date date, CalendarTimeEnum calendarTimeEnum) {
        return getTimeIntOf(date, calendarTimeEnum.getIndex());
    }

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
     * 日期比较  ================================================================
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

    public static Date earliest(Date... dates) {
        return Arrays.stream(dates).max((d1, d2) -> compare(d1, d2)).get();
    }

    public static Date latest(Date... dates) {
        return Arrays.stream(dates).min((d1, d2) -> compare(d1, d2)).get();
    }

    public static int compare(LocalDate t1, LocalDate t2) {
        if (t1.isBefore(t2)) {
            return 1;
        } else if (t1.isAfter(t2)) {
            return -1;
        } else {
            return 0;
        }
    }

    public static LocalDate earliest(LocalDate... dates) {
        return Arrays.stream(dates).max((d1, d2) -> compare(d1, d2)).get();
    }

    public static LocalDate latest(LocalDate... dates) {
        return Arrays.stream(dates).min((d1, d2) -> compare(d1, d2)).get();
    }

    public static int compare(LocalDateTime t1, LocalDateTime t2) {
        if (t1.isBefore(t2)) {
            return 1;
        } else if (t1.isAfter(t2)) {
            return -1;
        } else {
            return 0;
        }
    }

    public static LocalDateTime earliest(LocalDateTime... dates) {
        return Arrays.stream(dates).max((d1, d2) -> compare(d1, d2)).get();
    }

    public static LocalDateTime latest(LocalDateTime... dates) {
        return Arrays.stream(dates).min((d1, d2) -> compare(d1, d2)).get();
    }

    /**
     * 日期加减计算  ================================================================
     * <p>
     *     这里更推荐使用java.util.Date 类型的plus方法，因为这个类型的实现是非常完善的，
     *     如果你一定要使用java.time 包下的 Temporal plus方法，可能会因为类型的不匹配导致异常。
     * </p>
     */

    public static Date plusYear(Date source, int year) {
        return plus(source, Calendar.YEAR, year);
    }

    public static Date plusMonth(Date source, int month) {
        return plus(source, Calendar.MONTH, month);
    }

    public static Date plusDay(Date source, int day) {
        return plus(source, Calendar.DAY_OF_YEAR, day);
    }

    public static Date plusHour(Date source, int hour) {
        return plus(source, Calendar.HOUR, hour);
    }

    public static Date plusMinute(Date source, int minute) {
        return plus(source, Calendar.MINUTE, minute);
    }

    public static Date plus(Date source, int timeIndex, int increment) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(source);
        calendar.roll(timeIndex, increment);
        return calendar.getTime();
    }

    public static Date plus(Date source, CalendarTimeEnum calendarTimeEnum, int increment) {
        return plus(source, calendarTimeEnum.getIndex(), increment);
    }

    public static <T extends Temporal> T plusYear(T source, int year) {
        return plus(source, ChronoUnit.YEARS, year);
    }

    public static <T extends Temporal> T plusMonth(T source, int month) {
        return plus(source, ChronoUnit.MONTHS, month);
    }

    public static <T extends Temporal> T plusDay(T source, int day) {
        return plus(source, ChronoUnit.DAYS, day);
    }

    public static <T extends Temporal> T plusHour(T source, int hour) {
        return plus(source, ChronoUnit.HOURS, hour);
    }

    public static <T extends Temporal> T plusMinute(T source, int minute) {
        return plus(source, ChronoUnit.MINUTES, minute);
    }

    public static <T extends Temporal> T plus(T source, ChronoUnit chronoUnit, int increment) {
        return (T) source.plus(increment,chronoUnit);
    }


    /**
     * 日期对象格式转换  ================================================================
     */

    public static Date asDate(int year, int month, int day) {
        LocalDate localDate = LocalDate.of(year, month, day);
        Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public static Date asDate(int year, int month, int day, int hour, int minute, int seccond) {
        LocalDateTime localDateTime = LocalDateTime.of(year, month, day, hour, minute, seccond);
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public static Date asDate(LocalDate localDate) {
        Objects.requireNonNull(localDate, "LocalDate cannot be null!");
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date asDate(LocalDateTime localDateTime) {
        Objects.requireNonNull(localDateTime, "LocalDateTime cannot be null!");
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static <D extends TemporalAccessor> D asTemporalAccessor(Date date, Class<D> dClass) {
        Objects.requireNonNull(dClass,"TemporalAccessor class cannot be null!");
        ZonedDateTime zonedDateTime = Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault());
        return asTemporalAccessor(zonedDateTime, dClass);
    }

    /**
     * <p>
     *     这个方法必须遵循 TemporalAccessor 信息的完整性， 当从较为完整的信息转换为较为不完整的信息时，这个方法是可行的。
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
