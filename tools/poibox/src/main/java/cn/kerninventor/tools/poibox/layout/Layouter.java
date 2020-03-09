package cn.kerninventor.tools.poibox.layout;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * @Title: POILayouter
 * @ProjectName tools
 * @Description: TODO
 * @Author Kern
 * @Date 2019/10/30 17:38
 */
public interface Layouter {

    /**
     * merge cells
     * @param sheet
     * @param cellRangeAddress
     * @return
     */
    MergedRange mergedRegion(Sheet sheet, CellRangeAddress cellRangeAddress);

    /**
     * merge cells
     * @param sheet
     * @param topRow
     * @param footRow
     * @param leftColumn
     * @param rightColumn
     * @return
     */
    MergedRange mergedRegion(Sheet sheet, int topRow, int footRow, int leftColumn, int rightColumn);

    /**
     * merge cells on one row
     * @param sheet
     * @param row
     * @param leftColumn
     * @param rightColumn
     * @return
     */
    MergedRange mergedOneRow(Sheet sheet, int row, int leftColumn, int rightColumn);

    /**
     * merge cells on one column
     * @param sheet
     * @param column
     * @param topRow
     * @param footRow
     * @return
     */
    MergedRange mergedOneColumn(Sheet sheet, int column, int topRow, int footRow);

    /**
     * merge cells on one column by content
     * @param sheet
     * @param column
     */
    void mergedByColumnContent(Sheet sheet, int column);

    /**
     * merge cells on one row by content
     * @param sheet
     * @param row
     */
    void mergedByRowContent(Sheet sheet, int row);

    /**
     * set cells size (height and width)
     * @param sheet
     * @param height
     * @param width
     */
    void setCellsSize(Sheet sheet, Float height, Integer width);
}
