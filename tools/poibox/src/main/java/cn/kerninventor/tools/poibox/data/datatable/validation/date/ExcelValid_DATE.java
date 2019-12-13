package cn.kerninventor.tools.poibox.data.datatable.validation.date;

import cn.kerninventor.tools.poibox.data.datatable.validation.CompareType;
import cn.kerninventor.tools.poibox.data.datatable.validation.ExcelValid;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Title: ExcelValid_DATE
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.data.datatable.validation
 * @Author Kern
 * @Date 2019/12/13 11:10
 * @Description: TODO
 */
@ExcelValid
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelValid_DATE {

    /**
     * date format pattern
     */
    String pattern() default "YYYY-MM-dd";

    /**
     * required date value depend on compareType
     */
    String date() default "1900-01-01";

    /**
     * this date attribution is optional, whether it makes sense or not depends on compareType attribution.
     */
    String optionalDate() default "";

    /**
     * Prompt message when a cell is selected
     * @return
     */
    String prompMessage() default "";

    /**
     * Prompt message when a constraint is violated
     * @return
     */
    String errorMessage() default "";

    /**
     * compare type , default greater than.
     */
    CompareType compareType() default CompareType.GT;

}
