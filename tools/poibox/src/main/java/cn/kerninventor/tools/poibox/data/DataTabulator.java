package cn.kerninventor.tools.poibox.data;

import cn.kerninventor.tools.poibox.data.datatable.templator.Templator;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;

/**
 * @Title: POIDataProcessor
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.data
 * @Author Kern
 * @Date 2019/12/9 14:50
 * @Description: TODO
 */
public interface DataTabulator {

    /**
     * Create template on specified name with sheet
     * @param sheetName
     * @param sourceClass
     * @param <T>
     * @return
     */
    <T> Templator<T> templateTo(String sheetName, Class<T> sourceClass);

    /**
     * Create template on specified index with sheet
     * @param sheetIndex
     * @param sourceClass
     * @param <T>
     * @return
     */
    <T> Templator<T> templateTo(int sheetIndex, Class<T> sourceClass);

    /**
     * Create template on specified sheet
     * @param sheet
     * @param sourceClass
     * @param <T>
     * @return
     */
    <T> Templator<T> templateTo(Sheet sheet, Class<T> sourceClass);

    /**
     * Write datas into specified name with sheet
     * @param sheetName
     * @param datas
     * @param templator
     * @param <T>
     */
    <T> void writeDataTo(String sheetName, List<T> datas, Templator<T> templator);

    /**
     * Write datas into specified index with sheet
     * @param sheetIndex
     * @param datas
     * @param templator
     * @param <T>
     */
    <T> void writeDataTo(int sheetIndex, List<T> datas, Templator<T> templator);

    /**
     * Write datas into specified sheet
     * @param sheet
     * @param datas
     * @param templator
     * @param <T>
     */
    <T> void writeDataTo(Sheet sheet, List<T> datas, Templator<T> templator);

    /**
     * Extract datas from specified name with sheet
     * @param sheetName
     * @param clazz
     * @param <T>
     * @return
     * @throws NoSuchMethodException
     */
    <T> List<T> readDatasFrom(String sheetName, Class<T> clazz);

    /**
     * Extract datas from specified index with sheet
     * @param sheetIndex
     * @param clazz
     * @param <T>
     * @return
     * @throws NoSuchMethodException
     */
    <T> List<T> readDatasFrom(int sheetIndex, Class<T> clazz) ;

    /**
     * Extract datas from specified sheet
     * @param sheet
     * @param clazz
     * @param <T>
     * @return
     */
    <T> List<T> readDatasFrom(Sheet sheet, Class<T> clazz);
}
