package cn.kerninventor.tools.poibox.data.templatedtable;

import cn.kerninventor.tools.poibox.data.templatedtable.element.CellBorder;
import cn.kerninventor.tools.poibox.data.templatedtable.element.CellStyle;
import cn.kerninventor.tools.poibox.data.templatedtable.element.Font;
import cn.kerninventor.tools.poibox.data.templatedtable.element.Range;
import cn.kerninventor.tools.poibox.style.Fonter;
import org.apache.poi.ss.usermodel.BorderStyle;

/**
 * @author Kern
 * @date 2020/4/9 11:54
 * @description
 */
public @interface ExcelBanner {

    String value();

    CellStyle style() default @CellStyle(
            border = @CellBorder(borderStyle = BorderStyle.DOUBLE),
            font = @Font(fontName = Fonter.DEF_NAME_HEADER,
                    fontSize = Fonter.DEF_SIZE_HEADLINE,
                    bold = true
            ),
            wrapText = true
    );

    Range[] range() default {};

}
