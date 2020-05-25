package cn.kerninventor.tools.poibox.opensource.data.tabulation.writer;

import cn.kerninventor.tools.poibox.opensource.BoxGadget;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.ExcelColumn;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.ExcelTabulation;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.context.BannerDefinition;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.context.ColumnDefinition;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.context.TabContextModifier;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.context.TableContext;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.element.Textbox;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.validation.array.FormulaListDataValid;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.writer.chain.WriteThread;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.writer.tbody.TableBodyDataWriter;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.writer.tbody.TableBodyStyleWriter;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.writer.tbody.TbodyWriter;
import cn.kerninventor.tools.poibox.opensource.layout.MergedRange;
import cn.kerninventor.tools.poibox.opensource.utils.BeanUtil;
import cn.kerninventor.tools.poibox.opensource.utils.FormulaListUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.*;
import java.util.stream.Collectors;


/**
 * @author Kern
 * @date 2020/3/12 18:53
 */
public final class ETabulationWriter<T> implements TabulationWriter<T> {

    private TableContext<T> tableContext;

    private Map<String, Set<String>> formulaListMap;

    public ETabulationWriter(TableContext<T> context) {
        this.tableContext = Objects.requireNonNull(context);
    }

    private TableContext getTableContext() {
        return this.tableContext;
    }

    @Override
    public TabulationWriter<T> writeTo(String sheetName) {
        Sheet sheet = tableContext.getParent().getSheet(sheetName);
        return writeTo(sheet);
    }

    @Override
    public TabulationWriter<T> writeTo(Sheet sheet) {
        TbodyWriter tbodyWriter = new TableBodyStyleWriter();
        BasicTabulationWriter basicTabulationWriter = new BasicTabulationWriter(tbodyWriter);
        doWrite(sheet, null, basicTabulationWriter);
        return this;
    }

    @Override
    public TabulationWriter<T> writeTo(String sheetName, List<T> datas, String... ignore) {
        Sheet sheet = tableContext.getParent().getSheet(sheetName);
        return writeTo(sheet, datas, ignore);
    }

    @Override
    public TabulationWriter<T> writeTo(Sheet sheet, List<T> datas, String... ignore) {
        TbodyWriter tbodyWriter = new TableBodyDataWriter(datas);
        BasicTabulationWriter basicTabulationWriter = new BasicTabulationWriter(tbodyWriter);
        doWrite(sheet, ignore, basicTabulationWriter);
        return this;
    }

    private void doWrite(Sheet sheet, final String[] igonre, final BasicTabulationWriter basicTabulationWriter) {
        TableContext tableContext = getTableContext();
        List<ColumnDefinition> columnDefinitions = adaptIgonreColumns(igonre);
        writeformulaList(sheet);
        writeTextbox(tableContext, sheet);
        writeBanners(tableContext, columnDefinitions, sheet);
        basicTabulationWriter.doWrite(tableContext, columnDefinitions, sheet);
    }

    private List<ColumnDefinition> adaptIgonreColumns(String[] igonre) {
        List<ColumnDefinition> columnDefinitions = getTableContext().getColumnDefinitions();
        if (BeanUtil.hasElement(igonre)) {
            columnDefinitions = columnDefinitions.stream().filter(e -> Arrays.stream(igonre).noneMatch(str -> e.getTitleName().equals(str.trim()))).collect(Collectors.toList());
            getTableContext().setColumnsIndex(columnDefinitions);
        }
        return columnDefinitions;
    }

    private void writeformulaList(Sheet sheet) {
        Map<String, Set<String>> formulaListMap = getFormulaListMap();
        if (formulaListMap != null && !formulaListMap.isEmpty()){
            formulaListMap.keySet().forEach(e -> FormulaListUtil.addFormulaList(sheet, FormulaListDataValid.NAME_PRIFIIX + e, formulaListMap.get(e)));
            formulaListMap.clear();
        }
    }

    private void writeBanners(TableContext tableContext, List<ColumnDefinition> columnDefinitions, Sheet sheet) {
        new WriteThread(() -> {
                List<BannerDefinition> bannerContainer = tableContext.getBannerDefinitions();
                bannerContainer.forEach(e -> {
                    MergedRange mergedRange = tableContext.getParent().layouter().mergedRegion(
                            sheet,
                            e.adjustCellRangeAddress(tableContext, columnDefinitions)
                    );
                    mergedRange.setRowHeight(e.getRowHeight()).setMergeRangeContent(e.getValue()).setMergeRangeStyle(e.getCellStyle());
                });
        }).start();
    }

    private void writeTextbox(TableContext tableContext, Sheet sheet) {
        new WriteThread(() -> {
            Textbox[] textboxes = tableContext.getTextboxes();
            for (Textbox textbox : textboxes) {
                tableContext.getParent().layouter().addTextBox(sheet, textbox);
            }
        }).start();
    }

    public static void setColumnWidth(TableContext tabulation, ColumnDefinition column, Sheet sheet, int theadFontHeightInPoints) {
        int width;
        if (column.getColumnWidth() == ExcelColumn.DefaultColumnWidth){
            width = BoxGadget.getCellWidthByContent(column.getTitleName(), theadFontHeightInPoints);
            width = width < tabulation.getMinimumColumnsWidth() ? tabulation.getMinimumColumnsWidth() : width;
            width = width > tabulation.getMaximunColumnsWidth() ? tabulation.getMaximunColumnsWidth() : width;
        } else {
            width = BoxGadget.adjustCellWidth(column.getColumnWidth());
        }
        sheet.setColumnWidth(column.getColumnIndex(), width);
    }

    public static void setRowHeight(Row row, float height) {
        if (ExcelTabulation.DefaultRowHeight != height) {
            row.setHeightInPoints(height);
        }
    }

    @Override
    public TabulationWriter<T> addFormulaList(String name, Set<String> formulaList) {
        if (this.formulaListMap == null) {
            this.formulaListMap = new HashMap<>();
        }
        this.formulaListMap.put(name, formulaList);
        return this;
    }

    @Override
    public TabulationWriter<T> addAllFormulaList(Map<String, Set<String>> formulaListMap) {
        if (this.formulaListMap == null) {
            this.formulaListMap = new HashMap<>();
        }
        this.formulaListMap.putAll(formulaListMap);
        return this;
    }

    private Map<String, Set<String>> getFormulaListMap() {
        return formulaListMap;
    }

    @Override
    public TabContextModifier getTabContextModifier() {
        return tableContext;
    }

}
