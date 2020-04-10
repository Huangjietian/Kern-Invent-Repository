package cn.kerninventor.tools.poibox.style;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * @Author Kern
 * @Date 2019/10/29 14:57
 */
public interface Styler {

    StyleProducer producer();

    CellStyle usualHeadLine(Integer fontSize);

    CellStyle usualTableHeader(Integer fontSize);

    CellStyle usualTextPart(Integer fontSize);

    CellStyle generate(cn.kerninventor.tools.poibox.data.templatedtable.element.CellStyle cellStyle);

    static CellStyle cloneStyle(Workbook workbook, CellStyle sourceStyle) {
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.cloneStyleFrom(sourceStyle);
        return cellStyle;
    }
}
