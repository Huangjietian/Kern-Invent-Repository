package cn.kerninventor.tools.poibox.data.templatedtable.writer.tbodywriter.datacolumn;

import cn.kerninventor.tools.poibox.data.utils.CellValueUtil;
import cn.kerninventor.tools.poibox.layout.LayoutHandler;
import cn.kerninventor.tools.poibox.layout.Layouter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * @Title DataColumn
 * @ProjectName kerninventresp
 * @PackageName cn.kerninventor.tools.poibox.data.datatable.writer
 * @Author Kern
 * @Date 2020/3/17 10:28
 * @Description TODO
 */
public class DataColumnMergeable implements DataColumn {

    private Sheet sheet;

    private Integer columnIdx;

    private Integer firstRdx;

    private Integer lastRdx;

    private Object temporary;

    public DataColumnMergeable(Sheet sheet) {
        this.sheet = sheet;
    }

    @Override
    public void setCellValue(Cell cell, Object value, int rowIndex) {
        if (columnIdx == null) {
            if (cell != null) {
                columnIdx = cell.getColumnIndex();
            }
            firstRdx = rowIndex;
            lastRdx = rowIndex;
            temporary = value;
        } else {
            if (temporary != null && temporary.equals(value)) {
                lastRdx = rowIndex ;
            } else {
                if (firstRdx != lastRdx) {
                    flush();
                } else {
                    CellValueUtil.setCellValue(sheet.getRow(lastRdx).getCell(columnIdx), temporary);
                }
                firstRdx = rowIndex;
                lastRdx = rowIndex;
                temporary = value;
            }
        }
    }

    @Override
    public void flush() {
        Layouter layouter = new LayoutHandler(null);
        CellRangeAddress range = new CellRangeAddress(firstRdx, lastRdx, columnIdx, columnIdx);
        layouter.mergedRegion(sheet, range).setMergeRangeContent(temporary);
    }
}
