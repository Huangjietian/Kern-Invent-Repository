package cn.kerninventor.tools.poibox.data.utils;

import cn.kerninventor.tools.poibox.BoxGadget;
import cn.kerninventor.tools.poibox.data.exception.IllegalTypeOfCellValueException;
import cn.kerninventor.tools.poibox.utils.DataTypeGroupUtil;
import cn.kerninventor.tools.poibox.utils.ReflectUtil;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.formula.functions.Value;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.StringUtil;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 * @Title: CellValuer
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.data
 * @Author Kern
 * @Date 2019/12/6 10:07
 * @Description: TODO
 */
public final class CellValueUtil extends BoxGadget {

    /**
     * This method set specified value into cell.
     * @param cell
     * @param value
     */
    public static <T extends Object> void setCellValue(Cell cell, T value) {
        if (value == null) {
            return;
        }
        Class type = value.getClass();
        //Number
        if (DataTypeGroupUtil.isMemberOfIntType(type)){
            cell.setCellValue(((Integer)value));
        } else if (DataTypeGroupUtil.isMemberOfDecimal(type)){
            cell.setCellValue(((Number)value).doubleValue());
        }
        //Date
        else if (DataTypeGroupUtil.isMemberOfDateType(type)) {
            cell.setCellValue((Date)value);
        }
        //LocalDate
        else if (value instanceof LocalDate) {
            cell.setCellValue((Date.from(((LocalDate) value).atStartOfDay(ZoneId.systemDefault()).toInstant())));
        }
        //LocalDateTime
        else if (value instanceof LocalDateTime) {
            cell.setCellValue((LocalDateTime)value);
        }
        //Boolean
        else if (value instanceof Boolean) {
            cell.setCellValue((Boolean)value);
        }
        //Other cases
        else {
            cell.setCellValue(value.toString());
        }
    }

    /**
     * This method return the cell's value by specified type.
     * @param cell
     * @return Object
     */
    public static Object getCellValueBySpecifiedType(Cell cell, Class targetClass) {
        Object ret = null;
        //String
        if (cell.getCellType() == CellType.STRING && String.class == targetClass) {
            String str = cell.getStringCellValue().trim();
            ret = "".equals(str) ? null : str;
        }
        //Date

        else if (isCellDateFormat(cell)) {
            if (LocalDate.class == targetClass){
                ret = cell.getLocalDateTimeCellValue().toLocalDate();
            } else if (LocalDateTime.class == targetClass) {
                ret = cell.getLocalDateTimeCellValue();
            } else if (Date.class.isAssignableFrom(targetClass)){
                ret = cell.getDateCellValue();
            }
        }
        //Double
        else if (cell.getCellType() == CellType.NUMERIC){
            Double doub = cell.getNumericCellValue();
            //decimal
            if (BigDecimal.class.isAssignableFrom(targetClass)){
                ret = BigDecimal.valueOf(doub);
            } else if (Float.class == targetClass) {
                ret = doub.floatValue();
            } else if (Double.class == targetClass) {
                ret = doub;
            }
            //int
            else if (Long.class == targetClass) {
                ret = doub.longValue();
            }
            else {
                //自动装拆包
                ret = doub.intValue();
            }
        }
        //Boolean
        else if (cell.getCellType() == CellType.BOOLEAN && Boolean.class == targetClass) {
            ret = cell.getBooleanCellValue();
        }
        //In other cases , ret == null
        return ret;

    }

    /**
     * This method return the cell's value by specified type.
     * @param cell
     * @return Object
     */
    public static Object getCellValue(Cell cell) {
        //String
        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue().trim();
        }
        //Date
        else if (isCellDateFormat(cell)) {
            return cell.getDateCellValue();
        }
        //Double
        else if (cell.getCellType() == CellType.NUMERIC){
            return cell.getNumericCellValue();
        }
        //Boolean
        else if (cell.getCellType() == CellType.BOOLEAN) {
            return cell.getBooleanCellValue();
        }
        return "";
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
            return String.valueOf(d).length();
        } else if (cellType == CellType.STRING){
            return cell.getStringCellValue().getBytes(DEFAULT_CHARSET).length;
        } else if (cellType == CellType.BLANK){
            return 0;
        } else if (cellType == CellType.FORMULA){
            return cell.getCellFormula().getBytes(DEFAULT_CHARSET).length;
        } else if (cellType == CellType.BOOLEAN){
            return (cell.getBooleanCellValue()+"").getBytes(DEFAULT_CHARSET).length;
        } else if (isCellDateFormat(cell)){
            return cell.getLocalDateTimeCellValue().toString().getBytes(DEFAULT_CHARSET).length;
        } else if (cellType == CellType.ERROR){
            return 0;
        } else {
            return 0;
        }
    }

    private static boolean isCellDateFormat(Cell cell) {
        if (cell == null) {
            return false;
        } else {
            boolean bDate = false;
            if (cell.getCellType() == CellType.NUMERIC) {
                double d = cell.getNumericCellValue();
                if (DateUtil.isValidExcelDate(d)) {
                    ExcelNumberFormat nf = ExcelNumberFormat.from(cell, null);
                    if (nf == null) {
                        return false;
                    }

                    bDate = DateUtil.isADateFormat(nf);
                }
            }
            return bDate;
        }
    }
}
