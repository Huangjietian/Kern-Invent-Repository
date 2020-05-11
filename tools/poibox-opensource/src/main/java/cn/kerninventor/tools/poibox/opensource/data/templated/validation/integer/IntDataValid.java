package cn.kerninventor.tools.poibox.opensource.data.templated.validation.integer;

import cn.kerninventor.tools.poibox.opensource.data.templated.validation.CompareType;
import cn.kerninventor.tools.poibox.opensource.data.templated.validation.DataValid;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Kern
 * @date 2019/12/13 14:25
 */
@DataValid
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IntDataValid {

    int value() default 0;

    int optionalVal() default -1;

    String prompMessage() default "";

    String errorMessage() default "";

    CompareType compareType() default CompareType.GT;
}
