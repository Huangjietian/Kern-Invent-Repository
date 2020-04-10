package cn.kerninventor.tools.poibox.data.templatedtable.writer;

import cn.kerninventor.tools.poibox.BoxGadget;
import cn.kerninventor.tools.poibox.data.templatedtable.initializer.ExcelColumnInitializer;
import cn.kerninventor.tools.poibox.data.templatedtable.initializer.ExcelTabulationInitializer;
import cn.kerninventor.tools.poibox.data.templatedtable.templator.ExcelTabulationTemplator;
import cn.kerninventor.tools.poibox.data.templatedtable.templator.Templator;
import cn.kerninventor.tools.poibox.utils.ReflectUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;

/**
 * @Author Kern
 * @Date 2020/3/12 19:13
 */
public class ExcelTabulationTemplatedWriter<T> extends ExcelTabulationTemplator implements Writer<T> {

    private boolean defaultMergedMode = true;

    public ExcelTabulationTemplatedWriter(ExcelTabulationInitializer initializer) {
        super(initializer);
    }

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
        writeTo(initializer.getParent().getSheet(sheetName), datas, templator);
    }

    @Override
    public void writeTo(int sheetAt, List<T> datas, Templator<T> templator) {
        writeTo(initializer.getParent().getSheet(sheetAt), datas, templator);
    }

    /**
     * @param sheet
     * @param datas
     */
    public void writeTo(Sheet sheet, List<T> datas, Templator<T> templator){
        execute(sheet, datas);
    }

    @Override
    protected void templateTbody(ExcelTabulationInitializer initializer, ExcelColumnInitializer column, DataFormat dataFormat, Sheet sheet, Object... others){
        List datas = (List) others[0];

        //设置单元格格式
        if (column.getDataFormatEx() != null){
            column.getColumnStyle().setDataFormat(dataFormat.getFormat(column.getDataFormatEx()));
        }
        /**
         * DataCell object, to implement merge by cell content;
         */
        DataCell dataCell = DataCell.newInstance(column.isMergeByContent() && defaultMergedMode);
        //行
        for (int datasIndex = 0, rowIndex = initializer.getTableTextRdx(); datasIndex < datas.size() ; datasIndex ++ , rowIndex++){
            Row row = BoxGadget.getRowForce(sheet , rowIndex);
            Cell cell = BoxGadget.getCellForce(row, column.getColumnIndex());
            cell.setCellStyle(column.getColumnStyle());
            Object value = null;
            try {
                value = ReflectUtil.getFieldValue(column.getField(), datas.get(datasIndex));
            } catch (IllegalAccessException e) {
                throw new IllegalArgumentException("Field value get error., field name: " + column.getFieldName());
            }
            //翻译
            if (column.getInterpretor().isInterpretable()) {
                value = column.getInterpretor().interpreteOf(value);
            }
            dataCell.setValue(sheet, value, cell, rowIndex);
        }
        dataCell.setValue(sheet, null, null, 0);
    }

}
