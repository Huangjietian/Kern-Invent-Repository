package cn.kerninventor.tools.poibox.data.templatedtable.templator;

import cn.kerninventor.tools.poibox.BoxGadget;
import cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.DataValidationBuilderFactory;
import cn.kerninventor.tools.poibox.data.templatedtable.initializer.ExcelColumnInitializer;
import cn.kerninventor.tools.poibox.data.templatedtable.initializer.ExcelTabulationInitializer;
import cn.kerninventor.tools.poibox.data.templatedtable.reader.ExcelTabulationTemplatedReader;
import cn.kerninventor.tools.poibox.data.templatedtable.writer.ExcelTabulationTemplatedWriter;
import cn.kerninventor.tools.poibox.data.utils.InstanceGetter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;


/**
 * @Author Kern
 * @Date 2020/3/12 18:53
 * @Description TODO  模板应该支持改大标题，删减列等操作
 */
public class ExcelTabulationTemplator<T> implements Templator<T>, InstanceGetter<ExcelTabulationInitializer<T>> {

    protected ExcelTabulationInitializer<T> initializer;

    private boolean cellDataValid = true;

    public ExcelTabulationTemplator(ExcelTabulationInitializer<T> initializer) {
        this.initializer = initializer;
    }


    @Override
    public Templator<T> tempalateTo(String sheetName) {
        return tempalateTo(initializer.getParent().getSheet(sheetName));
    }

    @Override
    public Templator<T> tempalateTo(int sheetAt) {
        return tempalateTo(initializer.getParent().getSheet(sheetAt));
    }

    @Override
    public Templator<T> tempalateTo(Sheet sheet) {
        execute(sheet);
        return this;
    }

    public void execute(Sheet sheet, Object... others) {
        //画大标题
        if (headline != null){
            headline.draw(sheet);
        }

        //画表头表体
        Row headRow = sheet.createRow(initializer.getTableHeadRdx());
        Short tableHeadFHIP = sheet.getWorkbook().getFontAt(initializer.getTableHeadStyle().getFontIndexAsInt()).getFontHeightInPoints();
        DataFormat dataFormat = sheet.getWorkbook().createDataFormat();

        List<ExcelColumnInitializer> columnsContainer = initializer.getColumnsContainer();

        columnsContainer.forEach(column -> {
            //画表头单元格
            Cell headRowCell = headRow.createCell(column.getColumnIndex());
            //value
            headRowCell.setCellValue(column.getTitleName());
            //style
            headRowCell.setCellStyle(column.getColumnStyle());

            //列宽设置
            if (column.getColumnWidth() == -1 ){
                int columnWidth = BoxGadget.getCellWidthByContent(column.getTitleName(), tableHeadFHIP);
                sheet.setColumnWidth(column.getColumnIndex(), columnWidth);
            } else {
                sheet.setColumnWidth(column.getColumnIndex(), column.getColumnWidth());
            }
            //正文, 执行写入的时候不设置正文每一行的风格和数据有效性，由writer代替。考虑适用装饰者模式
            templateTbody(initializer, column, dataFormat, sheet, others);
        });
    }

    /**
    * @author Kern
    * @date 2020/4/10
    * @description
    * @中文描述 画表体
    */
    protected void templateTbody(ExcelTabulationInitializer initializer, ExcelColumnInitializer column, DataFormat dataFormat, Sheet sheet, Object... others){
        if (column.getDataFormatEx() != null){
            column.getColumnStyle().setDataFormat(dataFormat.getFormat(column.getDataFormatEx()));
        }
        //画每一行
        for (int i = 0 ; i < initializer.getTextRowNum(); i ++){
            Row textRow = BoxGadget.getRowForce(sheet, i + initializer.getTableTextRdx());
            Cell textCell = textRow.createCell(column.getColumnIndex());
            textCell.setCellStyle(column.getColumnStyle());
        }
        //数据有效性
        if (cellDataValid && column.getValidAnnotation() != null) {
            DataValidationBuilderFactory.getInstance(column.getValidAnnotation())
                    .addValidation(initializer, column, sheet);
        }
    }




    @Override
    public Templator setCellDataValid(boolean valid) {
        cellDataValid = valid;
        return this;
    }

    @Override
    public Templator<T> writeTo(String sheetName, List<T> datas) {
        new ExcelTabulationTemplatedWriter<T>().writeTo(sheetName, datas, this);
        return this;
    }

    @Override
    public Templator<T> writeTo(int sheetAt, List<T> datas) {
        new ExcelTabulationTemplatedWriter<T>().writeTo(sheetAt, datas, this);
        return this;
    }

    @Override
    public Templator<T> writeTo(Sheet sheet, List<T> datas) {
        new ExcelTabulationTemplatedWriter<T>().writeTo(sheet, datas, this);
        return this;
    }

    @Override
    public List<T> readFrom(String sheetName) {
        return new ExcelTabulationTemplatedReader<T>().readFrom(sheetName, this);
    }

    @Override
    public List<T> readFrom(int sheetAt) {
        return new ExcelTabulationTemplatedReader<T>().readFrom(sheetAt, this);
    }

    @Override
    public List<T> readFrom(Sheet sheet) {
        return new ExcelTabulationTemplatedReader<T>().readFrom(sheet, this);
    }

    @Override
    public ExcelTabulationInitializer<T> getInstance() {
        return initializer;
    }


}
