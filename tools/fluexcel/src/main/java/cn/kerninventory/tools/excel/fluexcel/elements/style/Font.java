package cn.kerninventory.tools.excel.fluexcel.elements.style;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.FontUnderline;

import java.lang.annotation.*;

/**
 * <p>
 *     Font definition.
 * </p>
 * @author Kern
 */
@Documented
@Target(ElementType.TYPE_PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface Font {

    /**
     * 字体名称
     * @return
     */
    String fontName() default "SimSun";

    /**
     * 字体大小
     * @return
     */
    int fontSize() default 10;

    /**
     * 字体颜色
     * @return
     */
    HSSFColor.HSSFColorPredefined color() default HSSFColor.HSSFColorPredefined.BLACK;

    /**
     * 字体加粗
     * @return
     */
    boolean bold() default false;

    /**
     * 字体倾斜
     * @return
     */
    boolean italic() default false;

    /**
     * 字体删除线
     * @return
     */
    boolean strikeout() default false;

    /**
     * 字体下划线
     * @return
     */
    FontUnderline underline() default FontUnderline.NONE;
}
