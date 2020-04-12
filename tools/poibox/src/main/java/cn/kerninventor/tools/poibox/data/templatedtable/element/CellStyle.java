package cn.kerninventor.tools.poibox.data.templatedtable.element;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

/**
 * @author Kern
 * @date 2020/4/9 11:58
 * @description
 */
public @interface CellStyle {

    CellBorder border() default @CellBorder(borderStyle = BorderStyle.THIN);

    Font font() default @Font;

    FillPatternType fillPatternType() default FillPatternType.NO_FILL;

    HSSFColor.HSSFColorPredefined foregroudColor() default HSSFColor.HSSFColorPredefined.AUTOMATIC;

    HSSFColor.HSSFColorPredefined backgroudColor() default HSSFColor.HSSFColorPredefined.AUTOMATIC;

    VerticalAlignment verticalAlignment() default VerticalAlignment.CENTER;

    HorizontalAlignment alignment() default HorizontalAlignment.CENTER;

    boolean wrapText() default false;

    boolean locked() default false;

    int indention() default 0;

    boolean hidden() default false;


}