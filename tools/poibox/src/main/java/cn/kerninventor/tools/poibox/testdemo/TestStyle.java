package cn.kerninventor.tools.poibox.testdemo;

import cn.kerninventor.tools.poibox.BoxBracket;
import cn.kerninventor.tools.poibox.BoxGadget;
import cn.kerninventor.tools.poibox.style.TabulationStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;

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
        CellStyle cellStyle = BoxGadget.root().styler().usualHeadLine(null);
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.LIGHT_BLUE.getIndex());
        return cellStyle;
    }

    @Override
    public CellStyle getTableHeadStyle() {
        return BoxGadget.root().styler().usualTableHeader(null);
    }

    @Override
    public CellStyle getTextStyle() {
        return BoxGadget.root().styler().usualTextPart(null);
    }

}
