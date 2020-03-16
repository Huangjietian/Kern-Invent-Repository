package cn.kerninventor.tools.poibox.data.utils;

import cn.kerninventor.tools.poibox.data.exception.IllegalFieldTypeException;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Title SupportedType
 * @ProjectName kerninventresp
 * @PackageName cn.kerninventor.tools.poibox.utils
 * @Author Kern
 * @Date 2020/3/11 16:23
 * @Description TODO
 */
public enum SupportedDataType {


    ByteT(Byte.class),
    ShortT(Short.class),
    IntegerT(Integer.class),
    LongT(Long.class),

    FloatT(Float.class),
    DoubleT(Double.class),
    BigDecimalT(BigDecimal.class),

    StringT(String.class),
    CharacterT(Character.class),

    DateT(java.util.Date.class),
    DateSqlT(java.sql.Date.class),
    TimeT(java.sql.Time.class),

    LocalDateT(LocalDate.class),
    LocalDateTimeT(LocalDateTime.class),

    BooleanT(Boolean.class),
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
            throw new IllegalFieldTypeException("Basic data type is unsupported, please used wrapper type! Field： " + field.getName());
        }
        if (!isSupportedType(field.getType())) {
            throw new IllegalFieldTypeException("Field type is unsupported! Field：" + field.getName() + System.lineSeparator() +
                    "List of supported data type please refer the enum: " + SupportedDataType.class);
        }
        return field;
    }
}
