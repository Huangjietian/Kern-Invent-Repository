package cn.kerninventor.tools.poibox.style;

import cn.kerninventor.tools.poibox.POIBox;
import cn.kerninventor.tools.poibox.POIBoxLinker;
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
public final class POIFonterOpened extends POIBoxLinker implements POIFonter {

    private Font font;

    HashMap<String, Font> fontBox = new HashMap<>();

    public POIFonterOpened(POIBox poiBox) {
        super(poiBox);
        font = getParent().working().createFont();
        fontBox.put(DEFAULT_KEY, font);
    }

    @Override
    public Font get() {
        return font;
    }

    @Override
    public POIFonter set(Font font) {
        this.font = font;
        return this;
    }

    @Override
    public POIFonter reset() {
        this.font = fontBox.get(DEFAULT_KEY);
        return this;
    }

    @Override
    public Font getDefault() {
        return fontBox.get(DEFAULT_KEY);
    }

    @Override
    public Font newSimpleFont(String fontName, int fontSize) {
        Font font = getParent().working().createFont();
        font.setFontName(fontName);
        font.setFontHeightInPoints((short) fontSize);
        return font;
    }

    @Override
    public Font newSimpleFont(String fontName, int fontSize, FonterElements.FontColor fontColor) {
        Font font = getParent().working().createFont();
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

    @Override
    public POIFonter setFontName(String fontName) {
        font.setFontName(fontName);
        return this;
    }

    @Override
    public POIFonter setFontSize(int fontSize) {
        font.setFontHeightInPoints((short) fontSize);
        return this;
    }

    @Override
    public POIFonter setFontColor(FonterElements.FontColor fontColor) {
        font.setColor(fontColor.getIndex());
        return this;
    }

    @Override
    public POIFonter setItalic(boolean italic) {
        font.setItalic(italic);
        return this;
    }

    @Override
    public POIFonter setStrikeout(boolean strikeout) {
        font.setStrikeout(strikeout);
        return this;
    }

    @Override
    public POIFonter setBold(boolean bold) {
        font.setBold(bold);
        return this;
    }

    @Override
    public POIFonter setUnderline(FonterElements.UnderLine underline) {
        font.setUnderline(underline.getIndex());
        return this;
    }

    @Override
    public POIFonter setCharSet(FonterElements.Charset charSet) {
        font.setCharSet(charSet.getIndex());
        return this;
    }
}
