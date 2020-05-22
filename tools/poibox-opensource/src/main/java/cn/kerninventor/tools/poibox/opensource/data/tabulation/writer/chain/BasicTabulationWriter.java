package cn.kerninventor.tools.poibox.opensource.data.tabulation.writer.chain;

import cn.kerninventor.tools.poibox.opensource.data.tabulation.context.ColumnDefinition;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.context.TableContext;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.writer.tbody.TbodyWriter;
import org.apache.poi.sl.usermodel.Sheet;

import java.util.List;

/**
 * @author Kern
 * @date 2020/5/22 18:12
 * @description
 */
public class BasicTabulationWriter implements WriteChain<Sheet> {

    private TableContext tableContext;
    private List<ColumnDefinition> columnDefinitionsTemporary;
    private TbodyWriter tbodyWriter;

    @Override
    public void writeTo(Sheet target) {

    }
}
