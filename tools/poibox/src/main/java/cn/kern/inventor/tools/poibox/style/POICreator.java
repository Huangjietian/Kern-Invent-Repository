package cn.kern.inventor.tools.poibox.style;

import cn.kern.inventor.tools.poibox.POIBox;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * @Title: POICreator
 * @ProjectName tools
 * @Description: TODO
 * @Author Kern
 * @Date 2019/10/29 19:55
 */
public class POICreator {

    private POIBox poiBox;

    public POICreator(POIBox poiBox) {
        this.poiBox = poiBox;
    }

    public POIBox getParent() {
        return poiBox;
    }

}
