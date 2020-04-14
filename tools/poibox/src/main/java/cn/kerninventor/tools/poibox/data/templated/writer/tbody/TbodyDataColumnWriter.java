package cn.kerninventor.tools.poibox.data.templated.writer.tbody;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * @author Kern
 * @date 2020/3/17 10:53
 * @description TODO
 */
public interface TbodyDataColumnWriter {

    static TbodyDataColumnWriter newInstance(boolean isMergeMode, Sheet sheet) {
        if (isMergeMode) {
            return new TbodyDataColumnWriterMergeable(sheet);
        }
        return new TbodyDataColumnWriterOrdinary();
    }

    void setCellValue(Cell cell, Object value, int rowIndex);

    void flush();
}
