package cn.kerninventor.tools.poibox.style;

import cn.kerninventor.tools.poibox.elements.StylerElements;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;

/**
 * @Title: POIStyleBox
 * @ProjectName tools
 * @Description: TODO
 * @Author Kern
 * @Date 2019/10/29 14:57
 */
public interface Styler {

    StyleProducer produce();

    CellStyle putInStyle(String key, CellStyle style);

    CellStyle putOutStyle(String key);

    /**
     * 常见的大标题格式
     * @param fontSize
     * @return
     */
    CellStyle usualHeadLine(Integer fontSize);

    /**
     * 常见的表头格式
     * @param fontSize
     * @return
     */
    CellStyle usualTableHeader(Integer fontSize);

    /**
     * 常见的正文格式
     * @param fontSize
     * @return
     */
    CellStyle usualTextPart(Integer fontSize);

    static CellStyle cloneStyle(Workbook workbook, CellStyle sourceStyle) {
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.cloneStyleFrom(sourceStyle);
        return cellStyle;
    }
}
