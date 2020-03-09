package cn.kerninventor.tools.poibox.style;

import cn.kerninventor.tools.poibox.BoxGadget;
import org.apache.poi.ss.usermodel.CellStyle;

/**
 * @Title: TabulationStyle
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.layout
 * @Author Kern
 * @Date 2019/12/9 19:28
 * @Description:
 */
public interface TabulationStyle {

    CellStyle getHeadLineStyle();

    CellStyle getTableHeadStyle();

    CellStyle getTextStyle();

}
