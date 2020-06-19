package cn.kerninventor.tools.poibox.data.tabulation.context;

import cn.kerninventor.tools.poibox.data.tabulation.annotations.Textbox;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Kern
 */
public interface TabulationConfiguration<T> {

    static void setColumnsIndexBySort(List<ColumnDefinition> columnDefinitions) {
        Collections.sort(columnDefinitions);
        for (int i = 0; i < columnDefinitions.size() ; i ++) {
            columnDefinitions.get(i).setColumnIndex(i);
        }
    }

    Class<T> getTabulationClass();

    Map<Integer, CellStyle> getTheadStyleMap();

    Map<Integer, CellStyle> getTbodyStyleMap();

    float getTheadRowHeight();

    float getTbodyRowHeight();

    int getStartRowIndex();

    int getEffectiveRows();

    int getMaximumColumnsWidth();

    int getMinimumColumnsWidth();

    int getTheadRowIndex();

    int getTbodyFirstRowIndex();

    List<BannerDefinition> getBannerDefinitions();

    List<ColumnDefinition> getColumnDefinitions();

    Textbox[] getTextnodes();

    TabulationConfiguration addBanner(String value, CellStyle cellStyle, int row1, int row2);

    TabulationConfiguration addBanner(String value, CellStyle cellStyle, int row1, int row2, int col1, int col2);

    TabulationConfiguration addBanner(String value, CellStyle cellStyle, CellRangeAddress cellRangeAddress);

    TabulationConfiguration alterStartRowIndex(int startRowIndex);

    TabulationConfiguration alterTheadRowHeight(float theadRowHeight);

    TabulationConfiguration alterTbodyRowHeight(float tbodyRowHeight);

    TabulationConfiguration alterEffectiveRows(int effctiveRows);

    TabulationConfiguration alterMaximumColumnsWidth(int maxmunColumnsWidth);

    TabulationConfiguration alterMinimumColumnsWidth(int minmunColumnsWidth);



}
