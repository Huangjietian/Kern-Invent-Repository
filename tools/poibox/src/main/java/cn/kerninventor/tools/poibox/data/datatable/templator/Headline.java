package cn.kerninventor.tools.poibox.data.datatable.templator;

import cn.kerninventor.tools.poibox.layout.MergedRange;
import org.apache.poi.ss.usermodel.CellStyle;

/**
 * @Title HeadLine
 * @ProjectName kerninventresp
 * @PackageName cn.kerninventor.tools.poibox.data.datatable.templator
 * @Author Kern
 * @Date 2020/3/12 19:53
 * @Description TODO
 */
public class Headline {

    public Headline(ExcelTabulationTemplator templator, MergedRange mergedRange, String content) {
        this.templator = templator;
        this.mergedRange = mergedRange;
        this.content = content;
    }

    private ExcelTabulationTemplator templator;

    private MergedRange mergedRange;

    private String content;

    public ExcelTabulationTemplator getTemplator() {
        return templator;
    }

    public MergedRange getMergedRange() {
        return mergedRange;
    }

    public String getContent() {
        return content;
    }

    public Headline setStyle(CellStyle cellStyle) {
        mergedRange.setMergeRangeStyle(cellStyle);
        return this;
    }

    public Headline setContent(String content) {
        this.content = content;
        mergedRange.setMergeRangeContent(content);
        return this;
    }
}
