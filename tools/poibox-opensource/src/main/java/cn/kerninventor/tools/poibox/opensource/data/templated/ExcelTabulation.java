package cn.kerninventor.tools.poibox.opensource.data.templated;
import cn.kerninventor.tools.poibox.opensource.data.templated.element.Style;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Kern
 * @Date 2019/12/6 10:39
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelTabulation {

    float DefaultRowHeight = -1.0f;

    int MaximumColumnWidthAllowed = 255;

    ExcelBanner[] banners() default {};

    Style[] theadStyles() default {@Style(index = 0)};

    Style[] tbodyStyles() default {@Style(index = 0)};

    float theadRowHeight() default DefaultRowHeight;

    float tbodyRowHeight() default DefaultRowHeight;

    int startRowIndex() default 0;

    int effectiveRows() default 20;

    int minimumColumnsWidth() default 0;

    int maximumColumnsWidth() default MaximumColumnWidthAllowed;


}
