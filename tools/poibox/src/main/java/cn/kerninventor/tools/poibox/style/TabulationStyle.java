package cn.kerninventor.tools.poibox.style;

import cn.kerninventor.tools.poibox.POIGadget;
import org.apache.poi.ss.usermodel.CellStyle;

/**
 * @Title: TabulationStyle
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.layout
 * @Author Kern
 * @Date 2019/12/9 19:28
 * @Description:
 */
public abstract class TabulationStyle {

    private POIStyler styler;

    private POIFonter fonter;

    protected CellStyle defaultStyleHeadLine;

    protected CellStyle defaultStyleTableHead;

    protected CellStyle defaultStyleText;

    {
        styler = POIGadget.root().styler();
        fonter = POIGadget.root().fonter();
        defaultStyleHeadLine = styler.usualHeadLine(null);
        defaultStyleTableHead = styler.usualTableHeader(null);
        defaultStyleText = styler.usualTextPart(null);
    }

    public abstract CellStyle getHeadLineStyle();

    public abstract CellStyle getTableHeadStyle();

    public abstract CellStyle getTextStyle();

    protected POIStyler getStyler() {
        return styler.reset();
    }

    protected POIFonter getFonter() {
        return fonter.reset();
    }
}
