package cn.kerninventor.tools.poibox.opensource.data.templated.writer;

import cn.kerninventor.tools.poibox.opensource.BoxGadget;
import cn.kerninventor.tools.poibox.opensource.data.templated.ExcelColumn;
import cn.kerninventor.tools.poibox.opensource.data.templated.ExcelTabulation;
import cn.kerninventor.tools.poibox.opensource.data.templated.initializer.EBannerInitiator;
import cn.kerninventor.tools.poibox.opensource.data.templated.initializer.EColumnInitiator;
import cn.kerninventor.tools.poibox.opensource.data.templated.initializer.ETabulationInitiator;
import cn.kerninventor.tools.poibox.opensource.data.templated.initializer.ReInitializer;
import cn.kerninventor.tools.poibox.opensource.data.templated.initializer.configuration.TabConfiguration;
import cn.kerninventor.tools.poibox.opensource.data.templated.validation.DataValidationBuilderFactory;
import cn.kerninventor.tools.poibox.opensource.data.templated.validation.array.FormulaListDataValid;
import cn.kerninventor.tools.poibox.opensource.data.templated.writer.tbody.TbodyWriter;
import cn.kerninventor.tools.poibox.opensource.layout.MergedRange;
import cn.kerninventor.tools.poibox.opensource.style.Styler;
import cn.kerninventor.tools.poibox.opensource.utils.BeanUtil;
import cn.kerninventor.tools.poibox.opensource.utils.NameManegeUtil;
import cn.kerninventor.tools.poibox.opensource.utils.ReflectUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.*;


/**
 * @Author Kern
 * @Date 2020/3/12 18:53
 * @Description
 */
public final class ETabulationWriter<T> implements Writer<T> {

    private ETabulationInitiator<T> tabInitiator;

    private Map<String, List<String>> nameNameMap = new HashMap<>();

    public ETabulationWriter(ETabulationInitiator<T> tabInitiator) {
        this.tabInitiator = Objects.requireNonNull(tabInitiator);
    }

    private ETabulationInitiator getTabulationInitializer() {
        return this.tabInitiator;
    }

    @Override
    public Writer<T> writeTo(String sheetName) {
        Sheet sheet = tabInitiator.getParent().getSheet(sheetName);
        return writeTo(sheet);
    }

    @Override
    public Writer<T> writeTo(int sheetAt) {
        Sheet sheet = tabInitiator.getParent().getSheet(sheetAt);
        return writeTo(sheet);
    }

    @Override
    public Writer<T> writeTo(Sheet sheet) {
        TbodyWriter tbodyWriter = getTemplateTbodyWriter();
        execute(sheet, null, null, tbodyWriter);
        return this;
    }

    @Override
    public Writer<T> writeTo(String sheetName, List<T> datas, String... ignore) {
        Sheet sheet = tabInitiator.getParent().getSheet(sheetName);
        return writeTo(sheet, datas, ignore);
    }

    @Override
    public Writer<T> writeTo(int sheetAt, List<T> datas, String... ignore) {
        Sheet sheet = tabInitiator.getParent().getSheet(sheetAt);
        return writeTo(sheet, datas, ignore);
    }

    @Override
    public Writer<T> writeTo(Sheet sheet, List<T> datas, String... ignore) {
        TbodyWriter tbodyWriter = getDataTbodyWriter();
        execute(sheet, datas, ignore, tbodyWriter);
        return this;
    }

    private void execute(Sheet sheet, List datas, final String[] igonre, final TbodyWriter tbodyWriter) {
        getTabulationInitializer().init();
        //1. 设置名称管理器
        setNameManager(sheet);
        //2. 过滤ignore 列
        ETabulationInitiator tabulation = getTabulationInitializer();
        List<EColumnInitiator> columnsContainer = tabulation.getColumnsContainer();
        if (BeanUtil.hasElement(igonre)) {
            columnsContainer = ((ReInitializer<List<EColumnInitiator>>) colums -> {
                List<EColumnInitiator> result = new ArrayList<>();
                colums.forEach(column -> {
                    if (Arrays.stream(igonre).noneMatch(e -> column.getTitleName().equals(e.trim()))) {
                        result.add(column);
                    }
                });
                return result;
            }).reInit(columnsContainer);
            tabulation.setColumnsIndex(columnsContainer);
        }
        //3. 绘制表头
        Row headRow = sheet.createRow(tabulation.getTheadRowIndex());
        setRowHeight(headRow, tabulation.getTheadRowHeight());
        Workbook workbook = tabulation.getParent().workbook();
        DataFormat dataFormat = workbook.createDataFormat();
        Styler styler = tabulation.getParent().styler();
        for (Iterator<EColumnInitiator> iterator = columnsContainer.iterator(); iterator.hasNext() ; ) {
            EColumnInitiator column = iterator.next();
            Cell headRowCell = headRow.createCell(column.getColumnIndex());
            headRowCell.setCellValue(column.getTitleName());
            headRowCell.setCellStyle(column.getTheadStyle());
            //设置列宽
            Short theadFontHeightInPoints = BoxGadget.getFontFrom(column.getTheadStyle(), workbook).getFontHeightInPoints();
            setColumnWidth(tabulation, column, sheet, 0, theadFontHeightInPoints);
            //设置单元格格式
            CellStyle tbodyStyle = column.getTbodyStyle();
            if (BeanUtil.isNotEmpty(column.getDataFormatEx())){
                tbodyStyle = styler.copyStyle(tbodyStyle);
                tbodyStyle.setDataFormat(dataFormat.getFormat(column.getDataFormatEx()));
            }
            //4. 绘制表体
            tbodyWriter.templateTbody(tabulation, column, tbodyStyle, sheet, datas);
        }
        //5. 写横幅
        tempalateBanners(tabulation, columnsContainer, sheet);
    }

