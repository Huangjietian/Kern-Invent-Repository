package cn.kerninventor.tools.poibox.data.templated.initializer;

import cn.kerninventor.tools.poibox.data.templated.ExcelBanner;
import cn.kerninventor.tools.poibox.data.templated.element.Range;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;

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

    public void adjustCellRangeAddress(ExcelTabulationInitializer tabulation) {
        if (rangeAddress.getFirstRow() == Range.defaultVal) {
            rangeAddress.setFirstRow(tabulation.getStartRowIndex());
        }
        if (rangeAddress.getLastRow() == Range.defaultVal) {
            rangeAddress.setLastRow(tabulation.getStartRowIndex());
        }
        if (rangeAddress.getFirstColumn() == Range.defaultVal) {
            rangeAddress.setFirstColumn(tabulation.getFirstColumnIndex());
        }
        if (rangeAddress.getLastColumn() == Range.defaultVal) {
            rangeAddress.setLastColumn(tabulation.getLastColumnIndex());
        }
    }

    public static ExcelBannerInitializer newInstance(ExcelTabulationInitializer tabulation, ExcelBanner banner) {
        return new ExcelBannerInitializer(tabulation, banner);
    }

}
