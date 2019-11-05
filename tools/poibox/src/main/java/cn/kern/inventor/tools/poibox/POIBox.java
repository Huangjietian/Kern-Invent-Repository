package cn.kern.inventor.tools.poibox;

import cn.kern.inventor.tools.poibox.layout.POILayouter;
import cn.kern.inventor.tools.poibox.style.POIFonter;
import cn.kern.inventor.tools.poibox.style.POIStyler;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.HashMap;

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
