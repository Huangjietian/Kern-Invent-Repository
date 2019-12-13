package cn.kerninventor.tools.poibox;

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
public class TestStyle extends TabulationStyle {

    @Override
    public CellStyle getHeadLineStyle() {
        defaultStyleHeadLine.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        defaultStyleHeadLine.setFillForegroundColor(HSSFColor.HSSFColorPredefined.GREEN.getIndex());
        return defaultStyleHeadLine;
    }

    @Override
    public CellStyle getTableHeadStyle() {
        return defaultStyleTableHead;
    }

    @Override
    public CellStyle getTextStyle() {
        return defaultStyleText;
    }

}
