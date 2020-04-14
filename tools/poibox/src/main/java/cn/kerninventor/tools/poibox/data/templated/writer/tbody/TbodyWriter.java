package cn.kerninventor.tools.poibox.data.templated.writer.tbody;

import cn.kerninventor.tools.poibox.data.templated.initializer.ExcelColumnInitializer;
import cn.kerninventor.tools.poibox.data.templated.initializer.ExcelTabulationInitializer;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;

/**
 * @author Kern
 * @date 2020/4/10 14:10
 * @description
 */
public interface TbodyWriter {

    void templateTbody(ExcelTabulationInitializer tabulation, ExcelColumnInitializer column, Sheet sheet, List data);

}
