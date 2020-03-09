package cn.kerninventor.tools.poibox.layout;

import cn.kerninventor.tools.poibox.POIBox;
import cn.kerninventor.tools.poibox.BoxBracket;
import cn.kerninventor.tools.poibox.data.utils.CellValueUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title: POILayouterInner
 * @ProjectName tools
 * @Description: TODO
 * @Author Kern
 * @Date 2019/10/30 18:34
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
            if (cell != null){
                int rowIndex = row.getRowNum();
                Object value = CellValueUtil.getCellValue(cell);
                CellRangeAddress address = map.get(value);
                //diverse value , to merge already recorded and reset map.
                if (address == null){
                    map.values().forEach(e -> {
                        int first = e.getFirstRow();
                        int last = e.getLastRow();
                        if (first != last){
                            sheet.addMergedRegion(e);
                        }
                    });
                    map.clear();
                    map.put(value, new CellRangeAddress(rowIndex, rowIndex, column, column));
                } else {
                    address.setLastRow(rowIndex);
                }
            }
        }
        if (!map.isEmpty()){
            map.values().forEach(e -> {
                int first = e.getFirstRow();
                int last = e.getLastRow();
                if (first != last){
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
        int rowIndex = mergedRow.getRowNum();
        Map<Object, CellRangeAddress> map = new HashMap<>();
        for (Cell cell : mergedRow){
            int cellIndex = cell.getColumnIndex();
            Object value = CellValueUtil.getCellValue(cell);
            CellRangeAddress address = map.get(value);
            //diverse value , to merge already recorded and reset map.
            if (address == null){
                map.values().forEach(e -> {
                    int first = e.getFirstColumn();
                    int last = e.getLastColumn();
                    if (first != last){
                        sheet.addMergedRegion(e);
                    }
                });
                map.clear();
                map.put(value, new CellRangeAddress(rowIndex, rowIndex, cellIndex, cellIndex));
            } else {
                address.setLastColumn(cellIndex);
            }
        }
        if (!map.isEmpty()){
            map.values().forEach(e -> {
                int first = e.getFirstColumn();
                int last = e.getLastColumn();
                if (first != last){
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
                sheet.setColumnWidth(i , (int)((width + 0.72) * 256));
            }
        }
    }


}
