package cn.kerninventor.tools.poibox.data.datatable.templator;

import cn.kerninventor.tools.poibox.BoxGadget;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.DataValidBuilder;
import cn.kerninventor.tools.poibox.data.datatable.initializer.ExcelColumnInitializer;
import cn.kerninventor.tools.poibox.data.datatable.initializer.ExcelTabulationInitializer;
import cn.kerninventor.tools.poibox.data.utils.InstanceGetter;
import cn.kerninventor.tools.poibox.layout.LayoutHandler;
import cn.kerninventor.tools.poibox.layout.MergedRange;
import cn.kerninventor.tools.poibox.style.Styler;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.Collections;
import java.util.List;


/**
 * @Title ExcelTabulationTemplator
 * @ProjectName kerninventresp
 * @PackageName cn.kerninventor.tools.poibox.data.datatable
 * @Author Kern
 * @Date 2020/3/12 18:53
 * @Description TODO  模板应该支持改大标题，删减列等操作
 */
public class ExcelTabulationTemplator<T> implements Templator<T>, InstanceGetter<ExcelTabulationInitializer<T>> {

    private ExcelTabulationInitializer<T> initializer;

    private Headline headline;

    public ExcelTabulationTemplator(ExcelTabulationInitializer<T> initializer) {
        this.initializer = initializer;
    }

    @Override
    public Headline getHeadline() {
        return headline;
    }

    @Override
    public Templator tabulateTo(Sheet sheet, boolean valid) {
        drawHeadLine(sheet);
        drawTable(sheet, valid);
        return this;
    }

    @Override
    public Templator ReTemplate(Sheet sheet) {
        Collections.sort(initializer.getColumnsContainer());
        /**
         * TODO 重新构建模板
         */
        int sheetIdx = sheet.getWorkbook().getSheetIndex(sheet);
        sheet.getWorkbook().removeSheetAt(sheetIdx);
        return null;
    }

    /**
     * draw headline
     * @param sheet
     */
    private void drawHeadLine(Sheet sheet){
        if (initializer.getStartRowIndex() == initializer.getHeadlineRdx()){
            CellRangeAddress range = new CellRangeAddress(
                    initializer.getHeadlineRdx(),
                    initializer.getHeadlineRdx(),
                    initializer.getFirstColumnIndex(),
                    initializer.getLastColumnIndex()
            );
            CellStyle style = Styler.cloneStyle(sheet.getWorkbook(), initializer.getTabulationStyle().getHeadLineStyle());
            String content = initializer.getHeadline();
            MergedRange mergedRange =  new LayoutHandler(null).mergedRegion(sheet, range)
                                                                        .setMergeRangeContent(content)
                                                                        .setMergeRangeStyle(style);
            this.headline = new Headline(this, mergedRange, content);
        }
    }

    /**
     * draw table
     */
    private void drawTable(Sheet sheet, boolean valid){
        Row row = sheet.createRow(initializer.getTableHeadRdx());
        CellStyle tableHeadStyle = Styler.cloneStyle(sheet.getWorkbook(), initializer.getTabulationStyle().getTableHeadStyle());
        Font tableHeadFont = sheet.getWorkbook().getFontAt(tableHeadStyle.getFontIndexAsInt());
        CellStyle tableTextStyle = Styler.cloneStyle(sheet.getWorkbook(), initializer.getTabulationStyle().getTextStyle());
        DataFormat dataFormat = sheet.getWorkbook().createDataFormat();

        List<ExcelColumnInitializer> columnsContainer = initializer.getColumnsContainer();
        columnsContainer.forEach( column -> {
            Cell cell = row.createCell(column.getColumnIndex());
            //value
            cell.setCellValue(column.getTitleName());
            //style
            cell.setCellStyle(tableHeadStyle);

            //column width
            if (column.getColumnWidth() == -1 ){
                int columnWidth = BoxGadget.getCellWidthByContent(column.getTitleName(), tableHeadFont.getFontHeightInPoints());
                sheet.setColumnWidth(column.getColumnIndex(), columnWidth);
            } else {
                sheet.setColumnWidth(column.getColumnIndex(), column.getColumnWidth());
            }

            //text style
            CellStyle columnStyle = sheet.getWorkbook().createCellStyle();
            columnStyle.cloneStyleFrom(tableTextStyle);
            if (column.getDataFormatEx() != null){
                columnStyle.setDataFormat(dataFormat.getFormat(column.getDataFormatEx()));
            }
            for (int i = 0 ; i < initializer.getTextRowNum(); i ++){
                Row textRow = BoxGadget.getRowForce(sheet, i + initializer.getTableTextRdx());
                Cell textCell = textRow.createCell(column.getColumnIndex());
                textCell.setCellStyle(columnStyle);
            }

            //data validation
            if (valid && column.getValidAnnotation() != null) {
                DataValidBuilder.getInstance(column.getValidAnnotation())
                        .addValidation(initializer, column, sheet);
            }

        });
    }

    @Override
    public ExcelTabulationInitializer<T> getInstance() {
        return initializer;
    }
}
