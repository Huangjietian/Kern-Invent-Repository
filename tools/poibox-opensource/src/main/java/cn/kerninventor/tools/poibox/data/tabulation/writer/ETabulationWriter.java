package cn.kerninventor.tools.poibox.data.tabulation.writer;

import cn.kerninventor.tools.poibox.data.tabulation.context.BannerDefinition;
import cn.kerninventor.tools.poibox.data.tabulation.context.ClassFileColumnDefinition;
import cn.kerninventor.tools.poibox.data.tabulation.context.TabContextModifier;
import cn.kerninventor.tools.poibox.data.tabulation.context.ClassFileTableContext;
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

    private ClassFileTableContext<T> classFileTableContext;

    private Map<String, Set<String>> formulaListMap;

    public ETabulationWriter(ClassFileTableContext<T> context) {
        this.classFileTableContext = Objects.requireNonNull(context);
    }

    private ClassFileTableContext getClassFileTableContext() {
        return this.classFileTableContext;
    }

    @Override
    public TabulationWriter<T> writeTo(String sheetName) {
        Sheet sheet = classFileTableContext.getParent().getSheet(sheetName);
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
        Sheet sheet = classFileTableContext.getParent().getSheet(sheetName);
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
        ClassFileTableContext classFileTableContext = getClassFileTableContext();
        List<ClassFileColumnDefinition> classFileColumnDefinitions = adaptIgonreColumns(igonre);
        writeformulaList(sheet);
        writeTextbox(classFileTableContext, sheet);
        writeBanners(classFileTableContext, classFileColumnDefinitions, sheet);
        basicTabulationWriter.doWrite(classFileTableContext, classFileColumnDefinitions, sheet);
    }

    private List<ClassFileColumnDefinition> adaptIgonreColumns(String[] igonre) {
        List<ClassFileColumnDefinition> classFileColumnDefinitions = getClassFileTableContext().getClassFileColumnDefinitions();
        if (BeanUtil.hasElement(igonre)) {
            classFileColumnDefinitions = classFileColumnDefinitions.stream().filter(e -> Arrays.stream(igonre).noneMatch(str -> e.getTitleName().equals(str.trim()))).collect(Collectors.toList());
            getClassFileTableContext().setColumnsIndex(classFileColumnDefinitions);
        }
        return classFileColumnDefinitions;
    }

    private void writeformulaList(Sheet sheet) {
        Map<String, Set<String>> formulaListMap = getFormulaListMap();
        if (formulaListMap != null && !formulaListMap.isEmpty()){
            formulaListMap.keySet().forEach(e -> FormulaListUtil.addFormulaList(sheet, FormulaListDataValid.NAME_PRIFIIX + e, formulaListMap.get(e)));
            formulaListMap.clear();
        }
    }

    private void writeBanners(ClassFileTableContext classFileTableContext, List<ClassFileColumnDefinition> classFileColumnDefinitions, Sheet sheet) {
        Layouter layouter = classFileTableContext.getParent().layouter();
        List<BannerDefinition> bannerDefinitions = classFileTableContext.getBannerDefinitions();
        for (BannerDefinition bannerDefinition : bannerDefinitions) {
            CellRangeAddress cellAddresses = bannerDefinition.adjustCellRangeAddress(classFileTableContext, classFileColumnDefinitions);
            layouter.mergedRegion(sheet, cellAddresses)
                    .setRowHeight(bannerDefinition.getRowHeight())
                    .setMergeRangeContent(bannerDefinition.getValue())
                    .setMergeRangeStyle(bannerDefinition.getCellStyle());
        }
    }

    private void writeTextbox(ClassFileTableContext classFileTableContext, Sheet sheet) {
        Textbox[] textboxes = classFileTableContext.getTextnodes();
        for (Textbox textbox : textboxes) {
            classFileTableContext.getParent().layouter().addTextBox(sheet, textbox);
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
        return classFileTableContext;
    }

    @Override
    public Object translate(ColumnDataTranslate translate, Object searchCondition) {
        if (translate.open()) {
            return getValue(translate.translator(), searchCondition, translate.tag(), translate.unmatchStrategy());
        }
        return searchCondition;
    }
}
