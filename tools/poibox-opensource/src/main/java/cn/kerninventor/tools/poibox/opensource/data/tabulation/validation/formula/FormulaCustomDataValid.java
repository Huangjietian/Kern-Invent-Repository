package cn.kerninventor.tools.poibox.opensource.data.tabulation.validation.formula;

import cn.kerninventor.tools.poibox.opensource.data.tabulation.validation.DataValid;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Kern
 * @date 2020/3/11 11:07
 */
@DataValid
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FormulaCustomDataValid {

    String formula();

    String prompMessage() default "";

    String errorMessage() default "";

}
