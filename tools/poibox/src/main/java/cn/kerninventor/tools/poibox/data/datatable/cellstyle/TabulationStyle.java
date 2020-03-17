package cn.kerninventor.tools.poibox.data.datatable.cellstyle;

import cn.kerninventor.tools.poibox.BoxGadget;
import cn.kerninventor.tools.poibox.data.exception.IllegalSourceClassOfTabulationException;
import org.apache.poi.ss.usermodel.CellStyle;

import javax.xml.bind.annotation.XmlType;
import java.lang.reflect.InvocationTargetException;

/**
 * @Title: TabulationStyle
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.layout
 * @Author Kern
 * @Date 2019/12/9 19:28
 * @Description:
 */
public interface TabulationStyle {

    static TabulationStyle newInstance(Class<? extends TabulationStyle> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new IllegalSourceClassOfTabulationException("An explicit, parameterless constructor is required in Tabulation.style()");
        }
    }

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
