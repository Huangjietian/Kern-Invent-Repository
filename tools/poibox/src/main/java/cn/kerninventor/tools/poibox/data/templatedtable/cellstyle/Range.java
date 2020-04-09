package cn.kerninventor.tools.poibox.data.templatedtable.cellstyle;

/**
 * @author Kern
 * @date 2020/4/9 16:19
 * @description
 */
public @interface Range {

    int fistRow();

    int firstCell();

    int lastRow();

    int lastCell();
}
