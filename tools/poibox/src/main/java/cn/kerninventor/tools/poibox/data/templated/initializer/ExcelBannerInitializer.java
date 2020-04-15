package cn.kerninventor.tools.poibox.data.templated.initializer;

import cn.kerninventor.tools.poibox.data.templated.ExcelBanner;
import cn.kerninventor.tools.poibox.data.templated.element.Range;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.List;

/**
 * @author Kern
 * @date 2020/4/10 10:35
 * @description
 */
public class ExcelBannerInitializer {

    private CellStyle cellStyle;

    private CellRangeAddress rangeAddress;

    private String value;

    private ExcelBannerInitializer(ExcelTabulationInitializer tabulation, ExcelBanner banner) {
        this.value = banner.value();
        this.cellStyle = tabulation.getParent().styler().generate(banner.style());
        this.rangeAddress = new CellRangeAddress(
                banner.range().fistRow(),
                banner.range().lastRow(),
                banner.range().firstCell(),
                banner.range().lastCell()
        );
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

    public CellRangeAddress adjustCellRangeAddress(ExcelTabulationInitializer tabulation, List<ExcelColumnInitializer> columns) {
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

    public static ExcelBannerInitializer newInstance(ExcelTabulationInitializer tabulation, ExcelBanner banner) {
        return new ExcelBannerInitializer(tabulation, banner);
    }

}
