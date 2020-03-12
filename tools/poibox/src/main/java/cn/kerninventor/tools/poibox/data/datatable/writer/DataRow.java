package cn.kerninventor.tools.poibox.data.datatable.writer;

import org.apache.poi.ss.usermodel.Row;

import java.util.List;

/**
 * @Title: DataRow
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.data.datatable.writer
 * @Author Kern
 * @Date 2020/3/12 21:58
 * @Description: TODO
 */
public class DataRow <T>{

    private Class<T> tClass;

    private T t;

    private Row row;

    private List<DataCell> dataCells;
}
