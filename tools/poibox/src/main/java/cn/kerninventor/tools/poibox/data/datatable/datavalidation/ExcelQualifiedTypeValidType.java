package cn.kerninventor.tools.poibox.data.datatable.datavalidation;

import cn.kerninventor.tools.poibox.data.datatable.datavalidation.date.ExcelValidDate;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.decimal.ExcelValidDecimal;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.integer.ExcelValidInt;
import cn.kerninventor.tools.poibox.utils.DataTypeGroupUtil;

import java.lang.annotation.Annotation;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Title: ExcelValidType
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.data.datatable.datavalidation
 * @Author Kern
 * @Date 2019/12/18 10:00
 * @Description: TODO
 */
public enum ExcelQualifiedTypeValidType {

    DATE(),
    DECIMAL(),
    INTEGER()
    ;

    @ExcelValidInt(value = Integer.MIN_VALUE, optionalVal = Integer.MAX_VALUE, compareType = CompareType.BET)
    private Integer defInt;

    @ExcelValidDecimal(value = - Double.MAX_VALUE, optionalVal = Double.MAX_VALUE, compareType = CompareType.BET)
    private BigDecimal defDecimal;

    @ExcelValidDate
    private Date defDate;

    private static Annotation getDefInt() {
        try {
            return ExcelQualifiedTypeValidType.class.getDeclaredField("defInt").getDeclaredAnnotation(ExcelValidInt.class);
        } catch (NoSuchFieldException e) {
            throw new IllegalArgumentException();
        }
    }

    private static Annotation getDefDecimal() {
        try {
            return ExcelQualifiedTypeValidType.class.getDeclaredField("defDecimal").getDeclaredAnnotation(ExcelValidDecimal.class);
        } catch (NoSuchFieldException e) {
            throw new IllegalArgumentException();
        }
    }

    private static Annotation getDefDate() {
        try {
            return ExcelQualifiedTypeValidType.class.getDeclaredField("defDate").getDeclaredAnnotation(ExcelValidDate.class);
        } catch (NoSuchFieldException e) {
            throw new IllegalArgumentException();
        }
    }

    public static DataValidBuilder getQualifiedTypeValidHandler(Class type) {
        if (DataTypeGroupUtil.isMemberOfIntType(type)) {
            return DataValidBuilder.getInstance(ExcelQualifiedTypeValidType.getDefInt());
        } else if (DataTypeGroupUtil.isMemberOfNumberType(type)){
            return DataValidBuilder.getInstance(ExcelQualifiedTypeValidType.getDefDecimal());
        } else if (DataTypeGroupUtil.isMemberOfDateType(type)) {
            return DataValidBuilder.getInstance(ExcelQualifiedTypeValidType.getDefDate());
        } else {
            return (p, a, s) -> {
                /**
                 * TO DO NOTHING
                 */
            };
        }
    }

 }