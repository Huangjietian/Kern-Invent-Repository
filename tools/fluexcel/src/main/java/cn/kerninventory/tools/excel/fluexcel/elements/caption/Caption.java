package cn.kerninventory.tools.excel.fluexcel.elements.caption;

import cn.kerninventory.tools.excel.fluexcel.elements.style.Style;

import java.lang.annotation.*;

/**
 * <p>
 *     The title;Subtitles;instructions.
 * </p>
 *
 * @author Kern
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Caption {

    /**
     * 内容
     * @return
     */
    String value();

    /**
     * 风格订阅
     * @return
     */
    Style style() default @Style;

    /**
     * 起始列
     * @return
     */
    int colStart() default 0;

    /**
     * 结束列
     * @return
     */
    int colEnd() default Integer.MAX_VALUE;

    /**
     * 长度
     * @return
     */
    int length() default 0;

    /**
     * 占据的行数
     * @return
     */
    int height() default 1;

    /**
     * 是否位于底部
     * @return
     */
    boolean bottom() default false;
}
