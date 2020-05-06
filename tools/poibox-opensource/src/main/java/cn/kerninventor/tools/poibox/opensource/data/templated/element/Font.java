package cn.kerninventor.tools.poibox.opensource.data.templated.element;

import cn.kerninventor.tools.poibox.opensource.style.enums.FontUnderline;
import cn.kerninventor.tools.poibox.opensource.style.Fonter;
import org.apache.poi.hssf.util.HSSFColor;

/**
 * @author Kern
 * @date 2020/4/9 12:29
 * @description
 */
public @interface Font {

    String fontName() default Fonter.DEF_NAME_TEXTPART;

    int fontSize() default Fonter.DEF_SIZE_TEXTPART;

    HSSFColor.HSSFColorPredefined color() default HSSFColor.HSSFColorPredefined.BLACK;

    boolean bold() default false;

    boolean italic() default false;

    boolean strikeout() default false;

    FontUnderline underline() default FontUnderline.NONE;
}