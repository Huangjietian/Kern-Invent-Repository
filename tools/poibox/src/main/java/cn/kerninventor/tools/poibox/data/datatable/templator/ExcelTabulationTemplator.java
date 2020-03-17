package cn.kerninventor.tools.poibox.data.datatable.templator;

import cn.kerninventor.tools.poibox.BoxGadget;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.DataValidBuilder;
import cn.kerninventor.tools.poibox.data.datatable.initializer.ExcelColumnInitializer;
import cn.kerninventor.tools.poibox.data.datatable.initializer.ExcelTabulationInitializer;
import cn.kerninventor.tools.poibox.data.datatable.reader.ExcelTabulationReader;
import cn.kerninventor.tools.poibox.data.datatable.reader.Reader;
import cn.kerninventor.tools.poibox.data.datatable.writer.ExcelTabulationWriter;
import cn.kerninventor.tools.poibox.data.datatable.writer.Writer;
import cn.kerninventor.tools.poibox.data.utils.InstanceGetter;
import org.apache.poi.ss.usermodel.*;

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

    private boolean cellDataValid = true;

    public ExcelTabulationTemplator(ExcelTabulationInitializer<T> initializer) {
        this.initializer = initializer;
        if (initializer.getHeadline() != null) {
            this.headline = new Headline(initializer.getHeadlineRdx(),
                    initializer.getFirstColumnIndex(),
                    initializer.getLastColumnIndex(),
                    initializer.getHeadlineStyle(),
                    initializer.getHeadline());
        }
    }

    @Override
    public Headline getHeadline() {
        return headline;
    }

    @Override
    public Templator tempalateTo(String sheetName) {
        return tempalateTo(initializer.getParent().getSheet(sheetName));
    }

    @Override
    public Templator tempalateTo(int sheetAt) {
        return tempalateTo(initializer.getParent().getSheet(sheetAt));
    }


    @Override
    public Templator tempalateTo(Sheet sheet) {
        execute(sheet, true);
        return this;
    }

    public void execute(Sheet sheet, boolean noWriter) {
        if (headline != null){
            headline.draw(sheet);
        }
        Row row = sheet.createRow(initializer.getTableHeadRdx());
        Short tableHeadFHIP = sheet.getWorkbook().getFontAt(initializer.getTableHeadStyle().getFontIndexAsInt()).getFontHeightInPoints();
        DataFormat dataFormat = sheet.getWorkbook().createDataFormat();
        List<ExcelColumnInitializer> columnsContainer = initializer.getColumnsContainer();
        columnsContainer.forEach(column -> {
            Cell cell = row.createCell(column.getColumnIndex());
            //value
            cell.setCellValue(column.getTitleName());
            //style
            cell.setCellStyle(column.getColumnStyle());
            //column width
            if (column.getColumnWidth() == -1 ){
                int columnWidth = BoxGadget.getCellWidthByContent(column.getTitleName(), tableHeadFHIP);
                sheet.setColumnWidth(column.getColumnIndex(), columnWidth);
            } else {
                sheet.setColumnWidth(column.getColumnIndex(), column.getColumnWidth());
            }
            //text style
            if (noWriter){
                if (column.getDataFormatEx() != null){
                    column.getColumnStyle().setDataFormat(dataFormat.getFormat(column.getDataFormatEx()));
                }
                for (int i = 0 ; i < initializer.getTextRowNum(); i ++){
                    Row textRow = BoxGadget.getRowForce(sheet, i + initializer.getTableTextRdx());
                    Cell textCell = textRow.createCell(column.getColumnIndex());
                    textCell.setCellStyle(column.getColumnStyle());
                }
                //data validation
                if (cellDataValid && column.getValidAnnotation() != null) {
                    DataValidBuilder.getInstance(column.getValidAnnotation())
                            .addValidation(initializer, column, sheet);
                }
            }
        });
    }

    @Override
    public Templator setCellDataValid(boolean valid) {
        cellDataValid = valid;
        return this;
    }

    @Override
    public ExcelTabulationInitializer<T> getInstance() {
        return initializer;
    }

    @Override
    public TemplatedWriter writer() {
        return new InnerWriter(this, new ExcelTabulationWriter());
    }

    private class InnerWriter <T> implements TemplatedWriter<T>{
        private ExcelTabulationTemplator<T> templator;
        private Writer<T> writer;

        public InnerWriter(ExcelTabulationTemplator<T> templator, Writer<T> writer) {
            this.templator = templator;
            this.writer = writer;
        }

        @Override
        public boolean getDefaultMergedMode() {
            return writer.getDefaultMergedMode();
        }

        @Override
        public TemplatedWriter setDefaultMergedMode(boolean isMergeColum) {
            writer.setDefaultMergedMode(isMergeColum);
            return this;
        }

        @Override
        public void writeTo(String sheetName, List<T> datas) {
            Sheet sheet = initializer.getParent().getSheet(sheetName);
            writeTo(sheet, datas);
        }

        @Override
        public void writeTo(int sheetAt, List<T> datas) {
            Sheet sheet = initializer.getParent().getSheet(sheetAt);
            writeTo(sheet, datas);
        }

        @Override
        public void writeTo(Sheet sheet, List<T> datas) {
            writer.writeTo(sheet, datas, templator);
        }
    }

    @Override
    public TemplatedReader reader() {
        return new InnerReader(this, new ExcelTabulationReader());
    }

    private class InnerReader<T> implements TemplatedReader<T>{

        public InnerReader(ExcelTabulationTemplator<T> templator, Reader<T> reader) {
            this.templator = templator;
            this.reader = reader;
        }

        private ExcelTabulationTemplator<T> templator;
        private Reader<T> reader;

        @Override
        public List<T> readFrom(String sheetName) {
            Sheet sheet = initializer.getParent().getSheet(sheetName);
            return reader.readFrom(sheet, templator);
        }

        @Override
        public List<T> readFrom(int sheetAt) {
            Sheet sheet = initializer.getParent().getSheet(sheetAt);
            return reader.readFrom(sheet, templator);
        }

        @Override
        public List<T> readFrom(Sheet sheet) {
            return reader.readFrom(sheet, templator);
        }
    }

}
