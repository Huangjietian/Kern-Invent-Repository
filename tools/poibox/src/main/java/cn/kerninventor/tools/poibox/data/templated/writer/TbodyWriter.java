package cn.kerninventor.tools.poibox.data.templated.writer;

import cn.kerninventor.tools.poibox.data.templated.initializer.EColumnInitiator;
import cn.kerninventor.tools.poibox.data.templated.initializer.ETabulationInitiator;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;

/**
 * @author Kern
 * @date 2020/4/10 14:10
 * @description
 */
@FunctionalInterface
public interface TbodyWriter {

    void templateTbody(ETabulationInitiator tabulation, EColumnInitiator column, CellStyle style, Sheet sheet, List data);

}
