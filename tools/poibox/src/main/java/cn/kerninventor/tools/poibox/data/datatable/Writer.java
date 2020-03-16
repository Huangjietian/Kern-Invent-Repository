package cn.kerninventor.tools.poibox.data.datatable;

import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;

/**
 * @Title Writer
 * @ProjectName kerninventresp
 * @PackageName cn.kerninventor.tools.poibox.data.datatable
 * @Author Kern
 * @Date 2020/3/16 19:27
 * @Description TODO
 */
public interface Writer<T> {

    void writeTo(String sheetName, List<T> datas, Templator<T> templator);

    void writeTo(int sheetAt, List<T> datas, Templator<T> templator);

    void writeTo(Sheet sheet, List<T> datas, Templator<T> templator);

}
