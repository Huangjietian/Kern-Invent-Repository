package cn.kerninventor.tools.poibox.style;

import cn.kerninventor.tools.poibox.elements.FonterElements;
import org.apache.poi.ss.usermodel.Font;

/**
 * @Title: POIFonter
 * @ProjectName tools
 * @Version 1.1.0.RELEASE
 * @Description: TODO
 * @Author Kern
 * @Date 2019/10/29 19:52
 */
public interface POIFonter {

    String DEFAULT_KEY = "DEFAULT_FONT";
    String NAME_HEADER = "黑体";
    String NAME_TEXTPART = "宋体";
    int SIZE_HEADLINE = 24;
    int SIZE_TABLEHEADER = 16;
    int SIZE_TEXTPART = 12;

    POIFontProducer produce();

    Font getDefault();

    Font newSimpleFont(String fontName, int fontSize);

    Font newSimpleFont(String fontName, int fontSize, FonterElements.FontColor fontColor);

    Font putInFont(String primaryKey, Font font);

    Font putOutFont(String primaryKey);

}
