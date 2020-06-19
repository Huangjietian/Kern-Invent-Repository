package cn.kerninventor.tools.poibox.data.tabulation.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Kern
 * @date 2020/5/21 10:41
 * @description
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Anchor {

    int left() default 0;

    int top() default 0;

    int right() default 0;

    int bottom() default 0;

    int col1();

    int row1();

    int col2();

    int row2();
}
