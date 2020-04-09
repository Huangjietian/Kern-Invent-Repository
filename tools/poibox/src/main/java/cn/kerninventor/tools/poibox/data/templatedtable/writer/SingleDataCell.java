package cn.kerninventor.tools.poibox.data.templatedtable.writer;

import cn.kerninventor.tools.poibox.data.utils.CellValueUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * @author Kern
 * @date 2020/3/17 11:00
 * @description TODO
 */
public class SingleDataCell implements DataCell {

    @Override
    public void setValue(Sheet sheet, Object value, Cell cell, int rowIndex) {
        CellValueUtil.setCellValue(cell, value);
    }
}
