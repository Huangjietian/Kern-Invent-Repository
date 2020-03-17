package cn.kerninventor.tools.poibox.data.datatable.templator;

import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;

/**
 * @Title TemplatedReader
 * @ProjectName kerninventresp
 * @PackageName cn.kerninventor.tools.poibox.data.datatable
 * @Author Kern
 * @Date 2020/3/16 19:27
 * @Description TODO
 */
public interface TemplatedReader<T> {

    List<T> readFrom(String sheetName);

    List<T> readFrom(int sheetAt);

    List<T> readFrom(Sheet sheet);
}
