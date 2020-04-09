package cn.kerninventor.tools.poibox.style.enums;

import org.apache.poi.ss.usermodel.Font;

/**
 * @author Kern
 * @date 2020/4/9 10:30
 */
public enum  FontColor {

    BLACK(Font.COLOR_NORMAL),
    RED(Font.COLOR_RED),
    ;
    private int index;

    FontColor(int index) {
        this.index = index;
    }

    public short getIndex() {
        return (short) index;
    }

}
