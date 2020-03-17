package cn.kerninventor.tools.poibox.data.datatable.templator;

import cn.kerninventor.tools.poibox.layout.LayoutHandler;
import cn.kerninventor.tools.poibox.layout.Layouter;
import cn.kerninventor.tools.poibox.layout.MergedRange;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;

/**
 * @Title HeadLine
 * @ProjectName kerninventresp
 * @PackageName cn.kerninventor.tools.poibox.data.datatable.templator
 * @Author Kern
 * @Date 2020/3/12 19:53
 * @Description TODO
 */
public class Headline {

    private int headlineRdx, firstCdx, lastCdx;

    private CellStyle style;

    private String content;

    public void draw(Sheet sheet){
        CellRangeAddress range = new CellRangeAddress(headlineRdx,headlineRdx,firstCdx,lastCdx);
        Layouter layouter = new LayoutHandler(null);
        layouter.mergedRegion(sheet, range)
                .setMergeRangeContent(content)
                .setMergeRangeStyle(style);
    }

    public Headline(int headlineRdx, int firstCdx, int lastCdx, CellStyle style, String content) {
        this.headlineRdx = headlineRdx;
        this.firstCdx = firstCdx;
        this.lastCdx = lastCdx;
        this.style = style;
        this.content = content;
    }

    public int getHeadlineRdx() {
        return headlineRdx;
    }

    public Headline setHeadlineRdx(int headlineRdx) {
        this.headlineRdx = headlineRdx;
        return this;
    }

    public int getFirstCdx() {
        return firstCdx;
    }

    public Headline setFirstCdx(int firstCdx) {
        this.firstCdx = firstCdx;
        return this;
    }

    public int getLastCdx() {
        return lastCdx;
    }

    public Headline setLastCdx(int lastCdx) {
        this.lastCdx = lastCdx;
        return this;
    }

    public CellStyle getStyle() {
        return style;
    }

    public Headline setStyle(CellStyle style) {
        this.style = style;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Headline setContent(String content) {
        this.content = content;
        return this;
    }
}
