package cn.kerninventor.tools.poibox.opensource.data.tabulation.statistics;

/**
 * @author Kern
 * @date 2020/5/21 18:23
 * @description
 */
public @interface ColumnStatDefinition {

    String columnFieldName();

    int AUTO_MODE_PRECISION = -1;

    NumberStatisticsMode statisticsMode();

    short precision() default AUTO_MODE_PRECISION;
}
