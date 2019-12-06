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
public final class MergedRangeHandler {

    private Sheet sheet;

    private CellRangeAddress range;

    public MergedRangeHandler(Sheet sheet, CellRangeAddress range) {
        this.sheet = sheet;
        this.range = range;
    }

    public MergedRangeHandler setContent(double d) {
        POIGadget.getCellForce(POIGadget.getRowForce(sheet, range.getFirstRow()), range.getFirstColumn()).setCellValue(d);
        return this;
    }

    public MergedRangeHandler setContent(String s) {
        POIGadget.getCellForce(POIGadget.getRowForce(sheet, range.getFirstRow()), range.getFirstColumn()).setCellValue(s);
        return this;
    }

    public MergedRangeHandler setContent(boolean b) {
        POIGadget.getCellForce(POIGadget.getRowForce(sheet, range.getFirstRow()), range.getFirstColumn()).setCellValue(b);
        return this;
    }

    public MergedRangeHandler setContent(Date date) {
        POIGadget.getCellForce(POIGadget.getRowForce(sheet, range.getFirstRow()), range.getFirstColumn()).setCellValue(date);
        return this;
    }

    public MergedRangeHandler setContent(Calendar calendar) {
        POIGadget.getCellForce(POIGadget.getRowForce(sheet, range.getFirstRow()), range.getFirstColumn()).setCellValue(calendar);
        return this;
    }

    public MergedRangeHandler setContent(RichTextString richTextString) {
        POIGadget.getCellForce(POIGadget.getRowForce(sheet, range.getFirstRow()), range.getFirstColumn()).setCellValue(richTextString);
        return this;
    }

    public MergedRangeHandler setMergeRangeStyle(CellStyle style) {
        for (int rowIdx = range.getFirstRow() ; rowIdx <= range.getLastRow() ; rowIdx++ ) {
            Row row = POIGadget.getRowForce(sheet, rowIdx);
            for (int columnIdx = range.getFirstColumn() ; columnIdx <= range.getLastColumn() ; columnIdx++ ) {
                Cell cell = POIGadget.getCellForce(row, columnIdx);
                cell.setCellStyle(style);
            }
        }
        return this;
    }


}
