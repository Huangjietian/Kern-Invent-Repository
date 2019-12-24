package cn.kerninventor.tools.poibox.data;

import cn.kerninventor.tools.poibox.data.datatable.result.ExcelTemplate;

import java.util.List;

/**
 * @Title: POIDataProcessor
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.data
 * @Author Kern
 * @Date 2019/12/9 14:50
 * @Description: TODO
 */
public interface POIDataProcessor {

    /**
     * Generate template to specified name sheet
     * @param sheetName
     * @param sourceClass
     * @return
     */
    ExcelTemplate templateTo(String sheetName, Class sourceClass);

    /**
     * Generate template to specified index sheet
     * @param sheetIndex
     * @param sourceClass
     * @return
     */
    ExcelTemplate templateTo(int sheetIndex, Class sourceClass);

    /**
     * Extract datas from specified name sheet
     * @param sheetName
     * @param clazz
     * @param <T>
     * @return
     * @throws NoSuchMethodException
     */
    <T> List<T> readDatasFrom(String sheetName, Class<T> clazz) throws NoSuchMethodException;

    /**
     * Extract datas from specified index sheet
     * @param sheetIndex
     * @param clazz
     * @param <T>
     * @return
     * @throws NoSuchMethodException
     */
    <T> List<T> readDatasFrom(int sheetIndex, Class<T> clazz) throws NoSuchMethodException;
}
