package cn.kerninventor.tools.poibox.opensource.data.tabulation.writer.tbody;

import cn.kerninventor.tools.poibox.opensource.BoxGadget;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.context.ColumnDefinition;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.context.TableContext;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.writer.ETabulationWriter;
import cn.kerninventor.tools.poibox.opensource.utils.BeanUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * @author Kern
 * @date 2020/5/25 14:18
 * @description
 */
public class TableBodyStyleWriter implements TbodyWriter {

    @Override
    public void templateTbody(ColumnDefinition columnDefinition, Sheet sheet) {
        TableContext table = columnDefinition.getTableContext();
        columnDefinition.getColWriter().pre();
        for (int rowIndex = table.getTbodyFirstRowIndex() ; rowIndex < table.getEffectiveRows() + table.getTbodyFirstRowIndex(); rowIndex ++){
            Row bodyRow = BoxGadget.getRowForce(sheet, rowIndex);
            ETabulationWriter.setRowHeight(bodyRow, table.getTbodyRowHeight());
            Cell bodyCell = bodyRow.createCell(columnDefinition.getColumnIndex());
            bodyCell.setCellStyle(columnDefinition.getTbodyStyle());
            if (BeanUtil.isNotEmpty(columnDefinition.getFormula())) {
                bodyCell.setCellFormula(columnDefinition.getFormula());
            }
        }
        if (columnDefinition.getDataValidationBuilder() != null) {
            columnDefinition.getDataValidationBuilder().addValidation(table, columnDefinition, sheet);
        }
        columnDefinition.getColWriter().flush();
    }
}
