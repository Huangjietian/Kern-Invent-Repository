package cn.kerninventor.tools.poibox.data.tabulation.writer.tbody.col;

import cn.kerninventor.tools.poibox.utils.CellValueUtil;
import cn.kerninventor.tools.poibox.layout.LayoutHandler;
import cn.kerninventor.tools.poibox.layout.Layouter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * @author Kern
 * @date 2020/3/17 10:28
 */
public class MergeAbleColWriter implements ColWriter {

    private Sheet sheet;
    private Integer columnIdx;
    private Integer firstRdx;
    private Integer lastRdx;
    private Object temporary;

    @Override
    public void setCellValue(Cell cell, Object value) {
        if (sheet == null) {
            sheet = cell.getSheet();
        }
        int rowIndex = cell.getRow().getRowNum() - 1;
        if (columnIdx == null) {
            columnIdx = cell.getColumnIndex();
            firstRdx = rowIndex;
            lastRdx = rowIndex;
            temporary = value;
        } else {
            if (temporary != null && temporary.equals(value)) {
                lastRdx = rowIndex ;
            } else {
                if (firstRdx != lastRdx) {
                    flush();
                } else {
                    CellValueUtil.setCellObjectValue(sheet.getRow(lastRdx).getCell(columnIdx), temporary);
                }
                firstRdx = rowIndex;
                lastRdx = rowIndex;
                temporary = value;
            }
        }
    }

    @Override
    public void flush() {
        Layouter layouter = new LayoutHandler(null);
        CellRangeAddress range = new CellRangeAddress(firstRdx, lastRdx, columnIdx, columnIdx);
        layouter.mergedRegion(sheet, range).setMergeRangeContent(temporary);
    }
}