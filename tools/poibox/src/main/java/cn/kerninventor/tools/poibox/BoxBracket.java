package cn.kerninventor.tools.poibox;


/**
 * @Title: POICreator
 * @ProjectName tools
 * @Description: TODO
 * @Author Kern
 * @Date 2019/10/29 19:55
 */
public class BoxBracket {

    protected POIBox poiBox;

    public BoxBracket(POIBox poiBox) {
        this.poiBox = poiBox;
    }

    public POIBox getParent() {
        return poiBox;
    }

}
