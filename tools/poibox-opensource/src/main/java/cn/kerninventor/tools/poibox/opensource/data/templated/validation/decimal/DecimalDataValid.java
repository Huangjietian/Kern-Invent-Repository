package cn.kerninventor.tools.poibox.opensource.data.templated.validation.decimal;

import cn.kerninventor.tools.poibox.opensource.data.templated.validation.CompareType;
import cn.kerninventor.tools.poibox.opensource.data.templated.validation.DataValid;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Kern
 * @date 2019/12/13 15:18
 */
@DataValid
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DecimalDataValid {

    double value() default 0.00;

    double optionalVal() default -1.00;

    String prompMessage() default "";

    String errorMessage() default "";

    CompareType compareType() default CompareType.GT;
}
