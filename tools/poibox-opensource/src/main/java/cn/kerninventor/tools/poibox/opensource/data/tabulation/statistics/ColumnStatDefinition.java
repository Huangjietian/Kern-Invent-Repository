package cn.kerninventor.tools.poibox.opensource.data.tabulation.statistics;

/**
 * @author Kern
 * @date 2020/5/21 18:23
 * @description
 */
public @interface ColumnStatDefinition {

    String columnFieldName();

    NumberStatisticsMode statisticsMode();

}
