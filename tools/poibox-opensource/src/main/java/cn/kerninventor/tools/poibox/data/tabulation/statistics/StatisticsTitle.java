package cn.kerninventor.tools.poibox.data.tabulation.statistics;

/**
 * @author Kern
 * @date 2020/5/26 10:20
 * @description
 */
public @interface StatisticsTitle {

    String content();

    int columnIndex() default 0;
}
