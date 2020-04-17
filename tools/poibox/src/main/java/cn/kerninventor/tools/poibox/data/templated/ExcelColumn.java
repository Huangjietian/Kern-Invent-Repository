package cn.kerninventor.tools.poibox.data.templated;

import cn.kerninventor.tools.poibox.data.templated.element.Style;

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

    boolean mergeByContent() default false;

    Style columnStyle() default @Style;

    boolean styleEffictive() default false;

}
