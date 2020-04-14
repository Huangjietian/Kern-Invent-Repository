package cn.kerninventor.tools.poibox.data.templated.writer.tbodywriter;

import cn.kerninventor.tools.poibox.BoxGadget;
import cn.kerninventor.tools.poibox.data.templated.initializer.ExcelColumnInitializer;
import cn.kerninventor.tools.poibox.data.templated.initializer.ExcelTabulationInitializer;
import cn.kerninventor.tools.poibox.data.templated.writer.tbodywriter.datacolumn.DataColumn;
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
public class TbodyDataWriter<T> implements TbodyWriter {

    @Override
    public void templateTbody(ExcelTabulationInitializer tabulation, ExcelColumnInitializer column, Sheet sheet, List datas){
        //设置单元格格式
        if (column.getDataFormatEx() != null){
            DataFormat dataFormat = tabulation.getParent().workbook().createDataFormat();
            String expression = column.getDataFormatEx();
            short dataFormatIndex = dataFormat.getFormat(expression);
            column.getColumnStyle().setDataFormat(dataFormatIndex);
        }
        /**
         * DataCell object, to implement merge by cell content;
         */
        DataColumn dataColumn = DataColumn.newInstance(column.isMergeByContent(), sheet);
        //行
        for (int datasIndex = 0, rowIndex = tabulation.getTbodyFirstRowIndex(); datasIndex < datas.size() ; datasIndex ++ , rowIndex++){
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
            dataColumn.setCellValue(cell, value, rowIndex);
        }
        dataColumn.flush();
    }

}
