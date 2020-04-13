package cn.kerninventor.tools.poibox.data.templatedtable.writer;

import cn.kerninventor.tools.poibox.BoxGadget;
import cn.kerninventor.tools.poibox.data.templatedtable.ExcelColumn;
import cn.kerninventor.tools.poibox.data.templatedtable.initializer.ExcelBannerInitializer;
import cn.kerninventor.tools.poibox.data.templatedtable.initializer.ExcelColumnInitializer;
import cn.kerninventor.tools.poibox.data.templatedtable.initializer.ExcelTabulationInitializer;
import cn.kerninventor.tools.poibox.data.templatedtable.writer.tbodywriter.TbodyDataWriter;
import cn.kerninventor.tools.poibox.data.templatedtable.writer.tbodywriter.TbodyEmptyWriter;
import cn.kerninventor.tools.poibox.data.templatedtable.writer.tbodywriter.TbodyWriter;
import cn.kerninventor.tools.poibox.layout.MergedRange;
import org.apache.poi.ss.usermodel.*;

import java.util.List;
import java.util.Objects;


/**
 * @Author Kern
 * @Date 2020/3/12 18:53
 * @Description TODO  模板应该支持改大标题，删减列等操作
 */
public class ExcelTabulationWriter<T> implements Writer<T> {

    protected ExcelTabulationInitializer<T> tabulationInitializer;

    public ExcelTabulationWriter(ExcelTabulationInitializer<T> tabulationInitializer) {
        this.tabulationInitializer = Objects.requireNonNull(tabulationInitializer);
    }

    @Override
    public Writer<T> writeTo(String sheetName) {
        return writeTo(sheetName, null);
    }

    @Override
    public Writer<T> writeTo(int sheetAt) {
        return writeTo(sheetAt, null);
    }

    @Override
    public Writer<T> writeTo(Sheet sheet) {
        return writeTo(sheet, null);
    }

    @Override
    public Writer<T> writeTo(String sheetName, List<T> datas) {
        Sheet sheet = tabulationInitializer.getParent().getSheet(sheetName);
        return writeTo(sheet, datas);
    }

    @Override
    public Writer<T> writeTo(int sheetAt, List<T> datas) {
        Sheet sheet = tabulationInitializer.getParent().getSheet(sheetAt);
        return writeTo(sheet, datas);
    }

    @Override
    public Writer<T> writeTo(Sheet sheet, List<T> datas) {
        TbodyWriter tbodyWriter;
        if (datas == null || datas.isEmpty()) {
            tbodyWriter = new TbodyEmptyWriter();
        } else {
            tbodyWriter = new TbodyDataWriter();
        }
        execute(sheet, tbodyWriter, datas);
        return this;
    }

    public void execute(Sheet sheet, TbodyWriter tbodyWriter, List datas) {
        //画表头表体
        Row headRow = sheet.createRow(tabulationInitializer.getTheadRowIndex());
        //获取当前字体的大小设置，以便自动调整列宽的设置实现
        Font font = BoxGadget.getFontFrom(tabulationInitializer.getTheadStyle(), tabulationInitializer.getParent().workbook());
        Short theadFontHeightInPoints = font.getFontHeightInPoints();
        //设置表头行风格
        CellStyle theadStyle = tabulationInitializer.getTheadStyle();
        List<ExcelColumnInitializer> columnsContainer = tabulationInitializer.getColumnsContainer();
        columnsContainer.forEach(column -> {
            //画表头单元格
            Cell headRowCell = headRow.createCell(column.getColumnIndex());
            headRowCell.setCellValue(column.getTitleName());
            headRowCell.setCellStyle(theadStyle);
            //列宽设置
            int columnWidth;
            if (column.getColumnWidth() == ExcelColumn.DEFAULT_COLUMN_WIDTH){
                columnWidth = BoxGadget.getCellWidthByContent(column.getTitleName(), theadFontHeightInPoints);
            } else {
                columnWidth = BoxGadget.adjustCellWidth(column.getColumnWidth());
            }
            sheet.setColumnWidth(column.getColumnIndex(), columnWidth);
            //正文, 执行写入的时候不设置正文每一行的风格和数据有效性，由writer代替。考虑适用装饰者模式
            tbodyWriter.templateTbody(tabulationInitializer, column, sheet, datas);
        });
        //画横幅
        tempalateBanners(tabulationInitializer, sheet);
    }

    protected void tempalateBanners(ExcelTabulationInitializer tabulationInitializer, Sheet sheet) {
        List<ExcelBannerInitializer> bannerContainer = tabulationInitializer.getBannerContainer();
        bannerContainer.forEach(e -> {
            //TODO 如何处理这个返回对象
            //方案1  提供对象赋值，提供给用户修改内容或风格
            //方案2  不提供
            e.adjustCellRangeAddress(tabulationInitializer);
            MergedRange mergedRange = tabulationInitializer.getParent().layouter().mergedRegion(sheet, e.getRangeAddress());
            //TODO 某些情况下生成多行的数据时，如何进行换行和如何调整行高。
//            String value = e.getV alue().replace("\r\n", System.lineSeparator());
//            String[] lineSeparatorNums = value.split(System.lineSeparator());
            mergedRange.setMergeRangeContent(e.getValue()).setMergeRangeStyle(e.getCellStyle());
        });
    }

}
