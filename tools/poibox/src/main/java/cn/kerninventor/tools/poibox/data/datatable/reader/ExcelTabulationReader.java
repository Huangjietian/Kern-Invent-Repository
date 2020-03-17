package cn.kerninventor.tools.poibox.data.datatable.reader;

import cn.kerninventor.tools.poibox.data.datatable.templator.Templator;
import cn.kerninventor.tools.poibox.data.datatable.initializer.ExcelColumnInitializer;
import cn.kerninventor.tools.poibox.data.datatable.initializer.ExcelTabulationInitializer;
import cn.kerninventor.tools.poibox.data.exception.IllegalSourceClassOfTabulationException;
import cn.kerninventor.tools.poibox.data.utils.CellValueUtil;
import cn.kerninventor.tools.poibox.data.utils.InstanceGetter;
import cn.kerninventor.tools.poibox.utils.ReflectUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title ExcelTabulationReader
 * @ProjectName kerninventresp
 * @PackageName cn.kerninventor.tools.poibox.data.datatable.reader
 * @Author Kern
 * @Date 2020/3/12 19:13
 * @Description TODO
 */
public class ExcelTabulationReader<T> implements Reader<T> {

    @Override
    public List<T> readFrom(String sheetName, Templator<T> templator) {
        ExcelTabulationInitializer initializer = ((InstanceGetter<ExcelTabulationInitializer>)templator).getInstance();
        return readFrom(initializer.getParent().workbook().getSheet(sheetName), templator);
    }

    @Override
    public List<T> readFrom(int sheetAt, Templator<T> templator) {
        ExcelTabulationInitializer initializer = ((InstanceGetter<ExcelTabulationInitializer>)templator).getInstance();
        return readFrom(initializer.getParent().workbook().getSheetAt(sheetAt), templator);
    }

    /**
     * TODO 无法解决的问题， 当写入不使用模板时， 读取的起始坐标错误，导致数据读取不全。
     * @param sheet
     * @return
     */
    public List<T> readFrom(Sheet sheet, Templator<T> templator) {
        if (templator == null) {
            throw new IllegalArgumentException("Templator is can't be null!");
        }
        List<T> list = new ArrayList();
        ExcelTabulationInitializer initializer = ((InstanceGetter<ExcelTabulationInitializer>)templator).getInstance();
        List<ExcelColumnInitializer> columnInitializers = initializer.getColumnsContainer();
        for (int i = initializer.getTableTextRdx(); i <= sheet.getLastRowNum() ; i ++) {
            T t = null;
            try {
                t = (T) ReflectUtil.newInstance(initializer.getTabulationClass());
            } catch (Exception e) {
                throw new IllegalSourceClassOfTabulationException("The tabulation Class Missing parameterless constructor! Class: " + initializer.getTabulationClass());
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
            if (nullCount < initializer.getColumnsContainer().size()){
                list.add(t);
            }
        }
        return list;
    }
}
