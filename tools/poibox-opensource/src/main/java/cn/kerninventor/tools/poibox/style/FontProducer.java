package cn.kerninventor.tools.poibox.style;

import cn.kerninventor.tools.poibox.style.enums.FontCharset;
import cn.kerninventor.tools.poibox.style.enums.FontUnderline;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Font;

/**
 * @author Kern
 * @date 2019/12/17 15:51
 */
public final class FontProducer {

    private Font font;

    FontProducer(Font font) {
        this.font = font;
    }

    public FontProducer setFontName(String fontName) {
        font.setFontName(fontName);
        return this;
    }

    public FontProducer setFontSize(int fontSize) {
        font.setFontHeightInPoints((short) fontSize);
        return this;
    }

    public FontProducer setFontColor(HSSFColor.HSSFColorPredefined fontColor) {
        font.setColor(fontColor.getIndex());
        return this;
    }

    public FontProducer setItalic(boolean italic) {
        font.setItalic(italic);
        return this;
    }

    public FontProducer setStrikeout(boolean strikeout) {
        font.setStrikeout(strikeout);
        return this;
    }

    public FontProducer setBold(boolean bold) {
        font.setBold(bold);
        return this;
    }

    public FontProducer setUnderline(FontUnderline fontUnderline) {
        font.setUnderline(fontUnderline.getIndex());
        return this;
    }

    public FontProducer setCharSet(FontCharset fontCharset) {
        font.setCharSet(fontCharset.getIndex());
        return this;
    }

    public Font get(){
        return font;
    }
}
