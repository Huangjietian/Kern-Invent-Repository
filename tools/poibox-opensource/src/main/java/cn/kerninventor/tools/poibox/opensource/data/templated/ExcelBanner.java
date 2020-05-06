package cn.kerninventor.tools.poibox.opensource.data.templated;

import cn.kerninventor.tools.poibox.opensource.data.templated.element.Border;
import cn.kerninventor.tools.poibox.opensource.data.templated.element.Font;
import cn.kerninventor.tools.poibox.opensource.data.templated.element.Range;
import cn.kerninventor.tools.poibox.opensource.data.templated.element.Style;
import cn.kerninventor.tools.poibox.opensource.style.Fonter;
import org.apache.poi.ss.usermodel.BorderStyle;

/**
 * @author Kern
 * @date 2020/4/9 11:54
 * @description
 */
public @interface ExcelBanner {

    String value();

    Style style() default @Style(
            index = -1,
            border = @Border(borderStyle = BorderStyle.THIN),
            font = @Font(fontName = Fonter.DEF_NAME_HEADER,
                    fontSize = Fonter.DEF_SIZE_HEADLINE
            ),
            wrapText = true
    );

    float rowHeight() default ExcelTabulation.DefaultRowHeight;

    Range range() default @Range;

}