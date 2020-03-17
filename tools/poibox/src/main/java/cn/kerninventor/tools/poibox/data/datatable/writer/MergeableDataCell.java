package cn.kerninventor.tools.poibox.data.datatable.writer;

import cn.kerninventor.tools.poibox.data.utils.CellValueUtil;
import cn.kerninventor.tools.poibox.layout.LayoutHandler;
import cn.kerninventor.tools.poibox.layout.Layouter;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
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
public class MergeableDataCell implements DataCell{

    private Integer columnIdx, firstRdx, lastRdx;

    private Object temporary;

    @Override
    public void setValue(Sheet sheet, Object value, Cell cell, int rowIndex) {
        if (columnIdx == null) {
            columnIdx = cell.getColumnIndex();
            firstRdx = rowIndex;
            lastRdx = rowIndex;
            temporary = value;
        } else {
            if (temporary != null && temporary.equals(value)) {
                lastRdx = rowIndex ;
            } else {
                if (firstRdx != lastRdx) {
                    Layouter layouter = new LayoutHandler(null);
                    CellRangeAddress range = new CellRangeAddress(firstRdx, lastRdx, columnIdx, columnIdx);
                    layouter.mergedRegion(sheet, range).setMergeRangeContent(temporary);
                } else {
                    CellValueUtil.setCellValue(sheet.getRow(lastRdx).getCell(columnIdx), temporary);
                }
                if (cell != null) {
                    columnIdx = cell.getColumnIndex();
                }
                firstRdx = rowIndex;
                lastRdx = rowIndex;
                temporary = value;
            }
        }
    }
}
