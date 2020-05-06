package cn.kerninventor.tools.poibox.opensource;

import cn.kerninventor.tools.poibox.opensource.data.DataTabulator;
import cn.kerninventor.tools.poibox.opensource.developer.SealingVersion;
import cn.kerninventor.tools.poibox.opensource.layout.Layouter;
import cn.kerninventor.tools.poibox.opensource.style.Fonter;
import cn.kerninventor.tools.poibox.opensource.style.Styler;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
* @author Kern
* @date 2019/10/29 19:06
*/
@SealingVersion(
        version = 1.00,
        zh_description = "poibox 接口",
        possible = "比较不可能做的实现之一： 增加打压缩包功能"
)
public interface Poibox {

    /**
     *
     *
     * @author Kern
     * @date 2020/5/6
    */
    DataTabulator dataTabulator();

    /**
     *
     *
     * @author Kern
     * @date 2020/5/6
    */
    Styler styler();

    /**
     *
     *
     * @author Kern
     * @date 2020/5/6
    */
    Fonter fonter();

    /**
     *
     *
     * @author Kern
     * @date 2020/5/6
    */
    Layouter layouter();

    /**
     *
     *
     * @author Kern
     * @date 2020/5/6
    */
    Workbook workbook();

    /**
     *
     *
     * @author Kern
     * @date 2020/5/6
    */
    Sheet getSheet(String sheetName);

    /**
     *
     *
     * @author Kern
     * @date 2020/5/6
    */
    Sheet getSheet(int sheetAt);

    /**
     *
     *
     * @author Kern
     * @date 2020/5/6
    */
    void writeToHttp(HttpServletResponse response, String fileName) throws IOException;

    /**
     *
     *
     * @author Kern
     * @date 2020/5/6
    */
    void writeToLocal(String fileFullName) throws IOException;

    /**
     *
     *
     * @author Kern
     * @date 2020/5/6
    */
    void flush();
}
