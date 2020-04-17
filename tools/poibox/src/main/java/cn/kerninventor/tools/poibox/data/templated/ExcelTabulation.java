package cn.kerninventor.tools.poibox.data.templated;
import cn.kerninventor.tools.poibox.data.templated.element.Font;
import cn.kerninventor.tools.poibox.data.templated.element.Style;
import cn.kerninventor.tools.poibox.developer.ReadyToDevelop;
import cn.kerninventor.tools.poibox.style.Fonter;

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

    @Deprecated
    Style theadStyle() default @Style(
            font = @Font(
                    fontName = Fonter.DEF_NAME_HEADER,
                    fontSize = Fonter.DEF_SIZE_TABLEHEADER
            )
    );

    @Deprecated
    Style tbodyStyle() default @Style;

    @ReadyToDevelop
    Style[] theadStyles() default {};

    @ReadyToDevelop
    Style[] tbodyStyles() default {};

    float theadRowHeight() default DefaultRowHeight;

    float tbodyRowHeight() default DefaultRowHeight;

    int startRowIndex() default 0;

    int effectiveRows() default 20;

    int minimumColumnsWidth() default 0;

    /**
    * @author Kern
    * @date 2020/4/17
    * @description 
    * @中文描述 
    */
    @ReadyToDevelop
    int maximumColumnsWidth() default MaximumColumnWidthAllowed;

    @Deprecated
    boolean autoColumnIndex() default true;

}
