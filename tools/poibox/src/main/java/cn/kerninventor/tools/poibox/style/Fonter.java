package cn.kerninventor.tools.poibox.style;

import cn.kerninventor.tools.poibox.style.enums.FontColor;
import org.apache.poi.ss.usermodel.Font;

/**
 * @Author Kern
 * @Date 2019/10/29 19:52
 */
public interface Fonter {

    String DEF_NAME_HEADER = "黑体";

    String DEF_NAME_TEXTPART = "宋体";

    int DEF_SIZE_HEADLINE = 24;

    int DEF_SIZE_TABLEHEADER = 16;

    int DEF_SIZE_TEXTPART = 12;

    FontProducer producer();

    Font simpleFont(String fontName, int fontSize);

    Font simpleFont(String fontName, int fontSize, FontColor fontColor);

    Font simpleFont(String fontName, int fontSize, boolean bold);

    Font generate(cn.kerninventor.tools.poibox.data.templatedtable.element.Font font);

}
