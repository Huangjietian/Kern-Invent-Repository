package cn.kerninventor.tools.poibox.data.datatable.templator;

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

    public Headline(ExcelTabulationTemplator templator, Sheet sheet, CellRangeAddress rangeAddress, CellStyle style, String content) {
        this.templator = templator;
        this.sheet = sheet;
        this.rangeAddress = rangeAddress;
        this.style = style;
        this.content = content;
    }

    private ExcelTabulationTemplator templator;

    private Sheet sheet;

    private CellRangeAddress rangeAddress;

    private CellStyle style;

    private String content;

    public ExcelTabulationTemplator getTemplator() {
        return templator;
    }

    public CellRangeAddress getRangeAddress() {
        return rangeAddress;
    }

    public void setRangeAddress(CellRangeAddress rangeAddress) {
        this.rangeAddress = rangeAddress;
    }

    public CellStyle getStyle() {
        return style;
    }

    public void setStyle(CellStyle style) {
        this.style = style;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
