package cn.kern.inventor.tools.poibox;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

/**
 * @Title: POIMultifunctionGadget
 * @ProjectName tools
 * @Description: TODO
 * @Author Kern
 * @Date 2019/10/30 17:58
 */
public final class POIGadget {

    private static final String DEFAULT_CHARSET = "GBK";

    public static Sheet getSheetForce(Workbook workbook, String sheetName) {
        return workbook.getSheet(sheetName) == null ? workbook.createSheet(sheetName) : workbook.getSheet(sheetName);
    }

    public static Sheet getSheetForce(Workbook workbook, int sheetAt) {
        return workbook.getSheetAt(sheetAt) == null ? workbook.createSheet() : workbook.getSheetAt(sheetAt);
    }

    public static Row getRowForce(Sheet sheet, int rowIdx) {
        return sheet.getRow(rowIdx) == null ? sheet.createRow(rowIdx) : sheet.getRow(rowIdx);
    }

    public static Cell getCellForce(Row row, int cellIdx) {
        return row.getCell(cellIdx) == null ? row.createCell(cellIdx) : row.getCell(cellIdx);
    }

    public static String TransferExcelColumnIndex(int cellIndex){
        String cellStrIndex = "";
        int iHead = (cellIndex - 1) / 26;
        int iLeftOver = (cellIndex - 1) % 26;
        if (iHead >= 26) {
            cellStrIndex = TransferExcelColumnIndex(iHead);
        } else if (iHead > 0) {
            cellStrIndex += (char)(64 + iHead);
        }
        cellStrIndex += (char)(65 + iLeftOver);
        return cellStrIndex;
    }

    public static int getCellLenth(Cell cell) throws UnsupportedEncodingException {
        int cellType = cell.getCellType();
        if (cellType == Cell.CELL_TYPE_NUMERIC ){
            double d = cell.getNumericCellValue();
            BigDecimal bd = new BigDecimal(d);
            return String.valueOf(bd).length();
        } else if (cellType == Cell.CELL_TYPE_STRING){
            return cell.getStringCellValue().getBytes(DEFAULT_CHARSET).length;
        } else if (cellType == Cell.CELL_TYPE_BLANK ){
            return 0;
        } else if (cellType == Cell.CELL_TYPE_FORMULA){
            return cell.getCellFormula().getBytes(DEFAULT_CHARSET).length;
        } else if (cellType == Cell.CELL_TYPE_BOOLEAN){
            return (cell.getBooleanCellValue()+"").getBytes(DEFAULT_CHARSET).length;
        } else if (cellType == Cell.CELL_TYPE_ERROR){
            return 0;
        } else {
            return 0;
        }
    }
}
