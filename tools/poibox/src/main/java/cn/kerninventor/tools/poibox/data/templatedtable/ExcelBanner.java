package cn.kerninventor.tools.poibox.data.templatedtable;

import cn.kerninventor.tools.poibox.data.templatedtable.cellstyle.CellBorder;
import cn.kerninventor.tools.poibox.data.templatedtable.cellstyle.CellStyle;
import cn.kerninventor.tools.poibox.data.templatedtable.cellstyle.Font;
import cn.kerninventor.tools.poibox.data.templatedtable.cellstyle.Range;
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

    //范围，允许一个标题行有多个范围，对结果不负责
    Range[] range() default {};

}
