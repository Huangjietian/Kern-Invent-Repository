package cn.kerninventor.tools.poibox.opensource.data.tabulation.context;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.List;

/**
 * @author Kern
 * @date 2020/4/13 11:58
 * @description
 */
public interface TabContextModifier {

    TabContextModifier addBanner(String value, CellStyle cellStyle, int row1, int row2);

    TabContextModifier addBanner(String value, CellStyle cellStyle, int row1, int row2, int col1, int col2);

    TabContextModifier addBanner(String value, CellStyle cellStyle, CellRangeAddress cellRangeAddress);

    List<? extends BannerDefinitionModifier> getBanners();

    BannerDefinitionModifier getBannerAt(int index);

    List<? extends ColumnDefinitionModifier> getColumns();

    ColumnDefinitionModifier getColumnByTitileName(String titleName);

    ColumnDefinitionModifier getColumnByFieldName(String fieldName);

    TabContextModifier alterStartRowIndex(int startRowIndex);

    TabContextModifier alterTheadRowHeight(float theadRowHeight);

    TabContextModifier alterTbodyRowHeight(float tbodyRowHeight);

    TabContextModifier alterEffectiveRows(int effctiveRows);

    TabContextModifier alterMaxmunColumnsWidth(int maxmunColumnsWidth);

    TabContextModifier alterMinmunColumnsWidth(int minmunColumnsWidth);



}
