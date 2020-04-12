package cn.kerninventor.tools.poibox;


import cn.kerninventor.tools.poibox.config.SealingVersion;

/**
 * @uthor Kern
 * @date 2019/10/29 19:55
 */
@SealingVersion(
        version = 0.00,
        description = "POIBox 支架"
)
public class BoxBracket {

    protected POIBox poiBox;

    public BoxBracket(POIBox poiBox) {
        this.poiBox = poiBox;
    }

    public POIBox getParent() {
        return poiBox;
    }

}
