package cn.kerninventor.tools.poibox.layout;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * @Title: POILayouterInner
 * @ProjectName tools
 * @Description: TODO
 * @Author Kern
 * @Date 2019/10/30 18:34
 */
public class POILayouterInner implements POILayouter {

    @Override
    public MergedRangeHandler mergedRegion(Sheet sheet, CellRangeAddress cellRangeAddress) {
        sheet.addMergedRegion(cellRangeAddress);
        return new MergedRangeHandler(sheet, cellRangeAddress);
    }

    @Override
    public MergedRangeHandler mergedRegion(Sheet sheet, int topRow, int footRow, int leftColumn, int rightColumn) {
        CellRangeAddress cellRangeAddress = new CellRangeAddress(topRow, footRow, leftColumn, rightColumn);
        return mergedRegion(sheet, cellRangeAddress);
    }

    @Override
    public MergedRangeHandler mergedOneRow(Sheet sheet, int row, int leftColumn, int rightColumn) {
        return mergedRegion(sheet, row, row, leftColumn, rightColumn);
    }

    @Override
    public MergedRangeHandler mergedOneColumn(Sheet sheet, int column, int topRow, int footRow) {
        return mergedRegion(sheet, topRow, footRow, column, column);
    }


}
