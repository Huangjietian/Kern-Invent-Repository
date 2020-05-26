package cn.kerninventor.tools.poibox.opensource.data.tabulation.writer.stat;

import cn.kerninventor.tools.poibox.opensource.data.tabulation.context.ColumnDefinition;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.context.TableContext;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;

/**
 * @author Kern
 * @date 2020/5/26 10:07
 * @description
 */
public class NumberStatisticsWriter {

    private TableContext tableContext;

    private List<ColumnDefinition> columnDefinitions;

    public NumberStatisticsWriter(TableContext tableContext, List<ColumnDefinition> columnDefinitions) {
        this.tableContext = tableContext;
        this.columnDefinitions = columnDefinitions;
    }



    public void test(Sheet sheet) {
        /**
         * 4 - 10 行下移一位， 复制行高， 不重置原始行高
         */
        sheet.shiftRows(4, 10, 1, true, false);
    }
}
