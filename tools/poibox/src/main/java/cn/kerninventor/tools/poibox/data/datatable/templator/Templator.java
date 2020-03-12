package cn.kerninventor.tools.poibox.data.datatable.templator;

import org.apache.poi.ss.usermodel.Sheet;

/**
 * @Title: Template
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.data.datatable.templator
 * @Author Kern
 * @Date 2020/3/12 23:04
 * @Description: TODO
 */
public interface Templator<T> {

    Headline getHeadline();

    Templator tabulateTo(Sheet sheet, boolean valid);

    Templator ReTemplate(Sheet sheet);
}
