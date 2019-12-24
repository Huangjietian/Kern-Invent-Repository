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
public interface POIStyler {

    String DEFAULT_KEY = "DEFAULT_STYLE";

    CellStyle getDefault();

    CellStyle get();

    POIStyler set(CellStyle style);

    POIStyler reset();

    CellStyle putInStyle(String key, CellStyle style);

    CellStyle putOutStyle(String key);

    /**
     * 风格加入字体
     * @param font
     * @return
     */
    POIStyler setFont(Font font);

    /**
     * 设置边框，颜色默认黑色
     * @param cellDirection  方位参数
     * @param borderStyle  边框线参数
     * @return
     */
    POIStyler setBorder(StylerElements.CellDirection cellDirection, BorderStyle borderStyle);

    /**
     * 设置边框颜色
     * @param cellDirection
     * @param hssfColorPredefined
     * @return
     */
    POIStyler setBorderColor(StylerElements.CellDirection cellDirection, HSSFColor.HSSFColorPredefined hssfColorPredefined);

    /**
     * 设置单元格背景填充样式：全铺，斑点，砖块等
     * @param fillPatternType
     * @return
     */
    POIStyler setFillPattern(FillPatternType fillPatternType);

    /**
     * 设置填充背景颜色，需要在此之后设置背景填充样式，否则无效
     * @param hssfColorPredefined
     * @return
     */
    POIStyler setFillBackgroundColor(HSSFColor.HSSFColorPredefined hssfColorPredefined);

    /**
     * 设置填充前景颜色，需要在此之后设置背景填充样式，否则无效
     * @param hssfColorPredefined
     * @return
     */
    POIStyler setFillForegroundColor(HSSFColor.HSSFColorPredefined hssfColorPredefined);

    /**
     * 设置上下居中
     * @param verticalAlignment
     * @return
     */
    POIStyler setVerticalAlignment(VerticalAlignment verticalAlignment);

    /**
     * 设置左右居中
     * @param horizontalAlignment
     * @return
     */
    POIStyler setAlignment(HorizontalAlignment horizontalAlignment);

    /**
     * 设置完全居中
     * @return
     */
    POIStyler setWholeCenter();

    /**
     * 设置单元格内容超出时是否自动换行
     * @param wrapText
     * @return
     */
    POIStyler setWrapText(boolean wrapText);

    /**
     * 设置单元格锁，此操作后生成的单元格无法在Excel中修改
     * @param locked
     * @return
     */
    POIStyler setLocked(boolean locked);

    /**
     * 根据short参数设置内容紧缩程度
     * @param indention
     * @return
     */
    POIStyler setIndention(short indention);

    /**
     * 设置隐藏
     * @param hidden
     * @return
     */
    POIStyler setHidden(Boolean hidden);

    /**
     * 设置数据格式
     * @param index
     * @return
     */
    POIStyler setDataFormat(short index);

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
