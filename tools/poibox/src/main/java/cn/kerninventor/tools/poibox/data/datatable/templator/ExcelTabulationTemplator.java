package cn.kerninventor.tools.poibox.data.datatable.templator;

import cn.kerninventor.tools.poibox.BoxGadget;
import cn.kerninventor.tools.poibox.data.datatable.TemplatedReader;
import cn.kerninventor.tools.poibox.data.datatable.TemplatedWriter;
import cn.kerninventor.tools.poibox.data.datatable.Templator;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.DataValidBuilder;
import cn.kerninventor.tools.poibox.data.datatable.initializer.ExcelColumnInitializer;
import cn.kerninventor.tools.poibox.data.datatable.initializer.ExcelTabulationInitializer;
import cn.kerninventor.tools.poibox.data.datatable.reader.ExcelTabulationReader;
import cn.kerninventor.tools.poibox.data.datatable.writer.ExcelTabulationWriter;
import cn.kerninventor.tools.poibox.data.utils.InstanceGetter;
import cn.kerninventor.tools.poibox.layout.LayoutHandler;
import cn.kerninventor.tools.poibox.layout.MergedRange;
import cn.kerninventor.tools.poibox.style.Styler;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.List;


/**
 * @Title ExcelTabulationTemplator
 * @ProjectName kerninventresp
 * @PackageName cn.kerninventor.tools.poibox.data.datatable
 * @Author Kern
 * @Date 2020/3/12 18:53
 * @Description TODO  模板应该支持改大标题，删减列等操作
 */
public class ExcelTabulationTemplator<T> implements Templator<T>, TemplatedWriter<T>, TemplatedReader<T>, InstanceGetter<ExcelTabulationInitializer<T>> {

    private ExcelTabulationInitializer<T> initializer;

    private Headline headline;

    private boolean cellDataValid = true;

    public ExcelTabulationTemplator(ExcelTabulationInitializer<T> initializer) {
        this.initializer = initializer;
        if (initializer.getHeadline() != null) {
            this.headline = new Headline(this, initializer.getTabulationStyle().getHeadLineStyle(), initializer.getHeadline());
        }
    }

    @Override
    public Headline getHeadline() {
        return headline;
    }

    @Override
    public Templator tempalateTo(String sheetName) {
        return tempalateTo(BoxGadget.getSheetForce(initializer.getPoiBox().workbook(), sheetName));
    }

    @Override
    public Templator tempalateTo(int sheetAt) {
        return tempalateTo(BoxGadget.getSheetForce(initializer.getPoiBox().workbook(), sheetAt));
    }


    @Override
    public Templator tempalateTo(Sheet sheet) {
        drawHeadLine(sheet);
        drawTable(sheet);
        return this;
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
            CellStyle style = Styler.cloneStyle(sheet.getWorkbook(), headline.getCellStyle());
            initializer.getPoiBox().layouter().mergedRegion(sheet, range)
                    .setMergeRangeContent(headline.getContent())
                    .setMergeRangeStyle(style);
        }
    }

    /**
     * draw table
     */
    private void drawTable(Sheet sheet){
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
            if (cellDataValid && column.getValidAnnotation() != null) {
                DataValidBuilder.getInstance(column.getValidAnnotation())
                        .addValidation(initializer, column, sheet);
            }

        });
    }

    @Override
    public Templator setCellDataValid(boolean valid) {
        cellDataValid = valid;
        return this;
    }

    @Override
    public TemplatedWriter writer() {
        return this;
    }

    @Override
    public TemplatedReader reader() {
        return this;
    }

    @Override
    public ExcelTabulationInitializer<T> getInstance() {
        return initializer;
    }

    @Override
    public List<T> readFrom(String sheetName) {
        return new ExcelTabulationReader<T>().readFrom(sheetName, this);
    }

    @Override
    public List<T> readFrom(int sheetAt) {
        return new ExcelTabulationReader<T>().readFrom(sheetAt, this);
    }

    @Override
    public List<T> readFrom(Sheet sheet) {
        return new ExcelTabulationReader<T>().readFrom(sheet, this);
    }

    @Override
    public void writeTo(String sheetName, List<T> datas) {
        tempalateTo(sheetName);
        new ExcelTabulationWriter<T>().writeTo(initializer.getPoiBox().workbook().getSheet(sheetName), datas, this);
    }

    @Override
    public void writeTo(int sheetAt, List<T> datas) {
        tempalateTo(sheetAt);
        new ExcelTabulationWriter<T>().writeTo(initializer.getPoiBox().workbook().getSheetAt(sheetAt), datas, this);
    }

    @Override
    public void writeTo(Sheet sheet, List<T> datas) {
        tempalateTo(sheet);
        new ExcelTabulationWriter<T>().writeTo(sheet, datas, this);
    }
}
