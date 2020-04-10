package cn.kerninventor.tools.poibox.data.templatedtable.element;

import cn.kerninventor.tools.poibox.style.Fonter;
import org.apache.poi.ss.usermodel.BorderStyle;

/**
 * @author Kern
 * @date 2020/4/9 11:54
 * @description
 */
public @interface Banner {

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
