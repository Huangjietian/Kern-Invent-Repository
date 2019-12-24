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

    HashMap<String, Font> fontBox = new HashMap<>();

    public POIFonterOpened(POIBox poiBox) {
        super(poiBox);
        fontBox.put(DEFAULT_KEY, getParent().working().createFont());
    }

    @Override
    public POIFontProducer produce() {
        Font font = getParent().working().createFont();
        return new POIFontProducer(font);
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


}
