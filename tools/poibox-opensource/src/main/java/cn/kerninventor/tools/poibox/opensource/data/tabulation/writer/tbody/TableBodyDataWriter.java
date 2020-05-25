package cn.kerninventor.tools.poibox.opensource.data.tabulation.writer.tbody;

import cn.kerninventor.tools.poibox.opensource.BoxGadget;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.context.ColumnDefinition;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.context.TableContext;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.writer.ETabulationWriter;
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

    public TableBodyDataWriter(List<T> datas) {
        this.datas = datas;
    }

    @Override
    public void templateTbody(ColumnDefinition<T> columnDefinition, Sheet sheet) {
        TableContext table = columnDefinition.getTableContext();
        columnDefinition.getColWriter().pre();
        for (int datasIndex = 0, rowIndex = table.getTbodyFirstRowIndex(); datasIndex < datas.size() ; datasIndex ++ , rowIndex++){
            Row bodyRow = BoxGadget.getRowForce(sheet, rowIndex);
            ETabulationWriter.setRowHeight(bodyRow, table.getTbodyRowHeight());
            Cell bodyCell = bodyRow.createCell(columnDefinition.getColumnIndex());
            //设置风格
            bodyCell.setCellStyle(columnDefinition.getTbodyStyle());
            Object value = null;
            try {
                value = ReflectUtil.getFieldValue(columnDefinition.getField(), datas.get(datasIndex));
            } catch (IllegalAccessException e) {
                throw new IllegalArgumentException("Field value get error., field name: " + columnDefinition.getFieldName());
            }
            columnDefinition.getColWriter().setCellValue(bodyCell, value);
        }
        columnDefinition.getColWriter().flush();
    }
}
