package cn.kerninventor.tools.poibox.style;

import cn.kerninventor.tools.poibox.BoxGadget;
import org.apache.poi.ss.usermodel.CellStyle;

/**
 * @Title: RootTabulationStyle
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.layout
 * @Author Kern
 * @Date 2019/12/9 20:15
 * @Description: TODO
 */
public final class RootTabulationStyle implements TabulationStyle {

    @Override
    public CellStyle getHeadLineStyle() {
        return BoxGadget.root().styler().usualHeadLine(null);
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
