package cn.kerninventor.tools.poibox.layout;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * @Author Kern
 * @Date 2019/10/30 17:38
 */
public interface Layouter {

    MergedRange mergedRegion(Sheet sheet, CellRangeAddress cellRangeAddress);

    MergedRange mergedRegion(Sheet sheet, int topRow, int footRow, int leftColumn, int rightColumn);

    MergedRange mergedOneRow(Sheet sheet, int row, int leftColumn, int rightColumn);

    MergedRange mergedOneColumn(Sheet sheet, int column, int topRow, int footRow);

    void mergedByColumnContent(Sheet sheet, int column);

    void mergedByRowContent(Sheet sheet, int row);

    void setCellsSize(Sheet sheet, Float height, Integer width);
}
