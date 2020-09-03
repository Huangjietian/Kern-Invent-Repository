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
public @interface Line {

    Caption[] value() default {};

    /**
     * 风格订阅
     * @return
     */
    Style lineStyle() default @Style;

    /**
     * 是否位于底部
     * @return
     */
    boolean bottom() default false;
}
