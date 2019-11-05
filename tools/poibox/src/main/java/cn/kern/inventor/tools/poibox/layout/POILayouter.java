package cn.kern.inventor.tools.poibox.layout;

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

    MergedRangeHandler mergedRegion(Sheet sheet, CellRangeAddress cellRangeAddress);

    MergedRangeHandler mergedRegion(Sheet sheet, int topRow, int footRow, int leftColumn, int rightColumn);

    MergedRangeHandler mergedOneRow(Sheet sheet, int row, int leftColumn, int rightColumn);

    MergedRangeHandler mergedOneColumn(Sheet sheet, int column, int topRow, int footRow);


}
