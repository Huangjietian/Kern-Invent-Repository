package cn.kerninventor.tools.poibox.data.templatedtable.element;

import cn.kerninventor.tools.poibox.developer.Postive;

/**
 * @author Kern
 * @date 2020/4/9 16:19
 * @description
 */
public @interface Range {

    int defaultVal = -1;

    @Postive
    int fistRow() default defaultVal;
    @Postive
    int firstCell() default defaultVal;
    @Postive
    int lastRow() default defaultVal;
    @Postive
    int lastCell() default defaultVal;

}
