package cn.kerninventory.tools.excel.anno.writer;

import javafx.scene.control.Cell;

/**
 * <p>一句话描述</p>
 *
 * @author Kern
 */
public interface CellValueWriter {

    void doWrite(Cell cell, Object value);

}
