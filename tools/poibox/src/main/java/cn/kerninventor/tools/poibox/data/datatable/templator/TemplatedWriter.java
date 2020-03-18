package cn.kerninventor.tools.poibox.data.datatable.templator;

import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;

/**
 * @Title TemplatedWriter
 * @ProjectName kerninventresp
 * @PackageName cn.kerninventor.tools.poibox.data.datatable
 * @Author Kern
 * @Date 2020/3/16 19:26
 * @Description TODO
 */
public interface TemplatedWriter<T> {

    boolean getDefaultMergedMode();

    TemplatedWriter setDefaultMergedMode(boolean isMergeColum);

    TemplatedWriter<T> writeTo(String sheetName, List<T> datas);

    TemplatedWriter<T> writeTo(int sheetAt, List<T> datas);

    TemplatedWriter<T> writeTo(Sheet sheet, List<T> datas);
}
