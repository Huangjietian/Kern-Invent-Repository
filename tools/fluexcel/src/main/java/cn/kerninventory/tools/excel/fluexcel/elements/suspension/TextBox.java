package cn.kerninventory.tools.excel.fluexcel.elements.suspension;

import org.apache.poi.ss.usermodel.VerticalAlignment;

import java.lang.annotation.*;

/**
 * <p>
 *     Text box definition.
 * </p>
 * @author Kern
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TextBox {

    /**
     * 文本框的内容
     * @return
     */
    String[] value();

    /**
     * 文本框的锚坐标
     * @see Anchor
     * @return
     */
    Anchor anchorIndex();

    /**
     * 文本框垂直居中样式
     * @return
     */
    VerticalAlignment verticalAlignment() default VerticalAlignment.CENTER;

    /**
     * 文本框边缘留白
     * @see Margins
     * @return
     */
    Margins margins() default @Margins;

    /**
     * 文本框填充颜色
     * @return
     */
    Palette fillColor() default @Palette(red = 255, green = 255, blue = 255);

    /**
     * 文本框边框颜色
     * @return
     */
    Palette lineColor() default @Palette(red = 0, green = 0, blue = 0);
}
