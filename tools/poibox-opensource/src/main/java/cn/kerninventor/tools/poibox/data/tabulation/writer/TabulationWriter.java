package cn.kerninventor.tools.poibox.data.tabulation.writer;

import cn.kerninventor.tools.poibox.data.tabulation.definition.TabulationDefinition;
import cn.kerninventor.tools.poibox.data.tabulation.translator.ColumnDataTranslator;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Kern
 * @date 2020/3/12 23:04
 */
public interface TabulationWriter<T> {

    TabulationWriter<T> writeTo(String sheetName);

    TabulationWriter<T> writeTo(Sheet sheet);

    TabulationWriter<T> writeTo(String sheetName, List<T> datas, String... ignore);

    TabulationWriter<T> writeTo(Sheet sheet, List<T> datas, String... ignore);

    TabulationWriter<T> withFormulaList(String name, Set<String> formulaList);

    TabulationWriter<T> withAllFormulaList(Map<String, Set<String>> formulaListMap);

    TabulationWriter<T> withColumnDataTranslator(String translatorName, ColumnDataTranslator columnDataTranslator);

    TabulationWriter<T> withAllColumnDataTranslator(Map<String, ColumnDataTranslator> columnDataTranslatorMap);

    TabulationDefinition getTabulationDefinition();

}
