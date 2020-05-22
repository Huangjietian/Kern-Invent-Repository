package cn.kerninventor.tools.poibox.opensource.data.tabulation.statistics;

/**
 * @author Kern
 * @date 2020/5/21 17:48
 * @description
 */
public @interface NumberStatistics {

    ColumnStatDefinition[] columnStatDefinitions();

    DisplayRowStrategy displayRow() default DisplayRowStrategy.BOTTOM;

    int columnIndex() default 0;

    String flagContent() default "statistics";

    String uninvolvedContent() default "-";
}
