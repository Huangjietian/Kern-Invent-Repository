package cn.kerninventor.tools.poibox.opensource;


/**
 * @uthor Kern
 * @date 2019/10/29 19:55
 */
public class BoxBracket {

    protected Poibox poiBox;

    public BoxBracket(Poibox poiBox) {
        this.poiBox = poiBox;
    }

    public Poibox getParent() {
        return poiBox;
    }

}
