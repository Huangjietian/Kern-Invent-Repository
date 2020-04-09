package cn.kerninventor.tools.poibox.data.templatedtable.writer;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * @author Kern
 * @date 2020/3/17 10:53
 * @description TODO
 */
@FunctionalInterface
public interface DataCell {

    static DataCell newInstance(boolean isMergeMode) {
        if (isMergeMode) {
            return new MergeableDataCell();
        }
        return new SingleDataCell();
    }

    /**
     *
     * @param sheet
     * @param value
     * @param cell
     * @param rowIndex
     */
    void setValue(Sheet sheet, Object value, Cell cell, int rowIndex);

}
