package cn.kerninventory.tools.excel.fluexcel.elements.style;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import java.lang.annotation.*;

/**
 * <p>
 *     Style definition.
 * </p>
 * @author Kern
 */
@Documented
@Target(ElementType.TYPE_PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface Style {

    /**
     * 风格字体
     * @see Font
     * @return
     */
    Font font() default @Font;

    /**
     * 边框风格
     * @return
     */
    BorderStyle borderStyle() default BorderStyle.THIN;

    /**
     * 边框颜色
     * @return
     */
    HSSFColor.HSSFColorPredefined borderColor() default HSSFColor.HSSFColorPredefined.BLACK;

    /**
     * 填充样式
     * @return
     */
    FillPatternType fillType() default FillPatternType.SOLID_FOREGROUND;

    /**
     * 前景颜色
     * @return
     */
    HSSFColor.HSSFColorPredefined fillColor() default HSSFColor.HSSFColorPredefined.AUTOMATIC;

    /**
     * 垂直居中样式
     * @return
     */
    VerticalAlignment verticalAlignment() default VerticalAlignment.CENTER;

    /**
     * 水平居中样式
     * @return
     */
    HorizontalAlignment alignment() default HorizontalAlignment.CENTER;

    /**
     * 自动换行
     * @return
     */
    boolean wrapText() default false;

    /**
     * 风格锁
     * @return
     */
    boolean locked() default false;

    /**
     * 缩进
     * @return
     */
    int indention() default 0;

    /**
     * 隐藏
     * @return
     */
    boolean hidden() default false;

}
