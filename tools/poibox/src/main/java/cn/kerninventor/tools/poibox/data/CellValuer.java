package cn.kerninventor.tools.poibox.data;

import cn.kerninventor.tools.poibox.POIGadget;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

/**
 * @Title: CellValuer
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.data
 * @Author Kern
 * @Date 2019/12/6 10:07
 * @Description: TODO
 */
public final class CellValuer extends POIGadget {

    /**
     * This method returns the string value of the cell's value
     * @param cell
     * @return Object
     */
    public static Object getCellValue(Cell cell) {
        if (cell.getCellType() == CellType.STRING || cell.getCellType() == CellType.BLANK) {
            return cell.getStringCellValue().trim();
        }
        else if (HSSFDateUtil.isCellDateFormatted(cell)) {
            return cell.getDateCellValue();
        }
        else {
            return cell.getNumericCellValue();
        }
    }

    /**
     * This method transfer excel column index to english form.
     * @param cellIndex
     * @return
     */
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

    /**
     * This method return cell value lenth to calibrate column width.
     * @param cell
     * @return
     * @throws UnsupportedEncodingException
     */
    public static int getCellValueLenth(Cell cell) throws UnsupportedEncodingException {
        CellType cellType = cell.getCellType();
        if (cellType == CellType.NUMERIC){
            double d = cell.getNumericCellValue();
            BigDecimal bd = new BigDecimal(d);
            return String.valueOf(bd).length();
        } else if (cellType == CellType.STRING){
            return cell.getStringCellValue().getBytes(DEFAULT_CHARSET).length;
        } else if (cellType == CellType.BLANK){
            return 0;
        } else if (cellType == CellType.FORMULA){
            return cell.getCellFormula().getBytes(DEFAULT_CHARSET).length;
        } else if (cellType == CellType.BOOLEAN){
            return (cell.getBooleanCellValue()+"").getBytes(DEFAULT_CHARSET).length;
        } else if (cellType == CellType.ERROR){
            return 0;
        } else {
            return 0;
        }
    }
}
