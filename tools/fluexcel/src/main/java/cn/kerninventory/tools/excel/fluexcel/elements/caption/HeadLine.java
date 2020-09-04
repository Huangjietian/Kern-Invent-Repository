package cn.kerninventory.tools.excel.fluexcel.elements.caption;

import cn.kerninventory.tools.excel.fluexcel.elements.style.Font;
import cn.kerninventory.tools.excel.fluexcel.elements.style.Style;

/**
 * <p>一句话描述</p>
 *
 * @author Kern
 */
public @interface HeadLine {

    String value();

    Style style() default @Style(font = @Font(fontSize = 12, fontName = "Arial Black"));

    int rowNumber() default 1;

    float rowHeight() default 15.0f;

}
