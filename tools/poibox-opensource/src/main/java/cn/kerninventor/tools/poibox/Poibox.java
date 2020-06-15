package cn.kerninventor.tools.poibox;

import cn.kerninventor.tools.poibox.data.DataTabulator;
import cn.kerninventor.tools.poibox.layout.Layouter;
import cn.kerninventor.tools.poibox.style.Fonter;
import cn.kerninventor.tools.poibox.style.Styler;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <h1>中文注释</h1>
 * <p>
 *     Poibox, excel处理工具盒子接口。<br/>
 *     底层基于 org.apache.poi 实现
 * </p>
* @author Kern
*/
public interface Poibox {

    /**
     * <p>
     *     中文注释:<br/>
     *     数据处理器
     *
     * </p>
     * @return
     */
    DataTabulator dataTabulator();

    /**
     *
     *
    */
    Styler styler();

    /**
     *
     *
    */
    Fonter fonter();

    /**
     *
     *
    */
    Layouter layouter();

    /**
     *
     *
    */
    Workbook workbook();

    /**
     *
     *
    */
    Sheet getSheet(String sheetName);

    /**
     *
     *
    */
    Sheet getSheet(int sheetAt);

    /**
     *
     *
    */
    void writeToHttp(HttpServletResponse response, String fileName) throws IOException;

    /**
     *
     *
    */
    void writeToLocal(String fileFullName) throws IOException;

    /**
     *
     *
    */
    void flush();
}
