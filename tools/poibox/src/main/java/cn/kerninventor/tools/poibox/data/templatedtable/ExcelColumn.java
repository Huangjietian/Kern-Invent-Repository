package cn.kerninventor.tools.poibox.data.templatedtable;

import cn.kerninventor.tools.poibox.data.templatedtable.element.CellStyle;
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

    String value();

    int columnIndex() default -1;

    int columnWidth() default -1;

    String dataFormatEx() default "";

    boolean mergeByContent() default false;

    @ReadyToDevelop("列特定风格")
    CellStyle columnStyle() default @CellStyle;
    @ReadyToDevelop("如果要使用上述风格，需要开启effictive")
    boolean styleEffictive() default false;

}
