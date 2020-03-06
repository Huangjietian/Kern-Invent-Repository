package cn.kerninventor.tools.poibox.data.datatable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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
     * Regular validation at data upload.
     * TODO 改为 dataFormatEx
     * @return
     */
    String regEx() default "";

    /**
     * Conversion pattern for Date type fields.
     * TODO 删掉，统一由regEx处理
     * @return
     */
    @Deprecated
    String dateFormat() default "YYYY-MM-dd";


    /**
     * TODO 是否根据内容合并
     * @return
     */
    boolean mergeByContext() default false;
}
