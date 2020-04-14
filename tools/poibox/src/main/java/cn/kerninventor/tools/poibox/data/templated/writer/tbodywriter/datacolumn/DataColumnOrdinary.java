package cn.kerninventor.tools.poibox.data.templated.writer.tbodywriter.datacolumn;

import cn.kerninventor.tools.poibox.utils.CellValueUtil;
import org.apache.poi.ss.usermodel.Cell;

/**
 * @author Kern
 * @date 2020/3/17 11:00
 * @description TODO
 */
public class DataColumnOrdinary implements DataColumn {

    @Override
    public void setCellValue(Cell cell, Object value, int rowIndex) {
        CellValueUtil.setCellValue(cell, value);
    }

    @Override
    public void flush() {
        //NOTHING TO DO !!!
    }
}
