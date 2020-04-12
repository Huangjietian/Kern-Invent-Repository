package cn.kerninventor.tools.poibox.data.templatedtable.initializer;

import cn.kerninventor.tools.poibox.data.templatedtable.ExcelBanner;
import cn.kerninventor.tools.poibox.data.templatedtable.element.Range;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Kern
 * @date 2020/4/10 10:35
 * @description
 */
public class ExcelBannerInitializer {

    private CellStyle cellStyle;
    private List<CellRangeAddress> rangeAddresses;
    private String value;

    private ExcelBannerInitializer(ExcelTabulationInitializer initializer, ExcelBanner banner) {
        value = banner.value();
        cellStyle = initializer.getParent().styler().generate(banner.style());
        Range[] ranges = banner.range();
        List<CellRangeAddress> rangeAddresses = new LinkedList();
        for (Range range : ranges) {
            CellRangeAddress rangeAddress = new CellRangeAddress(range.fistRow(), range.lastRow(), range.firstCell(), range.lastCell());
            rangeAddresses.add(rangeAddress);
        }
        if (rangeAddresses.isEmpty()){
            CellRangeAddress cellAddresses = new CellRangeAddress(initializer.getStartRowIndex(), initializer.getStartRowIndex(), initializer.getFirstColumnIndex(), initializer.getLastColumnIndex());
            rangeAddresses.add(cellAddresses);
        }
    }

    public static ExcelBannerInitializer newInstance(ExcelTabulationInitializer initializer, ExcelBanner banner) {
        return new ExcelBannerInitializer(initializer, banner);
    }

    public void setBanner(Sheet sheet, ExcelTabulationInitializer initializer) {
        rangeAddresses.forEach(e -> {
            initializer.getParent().layouter().mergedRegion(sheet, e).setMergeRangeStyle(cellStyle).setMergeRangeContent(value);
        });
    }
}
