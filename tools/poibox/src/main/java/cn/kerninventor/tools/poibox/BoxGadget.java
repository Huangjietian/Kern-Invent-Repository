package cn.kerninventor.tools.poibox;

import org.apache.poi.ss.usermodel.*;

import java.io.UnsupportedEncodingException;

/**
 * @Title: POIMultifunctionGadget
 * @ProjectName tools
 * @Description: TODO
 * @Author Kern
 * @Date 2019/10/30 17:58
 */
public class BoxGadget {

    private static POIBox rootBox = POIBoxFactory.open();

    public static POIBox root(){
        return rootBox;
    }

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
        final int DFHIP = 12;
        int size = 0;
        try {
            size = obj.toString().getBytes(DEFAULT_CHARSET).length;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return (int) (adjustCellWidth(size + 2) * fontHeightInPoints / DFHIP);
    }

    public static int adjustCellWidth(int width) {
        return (int) ((width + 0.82) * 256);
    }

    public static Font getFontFrom(CellStyle cellStyle, Workbook workbook) {
        return workbook.getFontAt(cellStyle.getFontIndexAsInt());
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
}
