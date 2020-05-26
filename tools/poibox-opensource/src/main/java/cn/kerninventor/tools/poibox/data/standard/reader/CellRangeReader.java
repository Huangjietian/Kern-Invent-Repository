package cn.kerninventor.tools.poibox.data.standard.reader;

import cn.kerninventor.tools.poibox.utils.CellValueUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kern
 * @date 2020/5/22 17:20
 * @description
 */
public class CellRangeReader implements StandardReader<DataRange, CellRangeAddress> {

    public DataRange readFrom(Sheet sheet, CellRangeAddress range) {
        DataRange dataRange = new DataRange(range);
        if (sheet != null) {
            List<DataRow> dataRows = new ArrayList<>(sheet.getLastRowNum());
            for (int rowIndex = range.getFirstRow() ; rowIndex <= range.getLastRow() ; rowIndex ++) {
                DataRow dataRow = new DataRow(rowIndex);
                Row row = sheet.getRow(rowIndex);
                if (row != null) {
                    List<DataCell> dataCells = new ArrayList<>(row.getLastCellNum());
                    for (int cellIndex = range.getFirstColumn() ; cellIndex <= range.getLastColumn() ; cellIndex ++) {
                        DataCell dataCell = new DataCell(cellIndex);
                        Cell cell = row.getCell(cellIndex);
                        if (cell != null) {
                            Object value = CellValueUtil.getCellValue(cell);
                            dataCell.setValue(value);
                        }
                        dataCells.add(dataCell);
                    }
                    dataRow.setDataCells(dataCells);
                }
                dataRows.add(dataRow);
            }
            dataRange.setDataRows(dataRows);
        }
        return dataRange;
    }

}