    private TbodyWriter getTemplateTbodyWriter(){
        return (table, col, tbSt, sh, data) -> {
            for (int rowIndex = table.getTbodyFirstRowIndex() ; rowIndex < table.getEffectiveRows() + table.getTbodyFirstRowIndex(); rowIndex ++){
                Row bodyRow = BoxGadget.getRowForce(sh, rowIndex);
                setRowHeight(bodyRow, table.getTbodyRowHeight());
                Cell bodyCell = bodyRow.createCell(col.getColumnIndex());
                //设置风格
                if (tbSt != null) {
                    bodyCell.setCellStyle(tbSt);
                }
                //设置函数
                if (BeanUtil.isNotEmpty(col.getFormula())) {
                    bodyCell.setCellFormula(col.getFormula());
                }
            }
            //设置数据有效性校验
            if (col.getValidAnnotation() != null) {
                DataValidationBuilderFactory.getInstance(col.getValidAnnotation()).addValidation(table, col, sh);
            }
        };
    }

    private TbodyWriter getDataTbodyWriter(){
        return (table, col, tbSt, sh, data) -> {
            for (int datasIndex = 0, rowIndex = table.getTbodyFirstRowIndex(); datasIndex < data.size() ; datasIndex ++ , rowIndex++){
                Row bodyRow = BoxGadget.getRowForce(sh, rowIndex);
                setRowHeight(bodyRow, table.getTbodyRowHeight());
                Cell bodyCell = bodyRow.createCell(col.getColumnIndex());
                //设置风格
                if (tbSt != null) {
                    bodyCell.setCellStyle(tbSt);
                }
                Object value = null;
                try {
                    value = ReflectUtil.getFieldValue(col.getField(), data.get(datasIndex));
                } catch (IllegalAccessException e) {
                    throw new IllegalArgumentException("Field value get error., field name: " + col.getFieldName());
                }
                //翻译 FIXME 去掉翻译功能
                if (col.getInterpretor().isInterpretable()) {
                    value = col.getInterpretor().interpreteOf(value);
                }
                col.getColWriter().setCellValue(bodyCell, value);
            }
            col.getColWriter().flush();
        };
    }

    private void setNameManager(Sheet sheet) {
        if (sheet.getSheetName().equals(NameManegeUtil.HIDDEN_SHEET_NAME)){
            throw new IllegalArgumentException("Sheet name can't be application's constant:" + sheet.getSheetName());
        }
        //设值名称管理器
        if (!nameNameMap.isEmpty()){
            nameNameMap.keySet().forEach(e -> {
                NameManegeUtil.addNameManage(sheet, FormulaListDataValid.NAME_PRIFIIX + e, nameNameMap.get(e));
            });
            nameNameMap.clear();
        }
    }

    private void setColumnWidth(ETabulationInitiator tabulation, EColumnInitiator column, Sheet sheet, int width, int var) {
        if (column.getColumnWidth() == ExcelColumn.DefaultColumnWidth){
            width = BoxGadget.getCellWidthByContent(column.getTitleName(), var);
            width = width < tabulation.getMinimumColumnsWidth() ? tabulation.getMinimumColumnsWidth() : width;
            width = width > tabulation.getMaxmunColumnsWidth() ? tabulation.getMaxmunColumnsWidth() : width;
        } else {
            width = BoxGadget.adjustCellWidth(column.getColumnWidth());
        }
        sheet.setColumnWidth(column.getColumnIndex(), width);
    }

    private void setRowHeight(Row row, float height) {
        if (ExcelTabulation.DefaultRowHeight != height) {
            row.setHeightInPoints(height);
        }
    }

    private void tempalateBanners(ETabulationInitiator tabulation, List<EColumnInitiator> columns, Sheet sheet) {
        List<EBannerInitiator> bannerContainer = tabulation.getBannerContainer();
        bannerContainer.forEach(e -> {
            MergedRange mergedRange = tabulation.getParent().layouter().mergedRegion(
                    sheet,
                    e.adjustCellRangeAddress(tabulation, columns)
            );
            //TODO 某些情况下生成多行的数据时，如何进行换行和如何调整行高。
            mergedRange.setRowHeight(e.getRowHeight()).setMergeRangeContent(e.getValue()).setMergeRangeStyle(e.getCellStyle());
        });
    }

    @Override
    public Writer<T> addNameName(Map<String, List<String>> nameNameMap) {
        this.nameNameMap.putAll(nameNameMap);
        return this;
    }

    @Override
    public TabConfiguration getConfiguration() {
        return tabInitiator;
    }

    @Override
    public Writer addBanner(String value, CellStyle style, CellRangeAddress range) {
        this.tabInitiator.addBanner(value, style, range);
        return this;
    }

    @Override
    public Writer addBanner(String value, CellStyle style, int row1, int row2) {
        this.tabInitiator.addBanner(value, style, row1, row2);
        return null;
    }

    @Override
    public Writer addBanner(String value, CellStyle style, int row1, int row2, int col1, int col2) {
        this.tabInitiator.addBanner(value, style, row1, row2, col1, col2);
        return this;
    }
}
