package cn.kerninventor.tools.poibox.data.datatable;

import cn.kerninventor.tools.poibox.data.datatable.datavalidation.ExcelValid;

import java.lang.annotation.*;

/**
 * @Title: POIColumn
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.data.annotation
 * @Author Kern
 * @Date 2019/12/6 10:41
 * @Description: TODO
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelColumn {

    /**
     * table title
     * @return
     */
    String value();

    /**
     * columns index
     * @return
     */
    int columnIndex() default 0;

    /**
     * By default, column widths are set based on content, and you can set a fixed width
     * @return
     */
    int columnWidth() default -1;

    /**
     * Excel data format expression.
     * @return
     */
    String dataFormatEx() default "";

    /**
     * If ture, poibox will merge by content In this column When download data.
     * @return
     */
    boolean mergeByContent() default false;

}
