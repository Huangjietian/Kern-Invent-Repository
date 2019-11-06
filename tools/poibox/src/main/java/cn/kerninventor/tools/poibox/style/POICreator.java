package cn.kerninventor.tools.poibox.style;

import cn.kerninventor.tools.poibox.POIBox;

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
