package cn.kerninventor.tools.poibox.layout;

import cn.kerninventor.tools.poibox.POIBox;
import cn.kerninventor.tools.poibox.style.POICreator;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * @Title: POILayouterInner
 * @ProjectName tools
 * @Description: TODO
 * @Author Kern
 * @Date 2019/10/30 18:34
 */
public final class POILayouterInner extends POICreator implements POILayouter {

    public POILayouterInner(POIBox poiBox) {
        super(poiBox);
    }

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

    @Override
    public void mergedByColumnContent(Sheet sheet, int column) {
        if (sheet.getLastRowNum() <= 0){
            return;
        }
        String content = "";
        for (Row row : sheet){

        }


    }


}
