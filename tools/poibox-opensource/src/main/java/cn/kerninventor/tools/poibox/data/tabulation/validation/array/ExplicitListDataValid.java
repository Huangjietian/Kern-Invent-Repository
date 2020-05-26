package cn.kerninventor.tools.poibox.data.tabulation.validation.array;

import cn.kerninventor.tools.poibox.data.tabulation.validation.DataValid;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Kern
 * @date 2020/5/6 9:36
 * @description
 */
@DataValid
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExplicitListDataValid {

    int ALLOWED_MAX_LIST_BYTES_LENGTH = 255;

    String[] list();

    String prompMessage() default "";

    String errorMessage() default "";

}
