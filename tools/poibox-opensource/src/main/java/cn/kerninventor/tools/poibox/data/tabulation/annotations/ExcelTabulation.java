package cn.kerninventor.tools.poibox.data.tabulation.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Kern
 * @date 2019/12/6 10:39
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelTabulation {

    int MaximumColumnWidthAllowed = 255;

    ExcelBanner[] banners() default {};

    Textbox[] textboxes() default {};

    Style[] theadStyles() default {@Style(index = 0)};

    Style[] tbodyStyles() default {@Style(index = 0)};

    float theadRowHeight() default 15.0f;

    float tbodyRowHeight() default 15.0f;

    int startRowIndex() default 0;

    int effectiveRows() default 20;

    int minimumColumnsWidth() default 0;

    int maximumColumnsWidth() default MaximumColumnWidthAllowed;


}
