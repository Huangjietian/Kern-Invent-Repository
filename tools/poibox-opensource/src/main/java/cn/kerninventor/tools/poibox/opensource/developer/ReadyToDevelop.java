package cn.kerninventor.tools.poibox.opensource.developer;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Kern
 * @date 2020/4/9 13:02
 * @description
 */
@Retention(RetentionPolicy.SOURCE)
public @interface ReadyToDevelop {

    String value() default "";
}
