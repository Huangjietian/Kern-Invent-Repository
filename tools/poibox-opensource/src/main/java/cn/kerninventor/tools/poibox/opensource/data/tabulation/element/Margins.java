package cn.kerninventor.tools.poibox.opensource.data.tabulation.element;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Kern
 * @date 2020/5/21 10:48
 * @description
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Margins {

    int left();

    int top();

    int right();

    int bottom();
}
