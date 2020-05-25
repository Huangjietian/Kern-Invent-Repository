package cn.kerninventor.tools.poibox.opensource.data.tabulation.writer;

import cn.kerninventor.tools.poibox.opensource.BoxGadget;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.context.ColumnDefinition;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.context.TableContext;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.writer.tbody.TbodyWriter;
import cn.kerninventor.tools.poibox.opensource.style.Styler;
import cn.kerninventor.tools.poibox.opensource.utils.BeanUtil;
import org.apache.poi.ss.usermodel.*;

import java.util.Iterator;
import java.util.List;

/**
 * @author Kern
 * @date 2020/5/22 18:12
 * @description
 */
public class BasicTabulationWriter<T> {

    private TbodyWriter tbodyWriter;

    public BasicTabulationWriter(TbodyWriter tbodyWriter) {
        this.tbodyWriter = tbodyWriter;
    }

    public void doWrite(TableContext tableContext, List<ColumnDefinition<T>> columnDefinitionsTemporary, Sheet sheet) {
        Row headRow = sheet.createRow(tableContext.getTheadRowIndex());
        ETabulationWriter.setRowHeight(headRow, tableContext.getTheadRowHeight());
        Workbook workbook = tableContext.getParent().workbook();
        DataFormat dataFormat = workbook.createDataFormat();
        Styler styler = tableContext.getParent().styler();
        for (Iterator<ColumnDefinition<T>> iterator = columnDefinitionsTemporary.iterator(); iterator.hasNext() ; ) {
            ColumnDefinition column = iterator.next();
            Cell headRowCell = headRow.createCell(column.getColumnIndex());
            headRowCell.setCellValue(column.getTitleName());
            headRowCell.setCellStyle(column.getTheadStyle());
            Short theadFontHeightInPoints = BoxGadget.getFontFrom(column.getTheadStyle(), workbook).getFontHeightInPoints();
            ETabulationWriter.setColumnWidth(tableContext, column, sheet, theadFontHeightInPoints);
            CellStyle tbodyStyle = column.getTbodyStyle();
            if (BeanUtil.isNotEmpty(column.getDataFormatEx())){
                tbodyStyle = styler.copyStyle(tbodyStyle);
                tbodyStyle.setDataFormat(dataFormat.getFormat(column.getDataFormatEx()));
                column.setTbodyStyle(tbodyStyle);
            }
            //4. 绘制表体
            tbodyWriter.templateTbody(column, sheet);
        }
    }

}
