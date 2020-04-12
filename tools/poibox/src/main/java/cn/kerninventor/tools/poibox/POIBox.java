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
* @author Kern
* @date 2019/10/29 19:06
* @description
* @中文描述
*/
public interface POIBox {

    /**
    * @author Kern
    * @date 2020/4/9
    * @description
    * @中文描述 数据处理器
    */
    DataTabulator dataTabulator();

    /**
    * @author Kern
    * @date 2020/4/9
    * @description
    * @中文描述 风格生成器
    */
    Styler styler();

    /**
    * @author Kern
    * @date 2020/4/9
    * @description
    * @中文描述 字体生成器
    */
    Fonter fonter();

    /**
    * @author Kern
    * @date 2020/4/9
    * @description
    * @中文描述 布局工具
    */
    Layouter layouter();

    /**
    * @author Kern
    * @date 2020/4/9
    * @description
    * @中文描述
    */
    Workbook workbook();

    /**
    * @author Kern
    * @date 2020/4/9
    * @description
    * @中文描述
    */
    Sheet getSheet(String sheetName);

    /**
    * @author Kern
    * @date 2020/4/9
    * @description
    * @中文描述
    */
    Sheet getSheet(int sheetAt);

    /**
    * @author Kern
    * @date 2020/4/9
    * @description
    * @中文描述 写入http响应
    */
    void writeToHttp(HttpServletResponse response, String fileName) throws IOException;

    /**
    * @author Kern
    * @date 2020/4/9
    * @description
    * @中文描述 写入本地文件
    */
    void writeToLocal(String fileFullName) throws IOException;


}
