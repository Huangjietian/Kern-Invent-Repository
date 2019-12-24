package cn.kerninventor.tools.poibox.data.datatable.datavalidation;

import cn.kerninventor.tools.poibox.data.datatable.datavalidation.date.ExcelValid_DATE;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.decimal.ExcelValid_DECIMAL;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.integer.ExcelValid_INT;

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

    @ExcelValid_INT
    private Integer defInt;

    @ExcelValid_DECIMAL
    private BigDecimal defDecimal;

    @ExcelValid_DATE
    private Date defDate;

    public static Annotation getDefInt() {
        try {
            return ExcelQualifiedTypeValidType.class.getDeclaredField("defInt").getDeclaredAnnotation(ExcelValid_INT.class);
        } catch (NoSuchFieldException e) {
            throw new IllegalArgumentException();
        }
    }

    public static Annotation getDefDecimal() {
        try {
            return ExcelQualifiedTypeValidType.class.getDeclaredField("defDecimal").getDeclaredAnnotation(ExcelValid_DECIMAL.class);
        } catch (NoSuchFieldException e) {
            throw new IllegalArgumentException();
        }
    }

    public static Annotation getDefDate() {
        try {
            return ExcelQualifiedTypeValidType.class.getDeclaredField("defDate").getDeclaredAnnotation(ExcelValid_DATE.class);
        } catch (NoSuchFieldException e) {
            throw new IllegalArgumentException();
        }
    }
}
