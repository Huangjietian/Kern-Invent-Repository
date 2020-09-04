package cn.kerninventory.tools.excel.fluexcel.elements.caption;

import cn.kerninventory.tools.excel.fluexcel.elements.style.Style;

/**
 * <p>一句话描述</p>
 *
 * @author Kern
 */
public @interface MultiLine {

    Line[] value();

    /**
     * 风格订阅
     * @return
     */
    Style globalStyle() default @Style;

}
