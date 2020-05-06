package cn.kerninventor.tools.poibox.opensource.data.templated.validation.array;

import cn.kerninventor.tools.poibox.opensource.data.templated.validation.DataValid;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Kern
 * @date 2020/4/15 12:23
 */
@DataValid
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FormulaListDataValid {

    String NAME_PRIFIIX = "NAME_";

    String value();

    String prompMessage() default "";

    String errorMessage() default "";

}
