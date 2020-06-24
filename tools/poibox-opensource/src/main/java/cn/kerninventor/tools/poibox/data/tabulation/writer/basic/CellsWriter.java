package cn.kerninventor.tools.poibox.data.tabulation.writer.basic;

import org.apache.poi.ss.usermodel.Cell;

import java.lang.reflect.Field;

/**
 * @author Kern
 * @date 2020/3/17 10:53
 * @description TODO
 */
public interface CellsWriter {

    default void pre() {
        //To do prepare work before forEach.
    }

    void supportedDataType(Field field);

    void setCellValue(Cell cell, Object value);

    default void flush() {
        //To do finish work after forEach.
    }


}
