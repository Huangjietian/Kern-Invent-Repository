package cn.kerninventor.tools.poibox.data.templatedtable.datavalidation;

import cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.date.DateDataValid;
import cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.decimal.DecimalDataValid;
import cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.integer.IntDataValid;
import cn.kerninventor.tools.poibox.utils.DataTypeGroupUtil;

import java.lang.annotation.Annotation;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author Kern
 * @Date 2019/12/18 10:00
 */
@Deprecated
public enum ExcelQualifiedTypeValidType {

    DATE(),
    DECIMAL(),
    INTEGER()
    ;

    @IntDataValid(value = Integer.MIN_VALUE, optionalVal = Integer.MAX_VALUE, compareType = CompareType.BET)
    private Integer defInt;

    @DecimalDataValid(value = - Double.MAX_VALUE, optionalVal = Double.MAX_VALUE, compareType = CompareType.BET)
    private BigDecimal defDecimal;

    @DateDataValid
    private Date defDate;

    private static Annotation getDefInt() {
        try {
            return ExcelQualifiedTypeValidType.class.getDeclaredField("defInt").getDeclaredAnnotation(IntDataValid.class);
        } catch (NoSuchFieldException e) {
            throw new IllegalArgumentException();
        }
    }

    private static Annotation getDefDecimal() {
        try {
            return ExcelQualifiedTypeValidType.class.getDeclaredField("defDecimal").getDeclaredAnnotation(DecimalDataValid.class);
        } catch (NoSuchFieldException e) {
            throw new IllegalArgumentException();
        }
    }

    private static Annotation getDefDate() {
        try {
            return ExcelQualifiedTypeValidType.class.getDeclaredField("defDate").getDeclaredAnnotation(DateDataValid.class);
        } catch (NoSuchFieldException e) {
            throw new IllegalArgumentException();
        }
    }

    public static DataValidationBuilder getQualifiedTypeValidHandler(Class type) {
        if (DataTypeGroupUtil.isMemberOfIntType(type)) {
            return DataValidationBuilderFactory.getInstance(ExcelQualifiedTypeValidType.getDefInt());
        } else if (DataTypeGroupUtil.isMemberOfNumberType(type)){
            return DataValidationBuilderFactory.getInstance(ExcelQualifiedTypeValidType.getDefDecimal());
        } else if (DataTypeGroupUtil.isMemberOfDateType(type)) {
            return DataValidationBuilderFactory.getInstance(ExcelQualifiedTypeValidType.getDefDate());
        } else {
            return (p, a, s) -> {
                /**
                 * TO DO NOTHING
                 */
            };
        }
    }

 }