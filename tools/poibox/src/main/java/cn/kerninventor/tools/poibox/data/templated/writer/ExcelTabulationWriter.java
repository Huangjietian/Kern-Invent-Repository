package cn.kerninventor.tools.poibox.data.templated.writer;

import cn.kerninventor.tools.poibox.BoxGadget;
import cn.kerninventor.tools.poibox.data.templated.ExcelColumn;
import cn.kerninventor.tools.poibox.data.templated.ExcelTabulation;
import cn.kerninventor.tools.poibox.data.templated.initializer.ExcelBannerInitializer;
import cn.kerninventor.tools.poibox.data.templated.initializer.ExcelColumnInitializer;
import cn.kerninventor.tools.poibox.data.templated.initializer.ExcelTabulationInitializer;
import cn.kerninventor.tools.poibox.data.templated.validation.DataValidationBuilderFactory;
import cn.kerninventor.tools.poibox.layout.MergedRange;
import cn.kerninventor.tools.poibox.utils.BeanUtil;
import cn.kerninventor.tools.poibox.utils.ReflectUtil;
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

    private ExcelTabulationInitializer getTabulationInitializer() {
        return this.tabulationInitializer;
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
        if (BeanUtil.isEmpty(datas)) {
            execute(sheet, null,

                (t, c, s, d) -> {
                    if (BeanUtil.isNotEmpty(c.getDataFormatEx())){
                        c.getColumnStyle().setDataFormat(
                                t.getParent().workbook().createDataFormat().getFormat(
                                        c.getDataFormatEx()
                                )
                        );
                    }
                    for (int i = 0 ; i < t.getEffectiveRows(); i ++){
                        Row textRow = setRowHeight(
                                BoxGadget.getRowForce(s, i + t.getTbodyFirstRowIndex()),
                                t.getTbodyRowHeight()
                        );
                        Cell textCell = textRow.createCell(c.getColumnIndex());
                        textCell.setCellStyle(c.getColumnStyle());
                        if (BeanUtil.isNotEmpty(c.getFormula())) {
                            textCell.setCellFormula(c.getFormula());
                        }
                    }
                    if (c.getValidAnnotation() != null) {
                        DataValidationBuilderFactory.getInstance(c.getValidAnnotation()).addValidation(t, c, s);
                    }
                }

            );
        } else {
            execute(sheet, datas,

                (t, c, s, d) -> {
                    if (BeanUtil.isNotEmpty(c.getDataFormatEx())){
                        c.getColumnStyle().setDataFormat(
                                t.getParent().workbook().createDataFormat().getFormat(
                                        c.getDataFormatEx()
                                )
                        );
                    }
                    ColWriter colWriter = ColWriter.newInstance(c.isMergeByContent(), s);
                    for (int datasIndex = 0, rowIndex = t.getTbodyFirstRowIndex(); datasIndex < d.size() ; datasIndex ++ , rowIndex++){
                        Row row = setRowHeight(
                                BoxGadget.getRowForce(s , rowIndex),
                                t.getTbodyRowHeight()
                        );
                        Cell cell = BoxGadget.getCellForce(row, c.getColumnIndex()
                        );
                        cell.setCellStyle(c.getColumnStyle());
                        Object value = null;
                        try {
                            value = ReflectUtil.getFieldValue(c.getField(), d.get(datasIndex));
                        } catch (IllegalAccessException e) {
                            throw new IllegalArgumentException("Field value get error., field name: " + c.getFieldName());
                        }
                        if (c.getInterpretor().isInterpretable()) {
                            value = c.getInterpretor().interpreteOf(value);
                        }
                        colWriter.setCellValue(cell, value, rowIndex);
                    }
                    colWriter.flush();
                }

            );
        }

        return this;
    }

    public void execute(Sheet sheet, List datas, final TbodyWriter tbodyWriter) {
        ExcelTabulationInitializer tabulationInitializer = getTabulationInitializer();
        Row headRow = setRowHeight(
                sheet.createRow(
                        tabulationInitializer.getTheadRowIndex()
                ),
                tabulationInitializer.getTheadRowHeight()
        );
        Font font = BoxGadget.getFontFrom(
                tabulationInitializer.getTheadStyle(),
                tabulationInitializer.getParent().workbook()
        );
        Short theadFontHeightInPoints = font.getFontHeightInPoints();
        CellStyle theadStyle = tabulationInitializer.getTheadStyle();
        List<ExcelColumnInitializer> columnsContainer = tabulationInitializer.getColumnsContainer();
        columnsContainer.forEach(column -> {
            Cell headRowCell = headRow.createCell(column.getColumnIndex());
            headRowCell.setCellValue(column.getTitleName());
            headRowCell.setCellStyle(theadStyle);
            if (column.getColumnWidth() == ExcelColumn.DEFAULT_COLUMN_WIDTH){
                sheet.setColumnWidth(
                        column.getColumnIndex(),
                        BoxGadget.getCellWidthByContent(
                                column.getTitleName(),
                                theadFontHeightInPoints
                        )
                );
            } else {
                sheet.setColumnWidth(
                        column.getColumnIndex(),
                        BoxGadget.adjustCellWidth(
                                column.getColumnWidth()
                        )
                );
            }
            tbodyWriter.templateTbody(tabulationInitializer, column, sheet, datas);
        });
        tempalateBanners(tabulationInitializer, sheet);
    }

    private Row setRowHeight(Row row, float height) {
        if (ExcelTabulation.DEF_ROW_HEIGHT != height) {
            row.setHeightInPoints(height);
        }
        return row;
    }

    private void tempalateBanners(ExcelTabulationInitializer tabulationInitializer, Sheet sheet) {
        List<ExcelBannerInitializer> bannerContainer = tabulationInitializer.getBannerContainer();
        bannerContainer.forEach(e -> {
            MergedRange mergedRange = tabulationInitializer.getParent().layouter().mergedRegion(
                    sheet,
                    e.adjustCellRangeAddress(tabulationInitializer)
            );
            //TODO 某些情况下生成多行的数据时，如何进行换行和如何调整行高。
            mergedRange.setMergeRangeContent(e.getValue()).setMergeRangeStyle(e.getCellStyle());
        });
    }

}
