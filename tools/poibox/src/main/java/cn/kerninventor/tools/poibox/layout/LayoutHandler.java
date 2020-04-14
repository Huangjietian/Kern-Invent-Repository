package cn.kerninventor.tools.poibox.layout;

import cn.kerninventor.tools.poibox.BoxBracket;
import cn.kerninventor.tools.poibox.BoxGadget;
import cn.kerninventor.tools.poibox.POIBox;
import cn.kerninventor.tools.poibox.utils.CellValueUtil;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFTextBox;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kern
 * @date 2019/10/30 18:34
 */
public final class LayoutHandler extends BoxBracket implements Layouter {

    public LayoutHandler(POIBox poiBox) {
        super(poiBox);
    }

    @Override
    public MergedRange mergedRegion(Sheet sheet, CellRangeAddress cellRangeAddress) {
        sheet.addMergedRegion(cellRangeAddress);
        return new MergedRange(sheet, cellRangeAddress);
    }

    @Override
    public MergedRange mergedRegion(Sheet sheet, int topRow, int footRow, int leftColumn, int rightColumn) {
        CellRangeAddress cellRangeAddress = new CellRangeAddress(topRow, footRow, leftColumn, rightColumn);
        return mergedRegion(sheet, cellRangeAddress);
    }

    @Override
    public MergedRange mergedOneRow(Sheet sheet, int row, int leftColumn, int rightColumn) {
        return mergedRegion(sheet, row, row, leftColumn, rightColumn);
    }

    @Override
    public MergedRange mergedOneColumn(Sheet sheet, int column, int topRow, int footRow) {
        return mergedRegion(sheet, topRow, footRow, column, column);
    }

    @Override
    public void mergedByColumnContent(Sheet sheet, int column) {
        if (sheet.getLastRowNum() <= 0){
            return;
        }
        Map<Object, CellRangeAddress> map = new HashMap<>();
        for (Row row : sheet){
            Cell cell = row.getCell(column);
            if (cell == null) {
                continue;
            }
            Object value = CellValueUtil.getCellValue(cell);
            //diverse value , to merge already recorded and reset map.
            CellRangeAddress address = map.get(value);
            if (address == null){
                map.values().forEach(e -> {
                    if (e.getFirstRow() != e.getLastRow()){
                        sheet.addMergedRegion(e);
                    }
                });
                map.clear();
                map.put(value, new CellRangeAddress(row.getRowNum(), row.getRowNum(), column, column));
            } else {
                address.setLastRow(row.getRowNum());
            }
        }
        if (!map.isEmpty()){
            map.values().forEach(e -> {
                if (e.getFirstRow() != e.getLastRow()){
                    sheet.addMergedRegion(e);
                }
            });
            map.clear();
        }
    }

    @Override
    public void mergedByRowContent(Sheet sheet, int row) {
        Row mergedRow;
        if ((mergedRow = sheet.getRow(row)) == null || mergedRow.getLastCellNum() <= 0){
            return;
        }
        Map<Object, CellRangeAddress> map = new HashMap<>();
        for (Cell cell : mergedRow){
            Object value = CellValueUtil.getCellValue(cell);
            //diverse value , to merge already recorded and reset map.
            CellRangeAddress address = map.get(value);
            if (address == null){
                map.values().forEach(e -> {
                    if (e.getFirstColumn() != e.getLastColumn()){
                        sheet.addMergedRegion(e);
                    }
                });
                map.clear();
                map.put(value, new CellRangeAddress(mergedRow.getRowNum(), mergedRow.getRowNum(), cell.getColumnIndex(), cell.getColumnIndex()));
            } else {
                address.setLastColumn(cell.getColumnIndex());
            }
        }
        if (!map.isEmpty()){
            map.values().forEach(e -> {
                if (e.getFirstColumn() != e.getLastColumn()){
                    sheet.addMergedRegion(e);
                }
            });
            map.clear();
        }
    }

    @Override
    public void setCellsSize(Sheet sheet, Float height, Integer width) {
        final short[] columnIndex = {0, 0};
        sheet.forEach(r -> {
            if (height != null){
                r.setHeightInPoints(height);
            }
            columnIndex[0] = r.getFirstCellNum() > columnIndex[1] ? r.getFirstCellNum() : columnIndex[1];
            columnIndex[1] = r.getLastCellNum() - 1 > columnIndex[1] ? (short) (r.getLastCellNum() - 1) : columnIndex[1];
        });
        if (width != null){
            for (int i = 0 ; i < columnIndex[0] ; i++ ){
                sheet.setColumnWidth(i , BoxGadget.adjustCellWidth(width));
            }
        }
    }

    @Override
    public void addTextBox(Sheet sheet, AnchorIndex anchorIndex, String text) {
        if (HSSFSheet.class.isAssignableFrom(sheet.getClass())) {
            addTextBox((HSSFSheet) sheet, anchorIndex, text);
        } else if (XSSFSheet.class.isAssignableFrom(sheet.getClass())){
            addTextBox((XSSFSheet) sheet, anchorIndex, text);
        } else {
            throw new IllegalArgumentException("Unsupported sheet type");
        }
    }

    @Override
    public void addTextBox(HSSFSheet sheet, AnchorIndex anchorIndex, String text) {
        HSSFPatriarch drawing = sheet.createDrawingPatriarch();
        HSSFClientAnchor clientAnchor = drawing.createAnchor(
                anchorIndex.getLeft(),
                anchorIndex.getTop(),
                anchorIndex.getRight(),
                anchorIndex.getBottom(),
                anchorIndex.getCol1(),
                anchorIndex.getRow1(),
                anchorIndex.getCol2(),
                anchorIndex.getRow2()
                );
        HSSFTextbox textBox = drawing.createTextbox(clientAnchor);
        textBox.setString(new HSSFRichTextString(text));
    }

    @Override
    public void addTextBox(XSSFSheet sheet, AnchorIndex anchorIndex, String text) {
        XSSFDrawing drawing = sheet.createDrawingPatriarch();
        XSSFClientAnchor clientAnchor = drawing.createAnchor(
                anchorIndex.getLeft(),
                anchorIndex.getTop(),
                anchorIndex.getRight(),
                anchorIndex.getBottom(),
                anchorIndex.getCol1(),
                anchorIndex.getRow1(),
                anchorIndex.getCol2(),
                anchorIndex.getRow2()
        );
        XSSFTextBox textBox = drawing.createTextbox(clientAnchor);
        textBox.setText(text);
    }


}
