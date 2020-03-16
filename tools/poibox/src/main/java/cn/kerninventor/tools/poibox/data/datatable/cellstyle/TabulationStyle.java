package cn.kerninventor.tools.poibox.data.datatable.cellstyle;

import cn.kerninventor.tools.poibox.BoxGadget;
import org.apache.poi.ss.usermodel.CellStyle;

import javax.xml.bind.annotation.XmlType;

/**
 * @Title: TabulationStyle
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.layout
 * @Author Kern
 * @Date 2019/12/9 19:28
 * @Description:
 */
public interface TabulationStyle {

    default CellStyle getHeadLineStyle() {
        return BoxGadget.root().styler().usualHeadLine(null);
    }

    default CellStyle getTableHeadStyle() {
        return BoxGadget.root().styler().usualTableHeader(null);
    }

    default CellStyle getTextStyle() {
        return BoxGadget.root().styler().usualTextPart(null);
    }

}
