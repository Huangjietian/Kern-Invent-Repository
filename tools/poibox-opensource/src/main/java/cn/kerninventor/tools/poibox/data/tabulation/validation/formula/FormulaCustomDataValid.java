package cn.kerninventor.tools.poibox.data.tabulation.validation.formula;

import cn.kerninventor.tools.poibox.data.tabulation.validation.DataValid;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Kern
 * @date 2020/3/11 11:07
 */
@DataValid(dvBuilder = FormulaCustomDataValidationBuilder.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FormulaCustomDataValid {

    String formula();

    String promptMessage() default "";

    String errorMessage() default "";

}
