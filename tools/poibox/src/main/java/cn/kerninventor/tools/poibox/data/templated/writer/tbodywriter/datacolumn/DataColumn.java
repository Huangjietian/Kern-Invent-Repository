package cn.kerninventor.tools.poibox.data.templated.writer.tbodywriter.datacolumn;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * @author Kern
 * @date 2020/3/17 10:53
 * @description TODO
 */
public interface DataColumn {

    static DataColumn newInstance(boolean isMergeMode, Sheet sheet) {
        if (isMergeMode) {
            return new DataColumnMergeable(sheet);
        }
        return new DataColumnOrdinary();
    }

    void setCellValue(Cell cell, Object value, int rowIndex);

    void flush();
}
