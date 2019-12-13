package cn.kerninventor.tools.poibox.style;

import org.apache.poi.ss.usermodel.CellStyle;

/**
 * @Title: RootTabulationStyle
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.layout
 * @Author Kern
 * @Date 2019/12/9 20:15
 * @Description: TODO
 */
public final class RootTabulationStyle extends TabulationStyle {

    @Override
    public CellStyle getHeadLineStyle() {
        return getStyler().get();
    }

    @Override
    public CellStyle getTableHeadStyle() {
        return getStyler().get();
    }

    @Override
    public CellStyle getTextStyle() {
        return getStyler().get();
    }

}
