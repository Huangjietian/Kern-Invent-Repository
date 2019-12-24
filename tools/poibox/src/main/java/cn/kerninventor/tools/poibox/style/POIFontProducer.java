package cn.kerninventor.tools.poibox.style;

import cn.kerninventor.tools.poibox.elements.FonterElements;
import org.apache.poi.ss.usermodel.Font;

/**
 * @Title: POIFontProducer
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.style
 * @Author Kern
 * @Date 2019/12/17 15:51
 * @Description: TODO
 */
public final class POIFontProducer {

    private Font font;

    public POIFontProducer(Font font) {
        this.font = font;
    }

    /**
     * 设置字体名称
     * @param fontName
     * @return
     */
    public POIFontProducer setFontName(String fontName) {
        font.setFontName(fontName);
        return this;
    }

    /**
     * 设置字体字号
     * @param fontSize
     * @return
     */
    public POIFontProducer setFontSize(int fontSize) {
        font.setFontHeightInPoints((short) fontSize);
        return this;
    }

    /**
     * 设置字体颜色
     * @param fontColor
     * @return
     */
    public POIFontProducer setFontColor(FonterElements.FontColor fontColor) {
        font.setColor(fontColor.getIndex());
        return this;
    }

    /**
     * 设置斜体
     * @param italic
     * @return
     */
    public POIFontProducer setItalic(boolean italic) {
        font.setItalic(italic);
        return this;
    }

    /**
     * 设置删除线
     * @param strikeout
     * @return
     */
    public POIFontProducer setStrikeout(boolean strikeout) {
        font.setStrikeout(strikeout);
        return this;
    }

    /**
     * 设置加粗
     * @param bold
     * @return
     */
    public POIFontProducer setBold(boolean bold) {
        font.setBold(bold);
        return this;
    }

    /**
     * 设置下划线
     * @param underline
     * @return
     */
    public POIFontProducer setUnderline(FonterElements.UnderLine underline) {
        font.setUnderline(underline.getIndex());
        return this;
    }

    /**
     * 设置编码格式
     * @param charSet
     * @return
     */
    public POIFontProducer setCharSet(FonterElements.Charset charSet) {
        font.setCharSet(charSet.getIndex());
        return this;
    }

    public Font get(){
        return font;
    }
}
