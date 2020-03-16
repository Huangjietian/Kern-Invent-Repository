package cn.kerninventor.tools.poibox.style;

import cn.kerninventor.tools.poibox.POIBox;
import cn.kerninventor.tools.poibox.BoxBracket;
import cn.kerninventor.tools.poibox.elements.StylerElements;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;

import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Title: StyleCreator
 * @ProjectName tools
 * @Description: TODO
 * @Author Kern
 * @Date 2019/10/29 17:25
 */
public final class StyleHandler extends BoxBracket implements Styler {

    ConcurrentHashMap<String, CellStyle> styleResp = new ConcurrentHashMap<>();

    public StyleHandler(POIBox poiBox) {
        super(poiBox);
    }

    @Override
    public StyleProducer produce() {
        return new StyleProducer(getParent().workbook().createCellStyle());
    }

    @Override
    public CellStyle putInStyle(String key, CellStyle style) {
        CellStyle cellStyle = getParent().workbook().createCellStyle();
        cellStyle.cloneStyleFrom(Objects.requireNonNull(style, "The style cannot be null"));
        styleResp.put(Objects.requireNonNull(key, "Style key cannot be null"), cellStyle);
        return style;
    }

    @Override
    public CellStyle putOutStyle(String key) {
        return Objects.requireNonNull(styleResp.get(key), "The style for the key does not exist");
    }

    @Override
    public CellStyle usualHeadLine(Integer fontSize) {
        return produce()
                .setWholeCenter()
                .setBorder(StylerElements.CellDirection.SURROUND, BorderStyle.DOUBLE)
                .setFont(
                        new FontHandler(getParent())
                                .produce()
                                .setFontName(Fonter.NAME_HEADER)
                                .setFontSize(fontSize == null ? Fonter.SIZE_HEADLINE : fontSize)
                                .setBold(true)
                                .get())
                .get();
    }

    @Override
    public CellStyle usualTableHeader(Integer fontSize) {
        return produce()
                .setWholeCenter()
                .setBorder(StylerElements.CellDirection.SURROUND, BorderStyle.THIN)
                .setFont(new FontHandler(getParent()).simpleFont(Fonter.NAME_HEADER, fontSize == null ? Fonter.SIZE_TABLEHEADER : fontSize))
                .get();
    }

    @Override
    public CellStyle usualTextPart(Integer fontSize) {
        return produce()
                .setWholeCenter()
                .setWrapText(true)
                .setBorder(StylerElements.CellDirection.SURROUND, BorderStyle.THIN)
                .setFont(new FontHandler(getParent()).simpleFont(Fonter.NAME_TEXTPART, fontSize == null ? Fonter.SIZE_TEXTPART : fontSize))
                .get();
    }
}
