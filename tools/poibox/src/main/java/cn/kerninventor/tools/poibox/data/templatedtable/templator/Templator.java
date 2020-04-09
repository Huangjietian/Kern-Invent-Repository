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

    //TODO 修改在初始化时赋值。
    Headline getHeadline();

    Templator tempalateTo(String sheetName);

    Templator tempalateTo(int sheetAt);

    Templator tempalateTo(Sheet sheet);

    Templator setCellDataValid(boolean valid);

    void wirteTo(String sheetName, List<T> datas);

    void wirteTo(int sheetAt, List<T> datas);

    void wirteTo(Sheet sheet, List<T> datas);

    TemplatedWriter writer();

    TemplatedReader reader();

}
