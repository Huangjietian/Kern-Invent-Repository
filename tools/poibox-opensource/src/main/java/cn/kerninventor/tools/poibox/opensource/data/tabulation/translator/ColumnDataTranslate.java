package cn.kerninventor.tools.poibox.opensource.data.tabulation.translator;

import java.lang.annotation.*;

/**
 * @author Kern
 * @date 2020/5/19 17:13
 * @description
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ColumnDataTranslate {

    String translator();

    String tag() default "";

    UnmatchStrategy unmatchStrategy() default UnmatchStrategy.CONSOLE;

    boolean open() default true;

}
