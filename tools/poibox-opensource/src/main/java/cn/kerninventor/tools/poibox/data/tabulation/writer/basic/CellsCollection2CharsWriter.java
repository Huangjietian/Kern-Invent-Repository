package cn.kerninventor.tools.poibox.data.tabulation.writer.basic;

import cn.kerninventor.tools.poibox.exception.IllegalColumnConfigureException;
import cn.kerninventor.tools.poibox.exception.UnSupportedDataTypeException;
import org.apache.poi.ss.usermodel.Cell;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.StringJoiner;

/**
 * @author Kern
 * @date 2020/5/6 11:36
 * @description
 */
public class CellsCollection2CharsWriter implements CellsWriter {

    @Override
    public void supportedDataType(Field field) {
        if (!Collection.class.isAssignableFrom(field.getType())) {
            throw new UnSupportedDataTypeException("" +
                    "The Field data type is not supported when using the Collection2CharsColWriter!" +
                    System.lineSeparator() +
                    "Please check the file type is assignable by java.util.Collection class! Field name: " + field.getName());
        }
    }

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
