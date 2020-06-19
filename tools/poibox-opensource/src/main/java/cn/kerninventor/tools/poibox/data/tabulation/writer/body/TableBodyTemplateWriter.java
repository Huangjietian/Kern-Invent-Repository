package cn.kerninventor.tools.poibox.data.tabulation.writer.body;

import cn.kerninventor.tools.poibox.BoxGadget;
import cn.kerninventor.tools.poibox.data.tabulation.context.ColumnDefinition;
import cn.kerninventor.tools.poibox.data.tabulation.context.TabulationBeanConfiguration;
import cn.kerninventor.tools.poibox.data.tabulation.writer.TbodyWriter;
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
        TabulationBeanConfiguration tableConfiguration = columnDefinition.getTableConfiguration();
        for (int rowIndex = tableConfiguration.getTbodyFirstRowIndex(); rowIndex < tableConfiguration.getEffectiveRows() + tableConfiguration.getTbodyFirstRowIndex(); rowIndex ++){
            Row bodyRow = BoxGadget.getRowForce(sheet, rowIndex);
            bodyRow.setHeightInPoints(tableConfiguration.getTbodyRowHeight());
            Cell bodyCell = bodyRow.createCell(columnDefinition.getColumnIndex());
            bodyCell.setCellStyle(columnDefinition.getTbodyStyle());
            if (BeanUtil.isNotEmpty(columnDefinition.getFormula())) {
                bodyCell.setCellFormula(columnDefinition.getFormula());
            }
        }
        if (columnDefinition.getDataValidationBuilder() != null) {
            columnDefinition.getDataValidationBuilder().setDataValidation(tableConfiguration, columnDefinition, sheet);
        }
    }
}
