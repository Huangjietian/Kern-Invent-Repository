package cn.kerninventor.tools.poibox.data.templated.writer;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * @author Kern
 * @date 2020/3/17 10:53
 * @description TODO
 */
public interface ColWriter {

    static ColWriter newInstance(boolean isMergeMode, Sheet sheet) {
        if (isMergeMode) {
            return new MergeableColWriter(sheet);
        }
        return new OrdinaryColWriter();
    }

    void setCellValue(Cell cell, Object value, int rowIndex);

    void flush();
}
