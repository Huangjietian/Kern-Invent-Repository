package cn.kerninventor.tools.poibox.data.tabulation.validation.array;

import cn.kerninventor.tools.poibox.data.tabulation.validation.DataValid;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Kern
 * @date 2020/5/25 15:48
 * @description
 */
@DataValid
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnumExplicitListDataValid {

    Class<? extends EnumExplicitList> enumClass();

    String prompMessage() default "";

    String errorMessage() default "";

}
