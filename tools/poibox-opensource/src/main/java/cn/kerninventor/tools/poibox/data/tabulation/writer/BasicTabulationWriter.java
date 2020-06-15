package cn.kerninventor.tools.poibox.data.tabulation.writer;

import cn.kerninventor.tools.poibox.BoxGadget;
import cn.kerninventor.tools.poibox.data.tabulation.ExcelColumn;
import cn.kerninventor.tools.poibox.data.tabulation.context.ClassFileColumnDefinition;
import cn.kerninventor.tools.poibox.data.tabulation.context.ClassFileTableContext;
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
public final class BasicTabulationWriter<T> {

    private TbodyWriter<T> tbodyWriter;

    public BasicTabulationWriter(TbodyWriter<T> tbodyWriter) {
        this.tbodyWriter = tbodyWriter;
    }

    public void doWrite(ClassFileTableContext classFileTableContext, List<ClassFileColumnDefinition<T>> classFileColumnDefinitions, Sheet sheet) {
        Row headRow = sheet.createRow(classFileTableContext.getTheadRowIndex());
        headRow.setHeightInPoints(classFileTableContext.getTheadRowHeight());
        Workbook workbook = classFileTableContext.getParent().workbook();
        DataFormat dataFormat = workbook.createDataFormat();
        Styler styler = classFileTableContext.getParent().styler();
        for (ClassFileColumnDefinition<T> classFileColumnDefinition : classFileColumnDefinitions) {
            Cell headRowCell = headRow.createCell(classFileColumnDefinition.getColumnIndex());
            headRowCell.setCellValue(classFileColumnDefinition.getTitleName());
            headRowCell.setCellStyle(classFileColumnDefinition.getTheadStyle());
            short theadFontHeightInPoints = BoxGadget.getFontFrom(classFileColumnDefinition.getTheadStyle(), workbook).getFontHeightInPoints();
            setColumnWidth(classFileTableContext, classFileColumnDefinition, sheet, theadFontHeightInPoints);
            CellStyle tbodyStyle = classFileColumnDefinition.getTbodyStyle();
            if (BeanUtil.isNotEmpty(classFileColumnDefinition.getDataFormatEx())) {
                tbodyStyle = styler.copyStyle(tbodyStyle);
                tbodyStyle.setDataFormat(dataFormat.getFormat(classFileColumnDefinition.getDataFormatEx()));
                classFileColumnDefinition.setTbodyStyle(tbodyStyle);
            }
            //4. 绘制表体
            this.tbodyWriter.templateTbody(classFileColumnDefinition, sheet);
        }
    }

    private void setColumnWidth(ClassFileTableContext tabulation, ClassFileColumnDefinition column, Sheet sheet, int theadFontHeightInPoints) {
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
