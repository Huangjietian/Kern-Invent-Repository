package cn.kerninventor.tools.poibox.opensource.data.templated.writer;

import cn.kerninventor.tools.poibox.opensource.data.templated.initializer.configuration.TabConfiguration;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.List;
import java.util.Map;

/**
 * @author Kern
 * @date 2020/3/12 23:04
 */
public interface Writer<T> {

    Writer<T> writeTo(String sheetName);

    Writer<T> writeTo(int sheetAt);

    Writer<T> writeTo(Sheet sheet);

    Writer<T> writeTo(String sheetName, List<T> datas, String... ignore);

    Writer<T> writeTo(int sheetAt, List<T> datas, String... ignore);

    Writer<T> writeTo(Sheet sheet, List<T> datas, String... ignore);

    Writer<T> addFormulaList(Map<String, List<String>> formulaListMap);

    TabConfiguration getConfiguration();

    Writer addBanner(String value, CellStyle style, CellRangeAddress range);

    Writer addBanner(String value, CellStyle style, int row1, int row2);

    Writer addBanner(String value, CellStyle style, int row1, int row2, int col1, int col2);



}
