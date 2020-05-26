package cn.kerninventor.tools.poibox.opensource.data.tabulation.writer.tbody;

import cn.kerninventor.tools.poibox.opensource.BoxGadget;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.context.ColumnDefinition;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.context.TableContext;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.writer.ETabulationWriter;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.writer.tbody.col.ColWriter;
import cn.kerninventor.tools.poibox.opensource.utils.ReflectUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;

/**
 * @author Kern
 * @date 2020/5/25 14:19
 * @description
 */
public class TableBodyDataWriter<T> implements TbodyWriter<T> {

    private List<T> datas;
    private ETabulationWriter parentWriter;

    public TableBodyDataWriter(List<T> datas, ETabulationWriter parentWriter) {
        this.datas = datas;
        this.parentWriter = parentWriter;
    }

    @Override
    public void templateTbody(ColumnDefinition<T> columnDefinition, Sheet sheet) {
        TableContext tableContext = columnDefinition.getTableContext();
        ColWriter colWriter = columnDefinition.getColWriter();
        colWriter.pre();
        for (int datasIndex = 0, rowIndex = tableContext.getTbodyFirstRowIndex(); datasIndex < datas.size() ; datasIndex ++ , rowIndex++){
            Row bodyRow = BoxGadget.getRowForce(sheet, rowIndex);
            bodyRow.setHeightInPoints(tableContext.getTbodyRowHeight());
            Cell bodyCell = bodyRow.createCell(columnDefinition.getColumnIndex());
            //设置风格
            bodyCell.setCellStyle(columnDefinition.getTbodyStyle());
            Object value = null;
            try {
                value = ReflectUtil.getFieldValue(columnDefinition.getField(), datas.get(datasIndex));
            } catch (IllegalAccessException e) {
                throw new IllegalArgumentException("Failed to get field value. Field name: " + columnDefinition.getFieldName());
            }
            value = this.parentWriter.translate(columnDefinition.getColumnDataTranslate(), value);
            colWriter.setCellValue(bodyCell, value);

        }
        colWriter.flush();
    }

    private void preNumberStatistics(String fieldName, Number number) {

    }
}
