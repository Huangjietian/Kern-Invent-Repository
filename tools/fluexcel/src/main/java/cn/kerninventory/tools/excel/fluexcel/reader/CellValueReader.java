package cn.kerninventory.tools.excel.fluexcel.reader;

import javafx.scene.control.Cell;

/**
 * <p>一句话描述</p>
 *
 * @author Kern
 */
public interface CellValueReader {

    <T> T doRead(Cell cell, Class<T> generics);

}
