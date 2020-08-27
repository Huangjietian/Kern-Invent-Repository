package cn.kerninventory.tools.excel.anno.element;

/**
 * <p>
 *     Table definition.
 * </p>
 *
 * @author Kern
 */
public @interface Tabulation {

    /**
     * 默认的sheet页名
     * @return
     */
    String value() default "sheet1";

    /**
     * 定义表头行行高
     * @return
     */
    float headRowHeight() default 15.0f;

    /**
     * 定义表体行行高
     * @return
     */
    float bodyRowHeight() default 15.0f;

    /**
     * 定义起始行
     * @return
     */
    int startRowIndex() default 0;

    /**
     * 定义起始列
     * @return
     */
    int startColumnIndex() default 0;

    /**
     * 定义表体配置有效行数
     * @return
     */
    int effectiveRows() default 20;

    /**
     * 定义最小列宽
     * @return
     */
    int minimumColumnsWidth() default 0;

    /**
     * 定义最大列宽
     * @return
     */
    int maximumColumnsWidth() default 255;
}
