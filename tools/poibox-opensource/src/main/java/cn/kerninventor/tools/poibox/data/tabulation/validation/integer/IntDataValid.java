package cn.kerninventor.tools.poibox.data.tabulation.validation.integer;

import cn.kerninventor.tools.poibox.data.tabulation.validation.CompareType;
import cn.kerninventor.tools.poibox.data.tabulation.validation.DataValid;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Kern
 * @date 2019/12/13 14:25
 */
@DataValid(dvBuilder = IntDataValidationBuilder.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IntDataValid {

    int value() default 0;

    int optionalVal() default -1;

    String promptMessage() default "";

    String errorMessage() default "";

    CompareType compareType() default CompareType.GT;
}
