package cn.kerninventor.tools.poibox.data.templatedtable;

import cn.kerninventor.tools.poibox.data.templatedtable.cellstyle.CellStyle;
import cn.kerninventor.tools.poibox.developer.ReadyToDevelop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Kern
 * @date 2019/12/6 10:41
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
    int columnIndex() default -1;

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

    @ReadyToDevelop
    CellStyle columnStyle() default @CellStyle(effective = false);

}
