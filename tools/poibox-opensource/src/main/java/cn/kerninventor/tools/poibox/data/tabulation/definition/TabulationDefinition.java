package cn.kerninventor.tools.poibox.data.tabulation.definition;

import cn.kerninventor.tools.poibox.Poibox;
import cn.kerninventor.tools.poibox.data.tabulation.annotations.Textbox;
import org.apache.poi.ss.usermodel.CellStyle;

import java.util.List;
import java.util.Map;

/**
 * <h1>中文注释<h1/>
 * <p>
 *     表格定义类。poibox会将用户使用一系列注解配置的bean最终解析成一个TabulationDefinition对象。
 * </p>
 * @author Kern
 */
public interface TabulationDefinition<T> {

    Poibox getParent();

    Class<T> getTabulationClass();

    float getTheadRowHeight();

    float getTbodyRowHeight();

    int getStartRowIndex();

    int getEffectiveRows();

    int getMaximumColumnsWidth();

    int getMinimumColumnsWidth();

    int getTheadRowIndex();

    int getTbodyFirstRowIndex();

    Map<Integer, CellStyle> getTheadStyleMap();

    Map<Integer, CellStyle> getTbodyStyleMap();

    List<BannerDefinition> getBannerDefinitions();

    List<ColumnDefinition> getColumnDefinitions();

    Textbox[] getTextnodes();

    TabulationDefinition setStartRowIndex(int startRowIndex);

    TabulationDefinition setTheadRowHeight(float theadRowHeight);

    TabulationDefinition setTbodyRowHeight(float tbodyRowHeight);

    TabulationDefinition setEffectiveRows(int effctiveRows);

    TabulationDefinition setMaximumColumnsWidth(int maxmunColumnsWidth);

    TabulationDefinition setMinimumColumnsWidth(int minmunColumnsWidth);



}
