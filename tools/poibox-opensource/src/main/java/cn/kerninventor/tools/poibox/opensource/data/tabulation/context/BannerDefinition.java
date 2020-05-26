package cn.kerninventor.tools.poibox.opensource.data.tabulation.context;

import cn.kerninventor.tools.poibox.opensource.data.tabulation.ExcelBanner;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.element.Range;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.List;

/**
 * @author Kern
 * @date 2020/4/10 10:35
 * @description
 */
public class BannerDefinition implements BannerDefinitionModifier {

    private CellStyle cellStyle;

    private CellRangeAddress rangeAddress;

    private String value;

    private Float rowHeight;

    public BannerDefinition(CellStyle cellStyle, CellRangeAddress rangeAddress, String value) {
        this.cellStyle = cellStyle;
        this.rangeAddress = rangeAddress;
        this.value = value;
    }

    public BannerDefinition(TableContext tabulation, ExcelBanner banner) {
        this.value = banner.value();
        this.cellStyle = tabulation.getParent().styler().generate(banner.style());
        this.rangeAddress = new CellRangeAddress(
                banner.range().fistRow(),
                banner.range().lastRow(),
                banner.range().firstCell(),
                banner.range().lastCell()
        );
        this.rowHeight = banner.rowHeight();
    }

    public int getLastRowIndex(){
        return rangeAddress.getLastRow();
    }

    public CellStyle getCellStyle() {
        return cellStyle;
    }

    public CellRangeAddress getRangeAddress() {
        return rangeAddress;
    }

    public String getValue() {
        return value;
    }

    public Float getRowHeight() {
        return rowHeight;
    }

    public CellRangeAddress adjustCellRangeAddress(TableContext tabulation, List<ColumnDefinition> columns) {
        CellRangeAddress address = rangeAddress.copy();
        if (rangeAddress.getFirstRow() == Range.defaultVal) {
            address.setFirstRow(tabulation.getStartRowIndex());
        }
        if (rangeAddress.getLastRow() == Range.defaultVal) {
            address.setLastRow(tabulation.getStartRowIndex());
        }
        if (rangeAddress.getFirstColumn() == Range.defaultVal) {
            address.setFirstColumn(columns.get(0).getColumnIndex());
        }
        if (rangeAddress.getLastColumn() == Range.defaultVal) {
            address.setLastColumn(columns.get(columns.size() - 1).getColumnIndex());
        }
        return address;
    }

    @Override
    public String getContent() {
        return value;
    }

    @Override
    public BannerDefinitionModifier setContent(String value) {
        this.value = value;
        return this;
    }

    @Override
    public BannerDefinitionModifier setCellStyle(CellStyle style) {
        this.cellStyle = style;
        return this;
    }

    @Override
    public BannerDefinitionModifier setRange(CellRangeAddress cellRangeAddress) {
        this.rangeAddress = cellRangeAddress;
        return this;
    }

    @Override
    public BannerDefinitionModifier setRange(int row1, int row2, int col1, int col2) {
        this.rangeAddress = new CellRangeAddress(row1, row2, col1, col2);
        return null;
    }
}
