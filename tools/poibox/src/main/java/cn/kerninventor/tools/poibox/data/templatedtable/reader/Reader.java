package cn.kerninventor.tools.poibox.data.templatedtable.reader;

import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;

/**
 * @Title Reader
 * @ProjectName kerninventresp
 * @PackageName cn.kerninventor.tools.poibox.data.datatable
 * @Author Kern
 * @Date 2020/3/16 19:27
 * @Description TODO
 */
public interface Reader<T> {

    List<T> readFrom(String sheetName);

    List<T> readFrom(int sheetAt);

    List<T> readFrom(Sheet sheet);

}
