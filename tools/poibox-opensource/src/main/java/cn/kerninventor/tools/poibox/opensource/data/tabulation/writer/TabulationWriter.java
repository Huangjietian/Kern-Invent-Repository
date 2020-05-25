package cn.kerninventor.tools.poibox.opensource.data.tabulation.writer;

import cn.kerninventor.tools.poibox.opensource.data.tabulation.context.TabContextModifier;
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

    TabulationWriter<T> addFormulaList(String name, Set<String> formulaList);

    TabulationWriter<T> addAllFormulaList(Map<String, Set<String>> formulaListMap);

    TabContextModifier getTabContextModifier();

}
