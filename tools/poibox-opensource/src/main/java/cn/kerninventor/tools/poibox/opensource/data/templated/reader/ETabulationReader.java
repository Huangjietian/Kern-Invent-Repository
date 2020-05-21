package cn.kerninventor.tools.poibox.opensource.data.templated.reader;

import cn.kerninventor.tools.poibox.opensource.data.templated.initializer.EColumnInitiator;
import cn.kerninventor.tools.poibox.opensource.data.templated.initializer.ETabulationInitiator;
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
public class ETabulationReader<T> implements Reader<T> {

    private ETabulationInitiator tabInitiator;

    private List<BeanValidator<T, ?>> beanValidators;

    public ETabulationReader(ETabulationInitiator tabInitiator) {
        this.tabInitiator = Objects.requireNonNull(tabInitiator);
    }

    @Override
    public List<T> readFrom(String sheetName) {
        Sheet sheet = tabInitiator.getParent().workbook().getSheet(sheetName);
        return readFrom(sheet);
    }

    @Override
    public List<T> readFrom(int sheetAt) {
        Sheet sheet = tabInitiator.getParent().workbook().getSheetAt(sheetAt);
        return readFrom(sheet);
    }

    @Override
    public List<T> readFrom(Sheet sheet) {
        tabInitiator.init();
        List<T> list = new ArrayList();
        List<EColumnInitiator> columnInitializers = tabInitiator.getColumnsContainer();
        for (int i = tabInitiator.getTbodyFirstRowIndex(); i <= sheet.getLastRowNum() ; i ++) {
            T t = null;
            Class<T> tClass;
            try {
                 tClass = tabInitiator.getTabulationClass();
                t = ReflectUtil.newInstance(tClass);
            } catch (Exception e) {
                throw new IllegalSourceClassOfTabulationException("The tabulation Class Missing parameterless constructor! Class: " + tabInitiator.getTabulationClass());
            }
            Row row = sheet.getRow(i);
            if (row == null){
                continue;
            }
            int nullCount = 0;
            for (EColumnInitiator column : columnInitializers){
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
            if (nullCount < tabInitiator.getColumnsContainer().size()){
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
    public Reader addBeanValidator(BeanValidator<T, ?>... beanValidators) {
        if (this.beanValidators == null) {
            this.beanValidators = new ArrayList();
        }
        this.beanValidators.addAll(Arrays.stream(beanValidators).collect(Collectors.toList()));
        return this;
    }


}
