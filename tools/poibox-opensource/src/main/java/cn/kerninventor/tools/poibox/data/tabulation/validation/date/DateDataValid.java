package cn.kerninventor.tools.poibox.data.tabulation.validation.date;

import cn.kerninventor.tools.poibox.data.tabulation.validation.DataValid;
import cn.kerninventor.tools.poibox.data.tabulation.validation.CompareType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Kern
 * @date 2019/12/13 11:10
 */
@DataValid(dvBuilder = DateDataValidationBuilder.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DateDataValid {

    String NOW = "now()";

    String parseFormat() default "yyyy-MM-dd";

    String date() default "1900-01-01";

    String optionalDate() default "";

    String promptMessage() default "";

    String errorMessage() default "";

    CompareType compareType() default CompareType.GT;

}
