package cn.kerninventor.tools.poibox.data.tabulation.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Kern
 * @date 2020/4/9 16:19
 * @description
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Range {

    int defaultVal = -1;

    int fistRow() default defaultVal;
    int firstCell() default defaultVal;
    int lastRow() default defaultVal;
    int lastCell() default defaultVal;

}
