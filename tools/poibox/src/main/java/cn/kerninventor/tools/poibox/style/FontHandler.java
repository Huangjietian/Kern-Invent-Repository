package cn.kerninventor.tools.poibox.style;

import cn.kerninventor.tools.poibox.POIBox;
import cn.kerninventor.tools.poibox.BoxBracket;
import cn.kerninventor.tools.poibox.elements.FonterElements;
import org.apache.poi.ss.usermodel.Font;

import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @Title: POIFonterInner
 * @ProjectName tools
 * @Description: TODO
 * @Author Kern
 * @Date 2019/10/29 19:53
 */
public final class FontHandler extends BoxBracket implements Fonter {

    ConcurrentHashMap<String, Font> fontGrid = new ConcurrentHashMap<>();

    public FontHandler(POIBox poiBox) {
        super(poiBox);
    }

    @Override
    public FontProducer produce() {
        return new FontProducer(getParent().workbook().createFont());
    }


    @Override
    public Font simpleFont(String fontName, int fontSize) {
        Font font = getParent().workbook().createFont();
        font.setFontName(fontName);
        font.setFontHeightInPoints((short) fontSize);
        return font;
    }

    @Override
    public Font simpleFont(String fontName, int fontSize, FonterElements.FontColor fontColor) {
        Font font = getParent().workbook().createFont();
        font.setFontName(fontName);
        font.setFontHeightInPoints((short)fontSize);
        font.setColor(fontColor.getIndex());
        return font;
    }

    @Override
    public Font putInFont(String primaryKey, Font font) {
        fontGrid.put(Objects.requireNonNull(primaryKey,"Font primary key cannot be null"),
                Objects.requireNonNull(font, " The font cannot be null"));
        return font;
    }

    @Override
    public Font putOutFont(String primaryKey) {
        return Objects.requireNonNull(fontGrid.get(primaryKey), "The font for the primary key does not exist");
    }


}
