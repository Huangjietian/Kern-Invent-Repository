package cn.kerninventor.tools.poibox.style;

import cn.kerninventor.tools.poibox.BoxBracket;
import cn.kerninventor.tools.poibox.POIBox;
import cn.kerninventor.tools.poibox.developer.SealingVersion;
import cn.kerninventor.tools.poibox.style.enums.FontColor;
import org.apache.poi.ss.usermodel.Font;


/**
 * @Author Kern
 * @Date 2019/10/29 19:53
 */
@SealingVersion(version = 1.00)
public final class FontHandler extends BoxBracket implements Fonter {

    public FontHandler(POIBox poiBox) {
        super(poiBox);
    }

    @Override
    public FontProducer producer() {
        Font font = poiBox.workbook().createFont();
        return new FontProducer(font);
    }

    @Override
    public Font simpleFont(String fontName, int fontSize) {
        Font font = poiBox.workbook().createFont();
        font.setFontName(fontName);
        font.setFontHeightInPoints((short) fontSize);
        return font;
    }

    @Override
    public Font simpleFont(String fontName, int fontSize, FontColor fontColor) {
        Font font = poiBox.workbook().createFont();
        font.setFontName(fontName);
        font.setFontHeightInPoints((short)fontSize);
        font.setColor(fontColor.getIndex());
        return font;
    }

    @Override
    public Font simpleFont(String fontName, int fontSize, boolean bold) {
        Font font = poiBox.workbook().createFont();
        font.setFontName(fontName);
        font.setFontHeightInPoints((short)fontSize);
        font.setBold(bold);
        return font;
    }

    @Override
    public Font generate(cn.kerninventor.tools.poibox.data.templatedtable.element.Font font) {
        return producer()
                .setFontName(font.fontName()).setFontSize(font.fontSize())
                .setBold(font.bold()).setFontColor(font.color())
                .setItalic(font.italic()).setStrikeout(font.strikeout())
                .setUnderline(font.underline()).get();
    }

}
