package cn.kerninventor.tools.poibox.style;

import cn.kerninventor.tools.poibox.data.templated.element.Style;
import cn.kerninventor.tools.poibox.developer.SealingVersion;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * @Author Kern
 * @Date 2019/10/29 14:57
 */
@SealingVersion(
        version = 1.00,
        description = "" +
                "1. 风格生产器" +
                "2. 默认的几种风格" +
                "3. 根据注释生成风格" +
                "4. 风格的复制"
)
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
