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

    Font get();

    POIFonter set(Font font);

    POIFonter reset();

    Font getDefault();

    Font newSimpleFont(String fontName, int fontSize);

    Font newSimpleFont(String fontName, int fontSize, FonterElements.FontColor fontColor);

    Font putInFont(String primaryKey, Font font);

    Font putOutFont(String primaryKey);

    /**
     * 设置字体
     * @param fontName
     * @return
     */
    POIFonter setFontName(String fontName);

    /**
     * 设置字体字号
     * @param fontSize
     * @return
     */
    POIFonter setFontSize(int fontSize);

    /**
     * 设置字体颜色
     * @param fontColor
     * @return
     */
    POIFonter setFontColor(FonterElements.FontColor fontColor);

    /**
     * 设置斜体
     * @param italic
     * @return
     */
    POIFonter setItalic(boolean italic);

    /**
     * 设置删除线
     * @param strikeout
     * @return
     */
    POIFonter setStrikeout(boolean strikeout);

    /**
     * 设置加粗
     * @param bold
     * @return
     */
    POIFonter setBold(boolean bold);

    /**
     * 设置下划线
     * @param underline
     * @return
     */
    POIFonter setUnderline(FonterElements.UnderLine underline);

    /**
     * 设置编码格式
     * @param charSet
     * @return
     */
    POIFonter setCharSet(FonterElements.Charset charSet);


}
