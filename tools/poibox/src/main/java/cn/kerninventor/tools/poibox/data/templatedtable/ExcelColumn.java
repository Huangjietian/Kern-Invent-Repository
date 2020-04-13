package cn.kerninventor.tools.poibox.data.templatedtable;

import cn.kerninventor.tools.poibox.data.templatedtable.element.Style;
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

    int columnIndex() default -1;

    int columnWidth() default DEFAULT_COLUMN_WIDTH;

    String dataFormatEx() default "";

    boolean mergeByContent() default false;

    @ReadyToDevelop("列特定风格")
    Style columnStyle() default @Style;
    @ReadyToDevelop("如果要使用上述风格，需要开启effictive")
    boolean styleEffictive() default false;

}
