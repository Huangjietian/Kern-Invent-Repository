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

    float DEF_ROW_HEIGHT = -1.0f;

    ExcelBanner[] banners() default {};

    Style theadStyle() default @Style(
            font = @Font(
                    fontName = Fonter.DEF_NAME_HEADER,
                    fontSize = Fonter.DEF_SIZE_TABLEHEADER
            )
    );

    Style tbodyStyle() default @Style;

    @ReadyToDevelop("表头高度, 已实现，未验证")
    float theadRowHeight() default DEF_ROW_HEIGHT;

    @ReadyToDevelop("表体， 已实现，未验证")
    float tbodyRowHeight() default DEF_ROW_HEIGHT;

    int startRowIndex() default 0;

    int effectiveRows() default 20;

    boolean autoColumnIndex() default true;

}
