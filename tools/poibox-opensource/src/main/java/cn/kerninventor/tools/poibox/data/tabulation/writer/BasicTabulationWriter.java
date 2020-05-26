package cn.kerninventor.tools.poibox.data.tabulation.writer;

import cn.kerninventor.tools.poibox.BoxGadget;
import cn.kerninventor.tools.poibox.data.tabulation.ExcelColumn;
import cn.kerninventor.tools.poibox.data.tabulation.context.ColumnDefinition;
import cn.kerninventor.tools.poibox.data.tabulation.context.TableContext;
import cn.kerninventor.tools.poibox.data.tabulation.writer.tbody.TbodyWriter;
import cn.kerninventor.tools.poibox.style.Styler;
import cn.kerninventor.tools.poibox.utils.BeanUtil;
import org.apache.poi.ss.usermodel.*;

import java.util.List;

/**
 * @author Kern
 * @date 2020/5/22 18:12
 * @description
 */
public class BasicTabulationWriter<T> {

    private TbodyWriter<T> tbodyWriter;

    public BasicTabulationWriter(TbodyWriter<T> tbodyWriter) {
        this.tbodyWriter = tbodyWriter;
    }

    public void doWrite(TableContext tableContext, List<ColumnDefinition<T>> columnDefinitions, Sheet sheet) {
        Row headRow = sheet.createRow(tableContext.getTheadRowIndex());
        headRow.setHeightInPoints(tableContext.getTheadRowHeight());
        Workbook workbook = tableContext.getParent().workbook();
        DataFormat dataFormat = workbook.createDataFormat();
        Styler styler = tableContext.getParent().styler();
        for (ColumnDefinition<T> columnDefinition : columnDefinitions) {
            Cell headRowCell = headRow.createCell(columnDefinition.getColumnIndex());
            headRowCell.setCellValue(columnDefinition.getTitleName());
            headRowCell.setCellStyle(columnDefinition.getTheadStyle());
            short theadFontHeightInPoints = BoxGadget.getFontFrom(columnDefinition.getTheadStyle(), workbook).getFontHeightInPoints();
            setColumnWidth(tableContext, columnDefinition, sheet, theadFontHeightInPoints);
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

    private void setColumnWidth(TableContext tabulation, ColumnDefinition column, Sheet sheet, int theadFontHeightInPoints) {
        int width;
        if (column.getColumnWidth() == ExcelColumn.DefaultColumnWidth){
            width = BoxGadget.getCellWidthByContent(column.getTitleName(), theadFontHeightInPoints);
            width = Math.max(width, tabulation.getMinimumColumnsWidth());
            width = Math.min(width, tabulation.getMaximunColumnsWidth());
        } else {
            width = BoxGadget.adjustCellWidth(column.getColumnWidth());
        }
        sheet.setColumnWidth(column.getColumnIndex(), width);
    }

}
