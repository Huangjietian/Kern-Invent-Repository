package cn.kerninventor.tools.poibox.opensource.data.templated.element;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Kern
 * @date 2020/5/21 10:47
 * @description
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Palette {

    int red();

    int green();

    int bule();
}
