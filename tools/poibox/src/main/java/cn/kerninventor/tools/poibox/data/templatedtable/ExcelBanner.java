package cn.kerninventor.tools.poibox.data.templatedtable;

import cn.kerninventor.tools.poibox.data.templatedtable.element.Border;
import cn.kerninventor.tools.poibox.data.templatedtable.element.Style;
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

    Style style() default @Style(
            border = @Border(borderStyle = BorderStyle.DOUBLE),
            font = @Font(fontName = Fonter.DEF_NAME_HEADER,
                    fontSize = Fonter.DEF_SIZE_HEADLINE,
                    bold = true
            ),
            wrapText = true
    );

    Range range() default @Range;

}
