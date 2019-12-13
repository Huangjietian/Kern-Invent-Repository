package cn.kerninventor.tools.poibox.layout;

import cn.kerninventor.tools.poibox.POIGadget;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.Calendar;
import java.util.Date;

/**
 * @Title: MergedRegion
 * @ProjectName tools
 * @Description: TODO
 * @Author Kern
 * @Date 2019/10/30 17:51
 */
public final class MergedRange {

    private Sheet sheet;

    private CellRangeAddress range;

    public MergedRange(Sheet sheet, CellRangeAddress range) {
        this.sheet = sheet;
        this.range = range;
    }

    public MergedRange setMergeRangeContent(double d) {
        POIGadget.getCellForce(POIGadget.getRowForce(sheet, range.getFirstRow()), range.getFirstColumn()).setCellValue(d);
        return this;
    }

    public MergedRange setMergeRangeContent(String s) {
        POIGadget.getCellForce(POIGadget.getRowForce(sheet, range.getFirstRow()), range.getFirstColumn()).setCellValue(s);
        return this;
    }

    public MergedRange setMergeRangeContent(boolean b) {
        POIGadget.getCellForce(POIGadget.getRowForce(sheet, range.getFirstRow()), range.getFirstColumn()).setCellValue(b);
        return this;
    }

    public MergedRange setMergeRangeContent(Date date) {
        POIGadget.getCellForce(POIGadget.getRowForce(sheet, range.getFirstRow()), range.getFirstColumn()).setCellValue(date);
        return this;
    }

    public MergedRange setMergeRangeContent(Calendar calendar) {
        POIGadget.getCellForce(POIGadget.getRowForce(sheet, range.getFirstRow()), range.getFirstColumn()).setCellValue(calendar);
        return this;
    }

    public MergedRange setMergeRangeContent(RichTextString richTextString) {
        POIGadget.getCellForce(POIGadget.getRowForce(sheet, range.getFirstRow()), range.getFirstColumn()).setCellValue(richTextString);
        return this;
    }

    public MergedRange setMergeRangeStyle(CellStyle style) {
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.cloneStyleFrom(style);
        for (int rowIdx = range.getFirstRow() ; rowIdx <= range.getLastRow() ; rowIdx++ ) {
            Row row = POIGadget.getRowForce(sheet, rowIdx);
            for (int columnIdx = range.getFirstColumn() ; columnIdx <= range.getLastColumn() ; columnIdx++ ) {
                Cell cell = POIGadget.getCellForce(row, columnIdx);
                cell.setCellStyle(cellStyle);
            }
        }
        return this;
    }


}
