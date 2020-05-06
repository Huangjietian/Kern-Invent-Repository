package cn.kerninventor.tools.poibox.opensource.data.templated.writer.col;

import org.apache.poi.ss.usermodel.Cell;

/**
 * @author Kern
 * @date 2020/3/17 10:53
 * @description TODO
 */
public interface ColWriter {

    void setCellValue(Cell cell, Object value);

    void flush();
}
