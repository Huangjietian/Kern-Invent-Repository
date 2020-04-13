package cn.kerninventor.tools.poibox.data.templatedtable.reader;

import cn.kerninventor.tools.poibox.data.exception.IllegalSourceClassOfTabulationException;
import cn.kerninventor.tools.poibox.data.templatedtable.initializer.ExcelColumnInitializer;
import cn.kerninventor.tools.poibox.data.templatedtable.initializer.ExcelTabulationInitializer;
import cn.kerninventor.tools.poibox.data.utils.CellValueUtil;
import cn.kerninventor.tools.poibox.utils.ReflectUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author Kern
 * @Date 2020/3/12 19:13
 */
public class ExcelTabulationReader<T> implements Reader<T> {

    private ExcelTabulationInitializer tabulationInitializer;

    public ExcelTabulationReader(ExcelTabulationInitializer tabulationInitializer) {
        this.tabulationInitializer = Objects.requireNonNull(tabulationInitializer);
    }

    @Override
    public List<T> readFrom(String sheetName) {
        Sheet sheet = tabulationInitializer.getParent().workbook().getSheet(sheetName);
        return readFrom(sheet);
    }

    @Override
    public List<T> readFrom(int sheetAt) {
        Sheet sheet = tabulationInitializer.getParent().workbook().getSheetAt(sheetAt);
        return readFrom(sheet);
    }

    @Override
    public List<T> readFrom(Sheet sheet) {
        List<T> list = new ArrayList();
        List<ExcelColumnInitializer> columnInitializers = tabulationInitializer.getColumnsContainer();
        for (int i = tabulationInitializer.getTbodyFirstRowIndex(); i <= sheet.getLastRowNum() ; i ++) {
            T t = null;
            try {
                Class<T> tClass = tabulationInitializer.getTabulationClass();
                t = ReflectUtil.newInstance(tClass);
            } catch (Exception e) {
                throw new IllegalSourceClassOfTabulationException("The tabulation Class Missing parameterless constructor! Class: " + tabulationInitializer.getTabulationClass());
            }
            Row row = sheet.getRow(i);
            if (row == null){
                continue;
            }
            int nullCount = 0;
            for (ExcelColumnInitializer column : columnInitializers){
                Cell cell = row.getCell(column.getColumnIndex());
                if (cell == null){
                    nullCount++;
                    continue;
                }
                Object value = null;
                //翻译
                if (column.getInterpretor().isInterpretable()) {
                    value = column.getInterpretor().getMetaDataFrom(cell);
                } else {
                    value = CellValueUtil.getCellValueBySpecifiedType(cell, column.getFieldType());
                }
                if (null == value) {
                    nullCount++;
                }
                try {
                    ReflectUtil.setFieldValue(column.getField(), t, value);
                } catch (IllegalAccessException e) {
                    throw new IllegalArgumentException("Set value to Field error! Field: " + column.getFieldName());
                }
            }
            if (nullCount < tabulationInitializer.getColumnsContainer().size()){
                list.add(t);
            }
        }
        return list;
    }
}
