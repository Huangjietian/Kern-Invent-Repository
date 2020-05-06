package cn.kerninventor.tools.poibox.opensource;

import cn.kerninventor.tools.poibox.opensource.developer.SealingVersion;
import cn.kerninventor.tools.poibox.opensource.style.Fonter;
import org.apache.poi.ss.usermodel.*;

import java.io.UnsupportedEncodingException;

/**
 * @uthor Kern
 * @date 2019/10/30 17:58
 */
@SealingVersion(
        version = 1.00,
        description = "Poi common util!",
        zh_description = "POIBox 通用工具类, " +
                "1. 提供获取表格各对象的功能；" +
                "2. 提供获取调整后列宽的功能；" +
                "3. 提供调整列宽的功能；" +
                "4. 提供转换列数字下标为英文下标的功能",
        possible = "New general functionality will be added based on the continuous extension."
)
public class BoxGadget {

    protected static final String DEFAULT_CHARSET = "GBK";

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

    public static int getCellWidthByContent(Object obj, int fontHeightInPoints) {
        int size = 0;
        int cellWidthAdjustIncrement = 2;
        try {
            size = obj.toString().getBytes(DEFAULT_CHARSET).length;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        size += cellWidthAdjustIncrement;
        return adjustCellWidth(size) * fontHeightInPoints / Fonter.DEFAULT_FONT_HEIGHT_IN_POINTS;
    }

    public static int adjustCellWidth(int width) {
        if (0 == width) {
            return 0;
        }
        return (int) ((width + 0.82) * 256);
    }

    public static Font getFontFrom(CellStyle cellStyle, Workbook workbook) {
        return workbook.getFontAt(cellStyle.getFontIndexAsInt());
    }

    public static String transferExcelColumnIndex(int cellIndex){
        String cellStrIndex = "";
        int iHead = (cellIndex - 1) / 26;
        int iLeftOver = (cellIndex - 1) % 26;
        if (iHead >= 26) {
            cellStrIndex = transferExcelColumnIndex(iHead);
        } else if (iHead > 0) {
            cellStrIndex += (char)(64 + iHead);
        }
        cellStrIndex += (char)(65 + iLeftOver);
        return cellStrIndex;
    }
}
