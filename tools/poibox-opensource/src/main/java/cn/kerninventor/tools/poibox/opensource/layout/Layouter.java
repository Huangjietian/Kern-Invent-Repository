package cn.kerninventor.tools.poibox.opensource.layout;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFTextbox;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFTextBox;

/**
 * @author Kern
 * @date 2019/10/30 17:38
 */
public interface Layouter {

    MergedRange mergedRegion(Sheet sheet, CellRangeAddress cellRangeAddress);

    MergedRange mergedRegion(Sheet sheet, int topRow, int footRow, int leftColumn, int rightColumn);

    MergedRange mergedOneRow(Sheet sheet, int row, int leftColumn, int rightColumn);

    MergedRange mergedOneColumn(Sheet sheet, int column, int topRow, int footRow);

    void mergedByColumnContent(Sheet sheet, int column);

    void mergedByRowContent(Sheet sheet, int row);

    void setCellsSize(Sheet sheet, Float height, Integer width);

    void addTextBox(Sheet sheet, String text, AnchorIndex anchorIndex);

    HSSFTextbox addTextBox(HSSFSheet sheet, AnchorIndex anchorIndex, String text);

    XSSFTextBox addTextBox(XSSFSheet sheet, AnchorIndex anchorIndex, String text);

    Picture addPicture(Sheet sheet, byte[] bytes, AnchorIndex anchorIndex);
}