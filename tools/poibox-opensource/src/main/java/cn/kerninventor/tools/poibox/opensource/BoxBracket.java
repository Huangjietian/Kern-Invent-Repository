package cn.kerninventor.tools.poibox.opensource;


import cn.kerninventor.tools.poibox.opensource.developer.SealingVersion;

/**
 * @uthor Kern
 * @date 2019/10/29 19:55
 */
@SealingVersion(
        version = 1.00,
        description = "Bracket of Poibox",
        zh_description = "POIBox 支架, 用于传递poibox对象，链接盒子的各个功能以便合并使用"
)
public class BoxBracket {

    protected Poibox poiBox;

    public BoxBracket(Poibox poiBox) {
        this.poiBox = poiBox;
    }

    public Poibox getParent() {
        return poiBox;
    }

}
