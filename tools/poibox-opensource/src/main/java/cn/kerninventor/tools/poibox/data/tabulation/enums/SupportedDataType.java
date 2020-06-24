package cn.kerninventor.tools.poibox.data.tabulation.enums;


import cn.kerninventor.tools.poibox.data.tabulation.writer.basic.CellsWriter;
import cn.kerninventor.tools.poibox.data.tabulation.writer.basic.CellsGeneralWriter;
import cn.kerninventor.tools.poibox.data.tabulation.writer.basic.CellsMergeColumnsWriter;
import cn.kerninventor.tools.poibox.exception.IllegalColumnConfigureException;
import cn.kerninventor.tools.poibox.exception.UnSupportedDataTypeException;

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

    public static boolean isSupportedType(Field field) {
        if (field.getType().isPrimitive()) {
            throw new IllegalColumnConfigureException("Basic data type is unsupported, please used wrapper type! Field： " + field.getName());
        }
        SupportedDataType[] types = SupportedDataType.values();
        for (SupportedDataType type : types) {
            if (type.clazz.isAssignableFrom(field.getType())) {
                return true;
            }
        }
        return false;
    }

    /**
     * The GeneralColWriter and MergeAbleColWriter provided by default support only some data types;
     * other data types specify custom implementations that implement the ColWriter interface.
     * If you need to query for the supported data type, check SupportedDataType.class.
     *
     * @param field
     * @param cellsWriter
     */
    public static void checkSupportability(Field field, CellsWriter cellsWriter) {
        boolean isSupportedType = SupportedDataType.isSupportedType(field);
        if (!isSupportedType && (cellsWriter instanceof CellsGeneralWriter || cellsWriter instanceof CellsMergeColumnsWriter)) {
            throw new UnSupportedDataTypeException("" +
                    "The Field data type is not supported when using the GeneralColWriter or MergeAbleColWriter!" +
                    System.lineSeparator() +
                    "Please check the enumeration values in SupportedDataType class!");
        }
    }

}
