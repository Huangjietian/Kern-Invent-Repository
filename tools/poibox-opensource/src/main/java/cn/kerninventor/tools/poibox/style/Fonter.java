package cn.kerninventor.tools.poibox.style;

import cn.kerninventor.tools.poibox.style.enums.FontColor;
import org.apache.poi.ss.usermodel.Font;

/**
 * @author Kern
 * @date 2019/10/29 19:52
 */
public interface Fonter {

    String DEF_NAME_HEADER = "Arial Black";

    String DEF_NAME_TEXTPART = "SimSun";

    int DEF_SIZE_HEADLINE = 14;

    int DEF_SIZE_TABLEHEADER = 12;

    int DEF_SIZE_TEXTPART = 10;

    int DEFAULT_FONT_HEIGHT_IN_POINTS = 12;

    FontProducer producer();

    Font simpleFont(String fontName, int fontSize);

    Font simpleFont(String fontName, int fontSize, FontColor fontColor);

    Font simpleFont(String fontName, int fontSize, boolean bold);

    Font generate(cn.kerninventor.tools.poibox.data.tabulation.element.Font font);

}
