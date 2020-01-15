package cn.kerninventor.tools.poibox.data.utils;

import cn.kerninventor.tools.poibox.POIGadget;
import cn.kerninventor.tools.poibox.data.exception.IllegalTypeOfCellValueException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Title: CellValuer
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.data
 * @Author Kern
 * @Date 2019/12/6 10:07
 * @Description: TODO
 */
public final class CellValueUtil extends POIGadget {

    /**
     * This method returns the string value of the cell's value
     * @param cell
     * @return Object
     */
    public static Object getCellValue(Cell cell) {
        //String
        if (cell.getCellType() == CellType.STRING || cell.getCellType() == CellType.BLANK) {
            String value = cell.getStringCellValue().trim();
            value = "".equals(value) ? null : value;
            return value;
        }
        //Date
        else if (DateUtil.isCellDateFormatted(cell)) {
            return cell.getDateCellValue();
        }
        //Double
        else if (cell.getCellType() == CellType.NUMERIC){
            return cell.getNumericCellValue();
        }
        else {
            return null;
        }
    }

    /**
     * return Date,BigDecimal,Double,Long,Integer,Short,Boolean,String
     * @param cell
     * @param type
     * @return
     */
    public static Object getSpecifiedTypeCellValue(Cell cell, Class type) throws IllegalTypeOfCellValueException {
        try {
            if (String.class == type){
                String str = "".equals(cell.getStringCellValue().trim()) ? null : cell.getStringCellValue();
                return str;
            } else if (Date.class == type) {
                return cell.getDateCellValue();
            } else if (BigDecimal.class == type){
                return new BigDecimal(cell.getNumericCellValue());
            } else if (Double.class == type){
                return cell.getNumericCellValue();
            } else if (Long.class == type) {
                return new Double(cell.getNumericCellValue()).longValue();
            } else if (Integer.class == type) {
                return new Double(cell.getNumericCellValue()).intValue();
            } else if (Short.class == type) {
                return new Double(cell.getNumericCellValue()).shortValue();
            } else if (Boolean.class == type) {
                return cell.getBooleanCellValue();
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new IllegalTypeOfCellValueException();
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
