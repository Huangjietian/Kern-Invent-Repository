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

    void writeTo(String sheetName, List<T> datas);

    void writeTo(int sheetAt, List<T> datas);

    void writeTo(Sheet sheet, List<T> datas);
}
