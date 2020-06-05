package cn.kerninventor.tools.poibox.data.tabulation.writer;

import cn.kerninventor.tools.poibox.data.tabulation.context.BannerDefinition;
import cn.kerninventor.tools.poibox.data.tabulation.context.ColumnDefinition;
import cn.kerninventor.tools.poibox.data.tabulation.context.TabContextModifier;
import cn.kerninventor.tools.poibox.data.tabulation.context.TableContext;
import cn.kerninventor.tools.poibox.data.tabulation.element.Textbox;
import cn.kerninventor.tools.poibox.data.tabulation.translator.AbstractColumnDataTranslator;
import cn.kerninventor.tools.poibox.data.tabulation.translator.ColumnDataTranslate;
import cn.kerninventor.tools.poibox.data.tabulation.translator.ColumnDataTranslator;
import cn.kerninventor.tools.poibox.data.tabulation.validation.array.FormulaListDataValid;
import cn.kerninventor.tools.poibox.data.tabulation.writer.tbody.TbodyWriter;
import cn.kerninventor.tools.poibox.data.tabulation.writer.tbody.TbodyWriterFactory;
import cn.kerninventor.tools.poibox.layout.Layouter;
import cn.kerninventor.tools.poibox.utils.BeanUtil;
import cn.kerninventor.tools.poibox.utils.FormulaListUtil;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.*;
import java.util.stream.Collectors;


/**
 * @author Kern
 * @date 2020/3/12 18:53
 */
public final class ETabulationWriter<T> extends AbstractColumnDataTranslator implements TabulationWriter<T> {

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
        TbodyWriter<T> tbodyWriter = TbodyWriterFactory.getTbodyWriter(this,null);
        BasicTabulationWriter basicTabulationWriter = new BasicTabulationWriter<T>(tbodyWriter);
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
        TbodyWriter<T> tbodyWriter = TbodyWriterFactory.getTbodyWriter(this, datas);
        BasicTabulationWriter basicTabulationWriter = new BasicTabulationWriter<T>(tbodyWriter);
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
        Layouter layouter = tableContext.getParent().layouter();
        List<BannerDefinition> bannerDefinitions = tableContext.getBannerDefinitions();
        for (BannerDefinition bannerDefinition : bannerDefinitions) {
            CellRangeAddress cellAddresses = bannerDefinition.adjustCellRangeAddress(tableContext, columnDefinitions);
            layouter.mergedRegion(sheet, cellAddresses)
                    .setRowHeight(bannerDefinition.getRowHeight())
                    .setMergeRangeContent(bannerDefinition.getValue())
                    .setMergeRangeStyle(bannerDefinition.getCellStyle());
        }
    }

    private void writeTextbox(TableContext tableContext, Sheet sheet) {
        Textbox[] textboxes = tableContext.getTextboxes();
        for (Textbox textbox : textboxes) {
            tableContext.getParent().layouter().addTextBox(sheet, textbox);
        }
    }

    @Override
    public TabulationWriter<T> withFormulaList(String name, Set<String> formulaList) {
        if (this.formulaListMap == null) {
            this.formulaListMap = new HashMap<>();
        }
        this.formulaListMap.put(name, formulaList);
        return this;
    }

    @Override
    public TabulationWriter<T> withAllFormulaList(Map<String, Set<String>> formulaListMap) {
        if (this.formulaListMap == null) {
            this.formulaListMap = new HashMap<>();
        }
        this.formulaListMap.putAll(formulaListMap);
        return this;
    }

    @Override
    public TabulationWriter<T> withColumnDataTranslator(String translatorName, ColumnDataTranslator columnDataTranslator) {
        this.putTranslator(translatorName, columnDataTranslator);
        return this;
    }

    @Override
    public TabulationWriter<T> withAllColumnDataTranslator(Map<String, ColumnDataTranslator> columnDataTranslatorMap) {
        this.putAllTranslator(columnDataTranslatorMap);
        return this;
    }

    private Map<String, Set<String>> getFormulaListMap() {
        return formulaListMap;
    }

    @Override
    public TabContextModifier getTabContextModifier() {
        return tableContext;
    }

    @Override
    public Object translate(ColumnDataTranslate translate, Object searchCondition) {
        if (translate.open()) {
            return getValue(translate.translator(), searchCondition, translate.tag(), translate.unmatchStrategy());
        }
        return searchCondition;
    }
}
