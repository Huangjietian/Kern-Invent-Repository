package cn.kerninventor.tools.poibox;

import cn.kerninventor.tools.poibox.layout.POILayouter;
import cn.kerninventor.tools.poibox.style.POIFonter;
import cn.kerninventor.tools.poibox.style.POIStyler;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * @Title: POIBox
 * @ProjectName tools
 * @Description: TODO
 * @Author Kern
 * @Date 2019/10/29 19:06
 */
public interface POIBox {

    POIStyler styler();

    POIFonter fonter();

    POILayouter layouter();

    Workbook working();
}
