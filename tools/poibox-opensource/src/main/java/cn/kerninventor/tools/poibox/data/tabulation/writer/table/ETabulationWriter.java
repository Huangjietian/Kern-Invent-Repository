package cn.kerninventor.tools.poibox.data.tabulation.writer.table;

import cn.kerninventor.tools.poibox.data.tabulation.context.BannerDefinition;
import cn.kerninventor.tools.poibox.data.tabulation.context.ColumnDefinition;
import cn.kerninventor.tools.poibox.data.tabulation.context.TabulationConfiguration;
import cn.kerninventor.tools.poibox.data.tabulation.context.TabulationBeanConfiguration;
import cn.kerninventor.tools.poibox.data.tabulation.annotations.Textbox;
import cn.kerninventor.tools.poibox.data.tabulation.translator.AbstractColumnDataTranslator;
import cn.kerninventor.tools.poibox.data.tabulation.translator.ColumnDataTranslate;
import cn.kerninventor.tools.poibox.data.tabulation.translator.ColumnDataTranslator;
import cn.kerninventor.tools.poibox.data.tabulation.validation.array.FormulaListDataValid;
import cn.kerninventor.tools.poibox.data.tabulation.writer.TabulationWriter;
import cn.kerninventor.tools.poibox.data.tabulation.writer.TbodyWriter;
import cn.kerninventor.tools.poibox.data.tabulation.writer.body.TbodyWriterFactory;
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

    private TabulationBeanConfiguration<T> tableConfiguration;

    private Map<String, Set<String>> formulaListMap;

    public ETabulationWriter(TabulationBeanConfiguration<T> context) {
        this.tableConfiguration = Objects.requireNonNull(context);
    }

    private TabulationBeanConfiguration getTableConfiguration() {
        return this.tableConfiguration;
    }

    @Override
    public TabulationWriter<T> writeTo(String sheetName) {
        Sheet sheet = tableConfiguration.getParent().getSheet(sheetName);
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
        Sheet sheet = tableConfiguration.getParent().getSheet(sheetName);
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
        TabulationBeanConfiguration tableConfiguration = getTableConfiguration();
        List<ColumnDefinition> columnDefinitions = adaptIgonreColumns(igonre);
        writeformulaList(sheet);
        writeTextbox(tableConfiguration, sheet);
        writeBanners(tableConfiguration, columnDefinitions, sheet);
        basicTabulationWriter.doWrite(tableConfiguration, columnDefinitions, sheet);
    }

    private List<ColumnDefinition> adaptIgonreColumns(String[] igonre) {
        List<ColumnDefinition> columnDefinitions = getTableConfiguration().getColumnDefinitions();
        if (BeanUtil.hasElement(igonre)) {
            columnDefinitions = columnDefinitions.stream().filter(e -> Arrays.stream(igonre).noneMatch(str -> e.getTitleName().equals(str.trim()))).collect(Collectors.toList());
            TabulationConfiguration.setColumnsIndexBySort(columnDefinitions);
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

    private void writeBanners(TabulationBeanConfiguration tableConfiguration, List<ColumnDefinition> columnDefinitions, Sheet sheet) {
        Layouter layouter = tableConfiguration.getParent().layouter();
        List<BannerDefinition> bannerDefinitions = tableConfiguration.getBannerDefinitions();
        for (BannerDefinition bannerDefinition : bannerDefinitions) {
            CellRangeAddress cellAddresses = bannerDefinition.adjustCellRangeAddress(tableConfiguration, columnDefinitions);
            layouter.mergedRegion(sheet, cellAddresses)
                    .setRowHeight(bannerDefinition.getRowHeight())
                    .setMergeRangeContent(bannerDefinition.getValue())
                    .setMergeRangeStyle(bannerDefinition.getCellStyle());
        }
    }

    private void writeTextbox(TabulationBeanConfiguration tableConfiguration, Sheet sheet) {
        Textbox[] textboxes = tableConfiguration.getTextnodes();
        for (Textbox textbox : textboxes) {
            tableConfiguration.getParent().layouter().addTextBox(sheet, textbox);
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
    public TabulationConfiguration getConfiguration() {
        return tableConfiguration;
    }

    @Override
    public Object translate(ColumnDataTranslate translate, Object searchCondition) {
        if (translate.open()) {
            return getValue(translate.translator(), searchCondition, translate.tag(), translate.unmatchStrategy());
        }
        return searchCondition;
    }
}
