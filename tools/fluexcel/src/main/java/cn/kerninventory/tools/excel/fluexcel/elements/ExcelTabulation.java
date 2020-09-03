package cn.kerninventory.tools.excel.fluexcel.elements;

import java.lang.annotation.*;

/**
 * <p>
 *     Table bean configuration.
 * </p>
 *
 * @author Kern
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelTabulation {

    /**
     * 定义表头行行高
     * @return
     */
    float theadRowHeight() default 15.0f;

    /**
     * 定义表体行行高
     * @return
     */
    float tbodyRowHeight() default 15.0f;

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
