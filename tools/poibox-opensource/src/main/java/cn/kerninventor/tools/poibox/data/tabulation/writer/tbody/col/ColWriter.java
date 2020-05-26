package cn.kerninventor.tools.poibox.data.tabulation.writer.tbody.col;

import org.apache.poi.ss.usermodel.Cell;

/**
 * @author Kern
 * @date 2020/3/17 10:53
 * @description TODO
 */
public interface ColWriter {

    default void pre() {
        //NOTHING TO DO
    }

    void setCellValue(Cell cell, Object value);

    default void flush() {
        //NOTHING TO DO
    }

}
