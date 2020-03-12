package cn.kerninventor.tools.poibox.data;

import cn.kerninventor.tools.poibox.data.datatable.templator.ExcelTabulationTemplator;
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
     * Generate template to specified name sheet
     * @param sheetName
     * @param sourceClass
     * @return
     */
    ExcelTabulationTemplator templateTo(String sheetName, Class sourceClass);

    /**
     * Generate template to specified index sheet
     * @param sheetIndex
     * @param sourceClass
     * @return
     */
    ExcelTabulationTemplator templateTo(int sheetIndex, Class sourceClass);

    /**
     * Generate template to specified index sheet
     * @param sheet
     * @param sourceClass
     * @return
     */
    ExcelTabulationTemplator templateTo(Sheet sheet, Class sourceClass);

    /**
     * Write datas into specified name sheet
     * @param sheetName
     * @param sourceClass
     * @param datas
     * @param <T>
     */
    <T> void writeDataTo(String sheetName, Class<T> sourceClass, List<T> datas);

    /**
     * Write datas into specified index sheet
     * @param sheetIndex
     * @param sourceClass
     * @param datas
     * @param <T>
     */
    <T> void writeDataTo(int sheetIndex, Class<T> sourceClass, List<T> datas);

    /**
     * Write datas into specified sheet
     * @param sheet
     * @param sourceClass
     * @param datas
     * @param <T>
     */
    <T> void writeDataTo(Sheet sheet, Class<T> sourceClass, List<T> datas);

    /**
     * Extract datas from specified name sheet
     * @param sheetName
     * @param clazz
     * @param <T>
     * @return
     * @throws NoSuchMethodException
     */
    <T> List<T> readDatasFrom(String sheetName, Class<T> clazz);

    /**
     * Extract datas from specified index sheet
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
