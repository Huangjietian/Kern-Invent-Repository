package cn.kerninventor.tools.poibox.data.templatedtable.element;

import cn.kerninventor.tools.poibox.developer.Postive;

/**
 * @author Kern
 * @date 2020/4/9 16:19
 * @description
 */
public @interface Range {

    @Postive
    int fistRow();
    @Postive
    int firstCell();
    @Postive
    int lastRow();
    @Postive
    int lastCell();

}
