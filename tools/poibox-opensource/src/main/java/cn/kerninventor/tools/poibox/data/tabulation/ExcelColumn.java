package cn.kerninventor.tools.poibox.data.tabulation;

import cn.kerninventor.tools.poibox.data.tabulation.translator.ColumnDataTranslate;
import cn.kerninventor.tools.poibox.data.tabulation.writer.tbody.col.ColWriter;
import cn.kerninventor.tools.poibox.data.tabulation.writer.tbody.col.GeneralColWriter;

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

    int DefaultColumnWidth = -1;

    String value();

    int columnSort() default -1;

    int columnWidth() default DefaultColumnWidth;

    String dataFormatEx() default "";

    String formula() default "";

    int theadStyleIndex() default 0;

    int tbodyStyleIndex() default 0;

    Class<? extends ColWriter> colWriter() default GeneralColWriter.class;

    ColumnDataTranslate dataTranslate() default @ColumnDataTranslate(translator = "", open = false);
}
