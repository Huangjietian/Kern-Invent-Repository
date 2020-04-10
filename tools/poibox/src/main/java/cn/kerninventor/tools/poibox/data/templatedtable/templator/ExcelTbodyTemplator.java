package cn.kerninventor.tools.poibox.data.templatedtable.templator;

import cn.kerninventor.tools.poibox.data.templatedtable.initializer.ExcelColumnInitializer;
import cn.kerninventor.tools.poibox.data.templatedtable.initializer.ExcelTabulationInitializer;
import javafx.scene.input.DataFormat;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * @author Kern
 * @date 2020/4/10 14:10
 * @description
 */
public interface ExcelTbodyTemplator {

    void drawTbody(ExcelTabulationInitializer tabulation, ExcelColumnInitializer column, DataFormat dataFormat, Sheet sheet, Object... others);
}
