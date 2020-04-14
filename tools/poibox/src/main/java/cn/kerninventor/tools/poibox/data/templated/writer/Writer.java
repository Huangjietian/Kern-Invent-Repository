package cn.kerninventor.tools.poibox.data.templated.writer;

import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;

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

    Writer<T> writeTo(String sheetName, List<T> datas);

    Writer<T> writeTo(int sheetAt, List<T> datas);

    Writer<T> writeTo(Sheet sheet, List<T> datas);

}
