package cn.kerninventor.tools.poibox.opensource.data.tabulation.writer.tbody.col;

import cn.kerninventor.tools.poibox.opensource.exception.IllegalColumnConfigureException;
import org.apache.poi.ss.usermodel.Cell;

import java.util.Collection;
import java.util.StringJoiner;

/**
 * @author Kern
 * @date 2020/5/6 11:36
 * @description
 */
public class Collection2CharsColWriter implements ColWriter {

    @Override
    public void setCellValue(Cell cell, Object value) {
        if (value == null) {
            return;
        }
        if (value instanceof Collection) {
            Collection collection = (Collection) value;
            if (collection.isEmpty()) {
                return;
            }
            StringJoiner joiner = new StringJoiner(",");
            collection.forEach(e -> {
                if (e != null) {
                    joiner.add(e.toString());
                }
            });
            cell.setCellValue(joiner.toString());
        } else {
            throw new IllegalColumnConfigureException("Collection2CharsColWriter must be used on the Collection field ");
        }
    }

}
