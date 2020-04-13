package cn.kerninventor.tools.poibox.data.templatedtable.writer.tbodywriter;

import cn.kerninventor.tools.poibox.data.templatedtable.initializer.ExcelColumnInitializer;
import cn.kerninventor.tools.poibox.data.templatedtable.initializer.ExcelTabulationInitializer;
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
