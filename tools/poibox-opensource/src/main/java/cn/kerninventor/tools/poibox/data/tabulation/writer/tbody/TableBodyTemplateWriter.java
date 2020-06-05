package cn.kerninventor.tools.poibox.data.tabulation.writer.tbody;

import cn.kerninventor.tools.poibox.BoxGadget;
import cn.kerninventor.tools.poibox.data.tabulation.context.ColumnDefinition;
import cn.kerninventor.tools.poibox.data.tabulation.context.TableContext;
import cn.kerninventor.tools.poibox.utils.BeanUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * @author Kern
 * @date 2020/5/25 14:18
 * @description
 */
public final class TableBodyTemplateWriter<T> implements TbodyWriter<T> {

    @Override
    public void templateTbody(ColumnDefinition<T> columnDefinition, Sheet sheet) {
        TableContext tableContext = columnDefinition.getTableContext();
        for (int rowIndex = tableContext.getTbodyFirstRowIndex() ; rowIndex < tableContext.getEffectiveRows() + tableContext.getTbodyFirstRowIndex(); rowIndex ++){
            Row bodyRow = BoxGadget.getRowForce(sheet, rowIndex);
            bodyRow.setHeightInPoints(tableContext.getTbodyRowHeight());
            Cell bodyCell = bodyRow.createCell(columnDefinition.getColumnIndex());
            bodyCell.setCellStyle(columnDefinition.getTbodyStyle());
            if (BeanUtil.isNotEmpty(columnDefinition.getFormula())) {
                bodyCell.setCellFormula(columnDefinition.getFormula());
            }
        }
        if (columnDefinition.getDataValidationBuilder() != null) {
            columnDefinition.getDataValidationBuilder().addValidation(tableContext, columnDefinition, sheet);
        }
    }
}
