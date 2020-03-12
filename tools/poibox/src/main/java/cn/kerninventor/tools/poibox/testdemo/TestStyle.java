package cn.kerninventor.tools.poibox.testdemo;

import cn.kerninventor.tools.poibox.BoxBracket;
import cn.kerninventor.tools.poibox.BoxGadget;
import cn.kerninventor.tools.poibox.elements.StylerElements;
import cn.kerninventor.tools.poibox.style.Styler;
import cn.kerninventor.tools.poibox.style.TabulationStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;

import javax.swing.text.Style;

/**
 * @Title: TestStyle
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.style
 * @Author Kern
 * @Date 2019/12/13 18:28
 * @Description: TODO
 */
public class TestStyle implements TabulationStyle {

    @Override
    public CellStyle getHeadLineStyle() {
        Styler styler = BoxGadget.root().styler();
        CellStyle cellStyle = styler.usualHeadLine(null);
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        styler.setBorder(StylerElements.CellDirection.SURROUND, BorderStyle.DOUBLE);
        cellStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.LIGHT_CORNFLOWER_BLUE.getIndex());
        return cellStyle;
    }

    @Override
    public CellStyle getTableHeadStyle() {
        CellStyle cellStyle = BoxGadget.root().styler().usualTableHeader(null);
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.GREY_25_PERCENT.getIndex());
        return cellStyle;
    }

    @Override
    public CellStyle getTextStyle() {
        return BoxGadget.root().styler().usualTextPart(null);
    }

}
