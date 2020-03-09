package cn.kerninventor.tools.poibox.data.datatable.datavalidation.date;

import cn.kerninventor.tools.poibox.data.datatable.datavalidation.CompareType;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.ExcelValid;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.text.SimpleDateFormat;

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

    String NOW = "now()";

    String parseFormat() default "yyyy-MM-dd";

    /**
     * required date value depend on compareType, you can set "now()" to represent the current time.
     */
    String date() default "1900-01-01";

    /**
     * this date attribution is optional, whether it makes sense or not depends on compareType attribution. you can set "now()" to represent the current time.
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
