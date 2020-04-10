package cn.kerninventor.tools.poibox.data.templatedtable.templator;

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
public interface Templator<T> {

    Templator<T> setCellDataValid(boolean valid);

    Templator<T> tempalateTo(String sheetName);

    Templator<T> tempalateTo(int sheetAt);

    Templator<T> tempalateTo(Sheet sheet);

    /**
     * FIXME 是否提供这种方式
     * @param sheetName
     * @param datas
     * @return
     */

    Templator<T> writeTo(String sheetName, List<T> datas);

    Templator<T> writeTo(int sheetAt, List<T> datas);

    Templator<T> writeTo(Sheet sheet, List<T> datas);

    List<T> readFrom(String sheetName);

    List<T> readFrom(int sheetAt);

    List<T> readFrom(Sheet sheet);

}
