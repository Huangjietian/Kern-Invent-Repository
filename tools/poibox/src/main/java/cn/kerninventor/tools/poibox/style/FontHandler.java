package cn.kerninventor.tools.poibox.style;

import cn.kerninventor.tools.poibox.POIBox;
import cn.kerninventor.tools.poibox.BoxBracket;
import cn.kerninventor.tools.poibox.elements.FonterElements;
import org.apache.poi.ss.usermodel.Font;

import java.util.HashMap;
import java.util.Objects;


/**
 * @Title: POIFonterInner
 * @ProjectName tools
 * @Description: TODO
 * @Author Kern
 * @Date 2019/10/29 19:53
 */
public final class FontHandler extends BoxBracket implements Fonter {

    HashMap<String, Font> fontBox = new HashMap<>();

    public FontHandler(POIBox poiBox) {
        super(poiBox);
        fontBox.put(DEFAULT_KEY, getParent().workbook().createFont());
    }

    @Override
    public FontProducer produce() {
        Font font = getParent().workbook().createFont();
        return new FontProducer(font);
    }

    @Override
    public Font getDefault() {
        return fontBox.get(DEFAULT_KEY);
    }

    @Override
    public Font newSimpleFont(String fontName, int fontSize) {
        Font font = getParent().workbook().createFont();
        font.setFontName(fontName);
        font.setFontHeightInPoints((short) fontSize);
        return font;
    }

    @Override
    public Font newSimpleFont(String fontName, int fontSize, FonterElements.FontColor fontColor) {
        Font font = getParent().workbook().createFont();
        font.setFontName(fontName);
        font.setFontHeightInPoints((short)fontSize);
        font.setColor(fontColor.getIndex());
        return font;
    }

    @Override
    public Font putInFont(String primaryKey, Font font) {
        fontBox.put(Objects.requireNonNull(primaryKey,"Font primary key cannot be null"),
                Objects.requireNonNull(font, " The font cannot be null"));
        return font;
    }

    @Override
    public Font putOutFont(String primaryKey) {
        return Objects.requireNonNull(fontBox.get(primaryKey), "The font for the primary key does not exist");
    }


}
