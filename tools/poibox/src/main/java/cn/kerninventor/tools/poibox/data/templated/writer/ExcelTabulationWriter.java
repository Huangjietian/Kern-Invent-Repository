package cn.kerninventor.tools.poibox.data.templated.writer;

import cn.kerninventor.tools.poibox.BoxGadget;
import cn.kerninventor.tools.poibox.data.templated.ExcelColumn;
import cn.kerninventor.tools.poibox.data.templated.ExcelTabulation;
import cn.kerninventor.tools.poibox.data.templated.initializer.ExcelBannerInitializer;
import cn.kerninventor.tools.poibox.data.templated.initializer.ExcelColumnInitializer;
import cn.kerninventor.tools.poibox.data.templated.initializer.ExcelTabulationInitializer;
import cn.kerninventor.tools.poibox.data.templated.initializer.ReInitializer;
import cn.kerninventor.tools.poibox.data.templated.validation.DataValidationBuilderFactory;
import cn.kerninventor.tools.poibox.data.templated.validation.array.NameNameDataValid;
import cn.kerninventor.tools.poibox.layout.MergedRange;
import cn.kerninventor.tools.poibox.utils.BeanUtil;
import cn.kerninventor.tools.poibox.utils.NameManegeUtil;
import cn.kerninventor.tools.poibox.utils.ReflectUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.*;


/**
 * @Author Kern
 * @Date 2020/3/12 18:53
 * @Description TODO  模板应该支持改大标题，删减列等操作
 */
public class ExcelTabulationWriter<T> implements Writer<T> {

    protected ExcelTabulationInitializer<T> tabulationInitializer;

    protected Map<String, List<String>> nameNameMap = new HashMap<>();


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
    public Writer<T> writeTo(String sheetName, List<T> datas, String... ignore) {
        Sheet sheet = tabulationInitializer.getParent().getSheet(sheetName);
        return writeTo(sheet, datas, ignore);
    }

    @Override
    public Writer<T> writeTo(int sheetAt, List<T> datas, String... ignore) {
        Sheet sheet = tabulationInitializer.getParent().getSheet(sheetAt);
        return writeTo(sheet, datas, ignore);
    }

    @Override
    public Writer<T> writeTo(Sheet sheet, List<T> datas, String... ignore) {
        if (BeanUtil.isEmpty(datas)) {
            execute(sheet, null, ignore,

                (t, c, s, d) -> {
                    CellStyle cellStyle = c.getColumnStyle();
                    if (BeanUtil.isNotEmpty(c.getDataFormatEx())){
                        if (cellStyle.equals(t.getTbodyStyle())) {
                            cellStyle = t.getParent().styler().copyStyle(cellStyle);
                        }
                        cellStyle.setDataFormat(
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
                        textCell.setCellStyle(cellStyle);
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
            execute(sheet, datas, ignore,

                (t, c, s, d) -> {
                    CellStyle cellStyle = c.getColumnStyle();
                    if (BeanUtil.isNotEmpty(c.getDataFormatEx())){
                        if (cellStyle.equals(t.getTbodyStyle())) {
                            cellStyle = t.getParent().styler().copyStyle(cellStyle);
                        }
                        cellStyle.setDataFormat(
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
                        cell.setCellStyle(cellStyle);
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

    @Override
    public Writer<T> addNameName(Map<String, List<String>> nameNameMap) {
        this.nameNameMap.putAll(nameNameMap);
        return this;
    }

    public void execute(Sheet sheet, List datas, final String[] igonre, final TbodyWriter tbodyWriter) {
        if (sheet.getSheetName().equals(NameManegeUtil.HIDDEN_SHEET_NAME)){
            throw new IllegalArgumentException("Sheet name can't be application's constant:" + sheet.getSheetName());
        }
        if (!nameNameMap.isEmpty()){
            nameNameMap.keySet().forEach(e -> {
                NameManegeUtil.addNameManage(sheet, NameNameDataValid.NAME_PRIFIIX + e, nameNameMap.get(e));
            });
            nameNameMap.clear();
        }

        ExcelTabulationInitializer tabulationInitializer = getTabulationInitializer();
        List<ExcelColumnInitializer> columnsContainer = tabulationInitializer.getColumnsContainer();
        if (BeanUtil.hasElement(igonre)) {
            columnsContainer = ((ReInitializer<List<ExcelColumnInitializer>>) colums -> {
                List<ExcelColumnInitializer> result = new ArrayList<>();
                colums.forEach(column -> {
                    if (Arrays.stream(igonre).noneMatch(e -> column.getTitleName().equals(e.trim()))) {
                        result.add(column);
                    }
                });
                return result;

            }).reInit(columnsContainer);
            tabulationInitializer.reIndex(columnsContainer);
        }
        Row headRow = setRowHeight(
                sheet.createRow(
                        tabulationInitializer.getTheadRowIndex()
                ),
                tabulationInitializer.getTheadRowHeight()
        );
        Short theadFontHeightInPoints = BoxGadget.getFontFrom(
                tabulationInitializer.getTheadStyle(),
                tabulationInitializer.getParent().workbook()
        ).getFontHeightInPoints();

        CellStyle theadStyle = tabulationInitializer.getTheadStyle();
        for (Iterator<ExcelColumnInitializer> iterator = columnsContainer.iterator() ; iterator.hasNext() ; ) {
            ExcelColumnInitializer column = iterator.next();
            Cell headRowCell = headRow.createCell(column.getColumnIndex());
            headRowCell.setCellValue(column.getTitleName());
            headRowCell.setCellStyle(theadStyle);
            setColumnWidth(tabulationInitializer, column, sheet, 0, theadFontHeightInPoints);
            tbodyWriter.templateTbody(tabulationInitializer, column, sheet, datas);
        }
        tempalateBanners(tabulationInitializer, columnsContainer, sheet);
    }

    private void setColumnWidth(ExcelTabulationInitializer tabulation, ExcelColumnInitializer column, Sheet sheet, int width, int var) {
        if (column.getColumnWidth() == ExcelColumn.DEFAULT_COLUMN_WIDTH){
            width = BoxGadget.getCellWidthByContent(column.getTitleName(), var);
            width = width < tabulation.getMinimumColumnsWidth() ? tabulation.getMinimumColumnsWidth() : width;
        } else {
            width = BoxGadget.adjustCellWidth(column.getColumnWidth());
        }
        sheet.setColumnWidth(column.getColumnIndex(), width);
    }

    private Row setRowHeight(Row row, float height) {
        if (ExcelTabulation.DEF_ROW_HEIGHT != height) {
            row.setHeightInPoints(height);
        }
        return row;
    }

    private void tempalateBanners(ExcelTabulationInitializer tabulation, List<ExcelColumnInitializer> columns, Sheet sheet) {
        List<ExcelBannerInitializer> bannerContainer = tabulation.getBannerContainer();
        bannerContainer.forEach(e -> {
            MergedRange mergedRange = tabulation.getParent().layouter().mergedRegion(
                    sheet,
                    e.adjustCellRangeAddress(tabulation, columns)
            );
            //TODO 某些情况下生成多行的数据时，如何进行换行和如何调整行高。
            mergedRange.setMergeRangeContent(e.getValue()).setMergeRangeStyle(e.getCellStyle());
        });
    }

}
