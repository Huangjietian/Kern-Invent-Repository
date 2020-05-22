package cn.kerninventor.tools.poibox.opensource.data.tabulation.writer.tbody;

import cn.kerninventor.tools.poibox.opensource.data.tabulation.context.ColumnDefinition;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.context.TableContext;
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

    void templateTbody(TableContext tabulation, ColumnDefinition column, CellStyle style, Sheet sheet, List data);

}
