package cn.kerninventor.tools.poibox.data.templatedtable.cellstyle;

import cn.kerninventor.tools.poibox.style.Fonter;
import cn.kerninventor.tools.poibox.style.enums.FontUnderline;

/**
 * @author Kern
 * @date 2020/4/9 12:29
 * @description
 */
public @interface Font {

    String fontName() default Fonter.DEF_NAME_TEXTPART;

    int fontSize() default Fonter.DEF_SIZE_TEXTPART;

    boolean bold() default false;

    boolean italic() default false;

    boolean strikeout() default false;

    FontUnderline underline() default FontUnderline.NONE;
}
