package cn.kerninventor.tools.poibox.data;

import cn.kerninventor.tools.poibox.data.datatable.Reader;
import cn.kerninventor.tools.poibox.data.datatable.Templator;
import cn.kerninventor.tools.poibox.data.datatable.Writer;
import org.apache.poi.ss.formula.functions.T;
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
     * 模板
     * @param sourceClass
     * @return
     */
    <T> Templator<T> templator(Class<T> sourceClass);

    /**
     * 写入器
     * @return
     */
    Writer writer();

    /**
     * 读取器
     * @return
     */
    Reader reader();
}
