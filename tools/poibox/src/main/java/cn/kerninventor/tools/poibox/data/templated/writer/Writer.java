package cn.kerninventor.tools.poibox.data.templated.writer;

import cn.kerninventor.tools.poibox.data.templated.initializer.configuration.TabConfiguration;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;
import java.util.Map;

/**
 * @Title: Template
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.data.datatable.templator
 * @Author Kern
 * @Date 2020/3/12 23:04
 * @Description: TODO
 */
public interface Writer<T> {

    Writer<T> writeTo(String sheetName);

    Writer<T> writeTo(int sheetAt);

    Writer<T> writeTo(Sheet sheet);

    Writer<T> writeTo(String sheetName, List<T> datas, String... ignore);

    Writer<T> writeTo(int sheetAt, List<T> datas, String... ignore);

    Writer<T> writeTo(Sheet sheet, List<T> datas, String... ignore);

    Writer<T> addNameName(Map<String, List<String>> nameNameMap);

    TabConfiguration getConfiguration();


}
