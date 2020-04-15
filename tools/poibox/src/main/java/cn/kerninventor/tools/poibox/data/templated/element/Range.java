package cn.kerninventor.tools.poibox.data.templated.element;

/**
 * @author Kern
 * @date 2020/4/9 16:19
 * @description
 */
public @interface Range {

    int defaultVal = -1;

    int fistRow() default defaultVal;
    int firstCell() default defaultVal;
    int lastRow() default defaultVal;
    int lastCell() default defaultVal;

}
