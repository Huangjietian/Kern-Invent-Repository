package cn.kerninventor.tools.poibox.data.templated;
import cn.kerninventor.tools.poibox.data.templated.element.Font;
import cn.kerninventor.tools.poibox.data.templated.element.Style;
import cn.kerninventor.tools.poibox.style.Fonter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Title: POIExcel
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.data.annotation
 * @Author Kern
 * @Date 2019/12/6 10:39
 * @Description: TODO
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelTabulation {

    ExcelBanner[] banners() default {};

    Style theadStyle() default @Style(
            font = @Font(
                    fontName = Fonter.DEF_NAME_HEADER,
                    fontSize = Fonter.DEF_SIZE_TABLEHEADER
            )
    );

    Style tbodyStyle() default @Style;

    int startRowIndex() default 0;

    int effectiveRows() default 20;

    boolean autoColumnIndex() default true;

}
