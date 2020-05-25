package cn.kerninventor.tools.poibox.opensource.data.tabulation.reader;

import cn.kerninventor.tools.poibox.opensource.data.tabulation.context.ColumnDefinition;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.context.TableContext;
import cn.kerninventor.tools.poibox.opensource.exception.IllegalSourceClassOfTabulationException;
import cn.kerninventor.tools.poibox.opensource.utils.BeanUtil;
import cn.kerninventor.tools.poibox.opensource.utils.CellValueUtil;
import cn.kerninventor.tools.poibox.opensource.utils.ReflectUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Kern
 * @date 2020/3/12 19:13
 */
public class ETabulationReader<T> implements TabulationReader<T> {

    private TableContext tabContext;

    private List<BeanValidator<T, ?>> beanValidators;

    public ETabulationReader(TableContext tableContext) {
        this.tabContext = Objects.requireNonNull(tableContext);
    }

    @Override
    public List<T> readFrom(String sheetName) {
        Sheet sheet = tabContext.getParent().workbook().getSheet(sheetName);
        return readFrom(sheet);
    }

    @Override
    public List<T> readFrom(int sheetAt) {
        Sheet sheet = tabContext.getParent().workbook().getSheetAt(sheetAt);
        return readFrom(sheet);
    }

    @Override
    public List<T> readFrom(Sheet sheet) {
        List<T> list = new ArrayList();
        List<ColumnDefinition> columnDefinitions = tabContext.getColumnDefinitions();
        for (int i = tabContext.getTbodyFirstRowIndex(); i <= sheet.getLastRowNum() ; i ++) {
            T t = null;
            Class<T> tClass;
            try {
                 tClass = tabContext.getTabulationClass();
                t = ReflectUtil.newInstance(tClass);
            } catch (Exception e) {
                throw new IllegalSourceClassOfTabulationException("The tabulation Class Missing parameterless constructor! Class: " + tabContext.getTabulationClass());
            }
            Row row = sheet.getRow(i);
            if (row == null){
                continue;
            }
            int nullCount = 0;
            for (ColumnDefinition column : columnDefinitions){
                Cell cell = row.getCell(column.getColumnIndex());
                if (cell == null){
                    nullCount++;
                    continue;
                }
                Object value = null;
                value = CellValueUtil.getCellValueBySpecifiedType(cell, column.getFieldType());
                if (null == value) {
                    nullCount++;
                }
                try {
                    ReflectUtil.setFieldValue(column.getField(), t, value);
                } catch (IllegalAccessException e) {
                    throw new IllegalArgumentException("Set value to Field error! Field: " + column.getFieldName());
                }
            }
            if (nullCount < tabContext.getColumnDefinitions().size()){
                if (BeanUtil.isNotEmpty(beanValidators)) {
                    for (BeanValidator validator : beanValidators) {
                        validator.validate(t);
                    }
                }
                list.add(t);
            }
        }
        return list;
    }

    @Override
    public TabulationReader addBeanValidator(BeanValidator<T, ?>... beanValidators) {
        if (this.beanValidators == null) {
            this.beanValidators = new ArrayList();
        }
        this.beanValidators.addAll(Arrays.stream(beanValidators).collect(Collectors.toList()));
        return this;
    }

}
