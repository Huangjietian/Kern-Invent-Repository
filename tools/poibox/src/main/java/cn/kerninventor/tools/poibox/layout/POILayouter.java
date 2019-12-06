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
public interface POILayouter {

    /**
     * 合并单元格
     * @param sheet
     * @param cellRangeAddress
     * @return
     */
    MergedRangeHandler mergedRegion(Sheet sheet, CellRangeAddress cellRangeAddress);

    /**
     * 合并单元格
     * @param sheet
     * @param topRow
     * @param footRow
     * @param leftColumn
     * @param rightColumn
     * @return
     */
    MergedRangeHandler mergedRegion(Sheet sheet, int topRow, int footRow, int leftColumn, int rightColumn);

    /**
     * 合并单元格
     * @param sheet
     * @param row
     * @param leftColumn
     * @param rightColumn
     * @return
     */
    MergedRangeHandler mergedOneRow(Sheet sheet, int row, int leftColumn, int rightColumn);

    /**
     * 合并单元格
     * @param sheet
     * @param column
     * @param topRow
     * @param footRow
     * @return
     */
    MergedRangeHandler mergedOneColumn(Sheet sheet, int column, int topRow, int footRow);

    /**
     * 按照内容合并单元格
     * @param sheet
     * @param column
     */
    void mergedByColumnContent(Sheet sheet, int column);
}
