package cn.kerninventor.tools.poibox.data;

import org.apache.poi.ss.usermodel.Sheet;

/**
 * @Title: POIDataProcessor
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.data
 * @Author Kern
 * @Date 2019/12/9 14:50
 * @Description: TODO
 */
public interface POIDataBox {


    Sheet getSheet();

    int getSheetIndex();

    void template(Class clazz);
}
