package cn.kerninventor.tools.poibox.opensource.data.templated.writer.col;

import cn.kerninventor.tools.poibox.opensource.utils.CellValueUtil;
import org.apache.poi.ss.usermodel.Cell;

/**
 * @author Kern
 * @date 2020/3/17 11:00
 * @description TODO
 */
public class GeneralColWriter implements ColWriter {

    @Override
    public void setCellValue(Cell cell, Object value) {
        CellValueUtil.setCellObjectValue(cell, value);
    }

    @Override
    public void flush() {
        //NOTHING TO DO !!!
    }
}
