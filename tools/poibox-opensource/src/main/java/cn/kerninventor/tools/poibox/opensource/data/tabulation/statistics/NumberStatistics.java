package cn.kerninventor.tools.poibox.opensource.data.tabulation.statistics;

/**
 * @author Kern
 * @date 2020/5/21 17:48
 * @description
 */
public @interface NumberStatistics {

    /**
     * 什么列要参与该统计，注意，该列必须是Number或者能够被Number类的子类通过valueOf方法函数转化的参数。
     * @return
     */
    String[] fieldsName();

    /**
     * 该统计的逻辑
     * @return
     */
    NumberStatisticsMode mode();

    /**
     * 定义该统计所使用的行标题，需要指定 该行所在列的坐标以及文本内容。
     * 默认情况下， columnIndex为-1，将不适用标题
     * @return
     */
    StatisticsTitle title() default @StatisticsTitle(content = "", columnIndex = -1);

    /**
     * 不参与的列的文本内容
     * @return
     */
    String uninvolvedContent() default "-";

}
