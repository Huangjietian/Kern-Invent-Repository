package cn.kerninventor.tools.poibox.data.templated.enums;


import cn.kerninventor.tools.poibox.exception.IllegalColumnConfigureException;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Kern
 * @date 2020/3/11 16:23
 */
public enum SupportedDataType {

    ByteType(Byte.class),
    ShortType(Short.class),
    IntegerType(Integer.class),
    LongType(Long.class),

    FloatType(Float.class),
    DoubleType(Double.class),
    BigDecimalType(BigDecimal.class),

    StringType(String.class),
    CharacterType(Character.class),

    DateType(java.util.Date.class),
    DateSqlType(java.sql.Date.class),
    TimeType(java.sql.Time.class),

    LocalDateType(LocalDate.class),
    LocalDateTimeType(LocalDateTime.class),

    BooleanType(Boolean.class),
    ;

    private Class clazz;

    SupportedDataType(Class clazz) {
        this.clazz = clazz;
    }

    public static boolean isSupportedType(Class clazz) {
        SupportedDataType[] types = SupportedDataType.values();
        for (SupportedDataType type : types) {
            if (type.clazz.isAssignableFrom(clazz)) {
                return true;
            }
        }
        return false;
    }

    public static Field checkSupportability(Field field) {
        if (field.getType().isPrimitive()) {
            throw new IllegalColumnConfigureException("Basic data type is unsupported, please used wrapper type! Field： " + field.getName());
        }
        if (!isSupportedType(field.getType())) {
            throw new IllegalColumnConfigureException("Field type is unsupported! Field：" + field.getName() + System.lineSeparator() +
                    "List of supported data type please refer the enum: " + SupportedDataType.class);
        }
        return field;
    }
}
