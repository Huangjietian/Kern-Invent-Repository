package cn.kerninventor.tools.poibox.opensource.developer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Kern
 * @date 2020/4/12 22:02
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
public @interface SealingVersion {

    double version();

    String description() default "";

    String zh_description() default "";

    String possible() default "";
}
