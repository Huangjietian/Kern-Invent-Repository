package cn.kerninventor.tools.poibox.data.templatedtable.templator;

import cn.kerninventor.tools.poibox.data.templatedtable.element.Banner;
import cn.kerninventor.tools.poibox.data.templatedtable.element.Range;
import cn.kerninventor.tools.poibox.data.templatedtable.initializer.ExcelTabulationInitializer;
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
public class ExcelBannerTemplator {

    private CellStyle cellStyle;
    private List<CellRangeAddress> rangeAddresses;
    private String value;

    public ExcelBannerTemplator(ExcelTabulationInitializer initializer, Banner banner) {
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

    public void setBanner(Sheet sheet, ExcelTabulationInitializer initializer) {
        rangeAddresses.forEach(e -> {
            initializer.getParent().layouter().mergedRegion(sheet, e).setMergeRangeStyle(cellStyle).setMergeRangeContent(value);
        });
    }
}
