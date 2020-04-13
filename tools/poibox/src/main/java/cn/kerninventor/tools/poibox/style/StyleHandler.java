package cn.kerninventor.tools.poibox.style;

import cn.kerninventor.tools.poibox.BoxBracket;
import cn.kerninventor.tools.poibox.POIBox;
import cn.kerninventor.tools.poibox.data.templatedtable.element.Style;
import cn.kerninventor.tools.poibox.developer.SealingVersion;
import cn.kerninventor.tools.poibox.style.enums.BorderDirection;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;

/**
 * @author Kern
 * @date 2019/10/29 17:25
 */
@SealingVersion(version = 1.00)
public final class StyleHandler extends BoxBracket implements Styler {

    public StyleHandler(POIBox poiBox) {
        super(poiBox);
    }

    @Override
    public StyleProducer producer() {
        CellStyle cellStyle = getParent().workbook().createCellStyle();
        return new StyleProducer(cellStyle);
    }

    @Override
    public CellStyle defaultHeadline(Integer fontSize) {
        fontSize = fontSize == null ? Fonter.DEF_SIZE_HEADLINE : fontSize;
        Font font = getParent().fonter().simpleFont(Fonter.DEF_NAME_HEADER, fontSize, true);
        return producer()
                .setWholeCenter()
                .setBorder(BorderDirection.SURROUND, BorderStyle.DOUBLE)
                .setFont(font)
                .get();
    }

    @Override
    public CellStyle defaultThead(Integer fontSize) {
        fontSize = fontSize == null ? Fonter.DEF_SIZE_TABLEHEADER : fontSize;
        Font font = getParent().fonter().simpleFont(Fonter.DEF_NAME_HEADER, fontSize);
        return producer()
                .setWholeCenter()
                .setBorder(BorderDirection.SURROUND, BorderStyle.THIN)
                .setFont(font)
                .get();
    }

    @Override
    public CellStyle defaultTbody(Integer fontSize) {
        fontSize = fontSize == null ? Fonter.DEF_SIZE_TEXTPART : fontSize;
        Font font = getParent().fonter().simpleFont(Fonter.DEF_NAME_TEXTPART, fontSize);
        return producer()
                .setWholeCenter()
                .setWrapText(true)
                .setBorder(BorderDirection.SURROUND, BorderStyle.THIN)
                .setFont(font)
                .get();
    }

    @Override
    public CellStyle generate(Style style) {
        return producer()
                .setBorder(style.border().direction(), style.border().borderStyle())
                .setFont(getParent().fonter().generate(style.font()))
                .setFillPattern(style.fillPatternType())
                .setFillForegroundColor(style.foregroudColor())
                .setFillBackgroundColor(style.backgroudColor())
                .setVerticalAlignment(style.verticalAlignment())
                .setAlignment(style.alignment())
                .setWrapText(style.wrapText())
                .setLocked(style.locked())
                .setIndention((short) style.indention())
                .setHidden(style.hidden())
                .get();
    }

    @Override
    public CellStyle copyStyle(CellStyle targetStyle) {
        CellStyle cellStyle = getParent().workbook().createCellStyle();
        cellStyle.cloneStyleFrom(targetStyle);
        return cellStyle;
    }
}
