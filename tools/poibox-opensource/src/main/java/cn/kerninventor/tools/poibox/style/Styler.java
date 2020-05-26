package cn.kerninventor.tools.poibox.style;

import cn.kerninventor.tools.poibox.data.tabulation.element.Style;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * @author Kern
 * @date 2019/10/29 14:57
 */
public interface Styler {

    StyleProducer producer();

    CellStyle defaultHeadline(Integer fontSize);

    CellStyle defaultThead(Integer fontSize);

    CellStyle defaultTbody(Integer fontSize);

    CellStyle generate(Style style);

    CellStyle copyStyle(CellStyle targetStyle);

    static CellStyle cloneStyle(Workbook workbook, CellStyle sourceStyle) {
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.cloneStyleFrom(sourceStyle);
        return cellStyle;
    }

    CellStyle copyStyle(CellStyle targetStyle, Workbook workbook);
}
