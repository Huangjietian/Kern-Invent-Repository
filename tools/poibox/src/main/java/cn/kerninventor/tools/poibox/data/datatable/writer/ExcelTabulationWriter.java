package cn.kerninventor.tools.poibox.data.datatable.writer;

import cn.kerninventor.tools.poibox.BoxGadget;
import cn.kerninventor.tools.poibox.data.datatable.initializer.ExcelColumnInitializer;
import cn.kerninventor.tools.poibox.data.datatable.initializer.ExcelTabulationInitializer;
import cn.kerninventor.tools.poibox.data.datatable.templator.ExcelTabulationTemplator;
import cn.kerninventor.tools.poibox.data.datatable.templator.Templator;
import cn.kerninventor.tools.poibox.data.utils.CellValueUtil;
import cn.kerninventor.tools.poibox.data.utils.InstanceGetter;
import cn.kerninventor.tools.poibox.utils.ReflectUtil;
import org.apache.poi.ss.usermodel.*;

import java.util.List;

/**
 * @Title ExcelTabulationWriter
 * @ProjectName kerninventresp
 * @PackageName cn.kerninventor.tools.poibox.data.datatable.writer
 * @Author Kern
 * @Date 2020/3/12 19:13
 * @Description TODO
 */
public class ExcelTabulationWriter<T> implements Writer<T> {

    private boolean defaultMergedMode = true;

    @Override
    public boolean getDefaultMergedMode() {
        return defaultMergedMode;
    }

    @Override
    public Writer setDefaultMergedMode(boolean isMergeColum) {
        defaultMergedMode = isMergeColum;
        return this;
    }

    @Override
    public void writeTo(String sheetName, List<T> datas, Templator<T> templator) {
        ExcelTabulationInitializer initializer = ((InstanceGetter<ExcelTabulationInitializer>)templator).getInstance();
        writeTo(initializer.getParent().getSheet(sheetName), datas, templator);
    }

    @Override
    public void writeTo(int sheetAt, List<T> datas, Templator<T> templator) {
        ExcelTabulationInitializer initializer = ((InstanceGetter<ExcelTabulationInitializer>)templator).getInstance();
        writeTo(initializer.getParent().getSheet(sheetAt), datas, templator);
    }

    /**
     * @param sheet
     * @param datas
     */
    public void writeTo(Sheet sheet, List<T> datas, Templator<T> templator){
        if (templator == null) {
            throw new IllegalArgumentException("Templator is can't be null!");
        }
        if (datas == null || datas.isEmpty()) {
            return;
        }
        ((ExcelTabulationTemplator)templator).execute(sheet, false);

        ExcelTabulationInitializer initializer = ((InstanceGetter<ExcelTabulationInitializer>)templator).getInstance();
        initializer.setTextRowNum(datas.size());
        int start = initializer.getTableTextRdx();
        List<ExcelColumnInitializer> columnInitializers = initializer.getColumnsContainer();
        DataFormat dataFormat = sheet.getWorkbook().createDataFormat();
        //列
        for (ExcelColumnInitializer column : columnInitializers) {
            //设置单元格格式
            if (column.getDataFormatEx() != null){
                column.getColumnStyle().setDataFormat(dataFormat.getFormat(column.getDataFormatEx()));
            }
            /**
             * DataCell object, to implement merge by cell content;
             */
            DataCell dataCell = DataCell.newInstance(column.isMergeByContent() && defaultMergedMode);
            //行
            for (int i = 0; i < datas.size() ; i ++ ){
                Row row = BoxGadget.getRowForce(sheet , start);
                Cell cell = BoxGadget.getCellForce(row, column.getColumnIndex());
                cell.setCellStyle(column.getColumnStyle());
                Object value = null;
                try {
                    value = ReflectUtil.getFieldValue(column.getField(), datas.get(i));
                } catch (IllegalAccessException e) {
                    throw new IllegalArgumentException("Field value get error., field name: " + column.getFieldName());
                }
                //翻译
                if (column.getInterpretor().isInterpretable()) {
                    value = column.getInterpretor().interpreteOf(value);
                }
                dataCell.setValue(sheet, value, cell, start++);
            }
            dataCell.setValue(sheet, null, null, 0);
            start -= datas.size();
        }
    }
}
