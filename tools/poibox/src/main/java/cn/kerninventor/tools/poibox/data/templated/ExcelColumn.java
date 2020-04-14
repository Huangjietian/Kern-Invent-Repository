package cn.kerninventor.tools.poibox.data.templated;

import cn.kerninventor.tools.poibox.data.templated.element.Style;
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

    int DEFAULT_COLUMN_WIDTH = -1;

    String value();

    /**
     * 公式
     * @return
     */
    @ReadyToDevelop
    String formula() default "";

    int columnIndex() default -1;

    int columnWidth() default DEFAULT_COLUMN_WIDTH;

    String dataFormatEx() default "";

    boolean mergeByContent() default false;

    Style columnStyle() default @Style;

    boolean styleEffictive() default false;

}
