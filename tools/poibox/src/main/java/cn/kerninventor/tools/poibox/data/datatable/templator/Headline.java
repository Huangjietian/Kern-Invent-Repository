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

    public Headline(ExcelTabulationTemplator templator, CellStyle style, String content) {
        this.templator = templator;
        this.cellStyle = style;
        this.content = content;
    }

    private ExcelTabulationTemplator templator;

    private CellStyle cellStyle;

    private String content;

    public ExcelTabulationTemplator getTemplator() {
        return templator;
    }

    public Headline setTemplator(ExcelTabulationTemplator templator) {
        this.templator = templator;
        return this;
    }

    public CellStyle getCellStyle() {
        return cellStyle;
    }

    public Headline setCellStyle(CellStyle cellStyle) {
        this.cellStyle = cellStyle;
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
