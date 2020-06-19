package cn.kerninventor.tools.poibox.data.tabulation.writer.table;

import cn.kerninventor.tools.poibox.BoxGadget;
import cn.kerninventor.tools.poibox.data.tabulation.annotations.ExcelColumn;
import cn.kerninventor.tools.poibox.data.tabulation.context.ColumnDefinition;
import cn.kerninventor.tools.poibox.data.tabulation.context.TabulationBeanConfiguration;
import cn.kerninventor.tools.poibox.data.tabulation.writer.TbodyWriter;
import cn.kerninventor.tools.poibox.style.Styler;
import cn.kerninventor.tools.poibox.utils.BeanUtil;
import org.apache.poi.ss.usermodel.*;

import java.util.List;

/**
 * @author Kern
 * @date 2020/5/22 18:12
 * @description
 */
public final class BasicTabulationWriter<T> {

    private TbodyWriter<T> tbodyWriter;

    public BasicTabulationWriter(TbodyWriter<T> tbodyWriter) {
        this.tbodyWriter = tbodyWriter;
    }

    public void doWrite(TabulationBeanConfiguration tableConfiguration, List<ColumnDefinition<T>> columnDefinitions, Sheet sheet) {
        Row headRow = sheet.createRow(tableConfiguration.getTheadRowIndex());
        headRow.setHeightInPoints(tableConfiguration.getTheadRowHeight());
        Workbook workbook = tableConfiguration.getParent().workbook();
        DataFormat dataFormat = workbook.createDataFormat();
        Styler styler = tableConfiguration.getParent().styler();
        for (ColumnDefinition<T> columnDefinition : columnDefinitions) {
            Cell headRowCell = headRow.createCell(columnDefinition.getColumnIndex());
            headRowCell.setCellValue(columnDefinition.getTitleName());
            headRowCell.setCellStyle(columnDefinition.getTheadStyle());
            short theadFontHeightInPoints = BoxGadget.getFontFrom(columnDefinition.getTheadStyle(), workbook).getFontHeightInPoints();
            setColumnWidth(tableConfiguration, columnDefinition, sheet, theadFontHeightInPoints);
            CellStyle tbodyStyle = columnDefinition.getTbodyStyle();
            if (BeanUtil.isNotEmpty(columnDefinition.getDataFormatEx())) {
                tbodyStyle = styler.copyStyle(tbodyStyle);
                tbodyStyle.setDataFormat(dataFormat.getFormat(columnDefinition.getDataFormatEx()));
                columnDefinition.setTbodyStyle(tbodyStyle);
            }
            //4. 绘制表体
            this.tbodyWriter.templateTbody(columnDefinition, sheet);
        }
    }

    private void setColumnWidth(TabulationBeanConfiguration tabulation, ColumnDefinition column, Sheet sheet, int theadFontHeightInPoints) {
        int width;
        if (column.getColumnWidth() == ExcelColumn.DefaultColumnWidth){
            width = BoxGadget.getCellWidthByContent(column.getTitleName(), theadFontHeightInPoints);
            width = Math.max(width, tabulation.getMinimumColumnsWidth());
            width = Math.min(width, tabulation.getMaximumColumnsWidth());
        } else {
            width = BoxGadget.adjustCellWidth(column.getColumnWidth());
        }
        sheet.setColumnWidth(column.getColumnIndex(), width);
    }

}
