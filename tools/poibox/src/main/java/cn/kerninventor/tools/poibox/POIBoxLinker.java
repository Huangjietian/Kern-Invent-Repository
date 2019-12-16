package cn.kerninventor.tools.poibox;


import java.io.Serializable;

/**
 * @Title: POICreator
 * @ProjectName tools
 * @Description: TODO
 * @Author Kern
 * @Date 2019/10/29 19:55
 */
public class POIBoxLinker implements Serializable {

    protected static final long serialVersionUID = 1L;

    private POIBox poiBox;

    public POIBoxLinker(POIBox poiBox) {
        this.poiBox = poiBox;
    }

    public POIBox getParent() {
        return poiBox;
    }

}