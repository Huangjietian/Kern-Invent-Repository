package cn.kerninventor.tools.poibox.style;

import cn.kerninventor.tools.poibox.POIBox;
import cn.kerninventor.tools.poibox.enums.FonterElements;
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
public final class POIFonterInner extends POICreator implements POIFonter {

    private Font font;

    HashMap<String, Font> fontBox = new HashMap<>();

    public POIFonterInner(POIBox poiBox) {
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
        fontBox.put(Objects.requireNonNull(primaryKey,"字体唯一标识不能为空, Font primary key cannot be null"),
                Objects.requireNonNull(font, "字体不能为空, The font cannot be null"));
        return font;
    }

    @Override
    public Font putOutFont(String primaryKey) {
        return Objects.requireNonNull(fontBox.get(primaryKey), "该唯一标识的字体不存在， The font for the primary key does not exist");
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
        if (bold){
            font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        } else {
            font.setBoldweight(Font.BOLDWEIGHT_NORMAL);
        }
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
