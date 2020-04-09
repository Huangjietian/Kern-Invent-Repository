package cn.kerninventor.tools.poibox.style;

import cn.kerninventor.tools.poibox.BoxBracket;
import cn.kerninventor.tools.poibox.POIBox;
import cn.kerninventor.tools.poibox.style.enums.BorderDirection;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;

/**
 * @Author Kern
 * @Date 2019/10/29 17:25
 */
public final class StyleHandler extends BoxBracket implements Styler {

    public StyleHandler(POIBox poiBox) {
        super(poiBox);
    }

    @Override
    public StyleProducer producer() {
        CellStyle cellStyle = poiBox.workbook().createCellStyle();
        return new StyleProducer(cellStyle);
    }

    @Override
    public CellStyle usualHeadLine(Integer fontSize) {
        fontSize = fontSize == null ? Fonter.DEF_SIZE_HEADLINE : fontSize;
        Font font = poiBox.fonter().simpleFont(Fonter.DEF_NAME_HEADER, fontSize, true);
        return producer()
                .setWholeCenter()
                .setBorder(BorderDirection.SURROUND, BorderStyle.DOUBLE)
                .setFont(font)
                .get();
    }

    @Override
    public CellStyle usualTableHeader(Integer fontSize) {
        fontSize = fontSize == null ? Fonter.DEF_SIZE_TABLEHEADER : fontSize;
        Font font = poiBox.fonter().simpleFont(Fonter.DEF_NAME_HEADER, fontSize);
        return producer()
                .setWholeCenter()
                .setBorder(BorderDirection.SURROUND, BorderStyle.THIN)
                .setFont(font)
                .get();
    }

    @Override
    public CellStyle usualTextPart(Integer fontSize) {
        fontSize = fontSize == null ? Fonter.DEF_SIZE_TEXTPART : fontSize;
        Font font = poiBox.fonter().simpleFont(Fonter.DEF_NAME_TEXTPART, fontSize);
        return producer()
                .setWholeCenter()
                .setWrapText(true)
                .setBorder(BorderDirection.SURROUND, BorderStyle.THIN)
                .setFont(font)
                .get();
    }
}
