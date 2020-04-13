package cn.kerninventor.tools.poibox.data.templatedtable;
import cn.kerninventor.tools.poibox.data.templatedtable.element.Style;
import cn.kerninventor.tools.poibox.data.templatedtable.element.Font;
import cn.kerninventor.tools.poibox.developer.ReadyToDevelop;
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

    @ReadyToDevelop("大标题，允许有多个大标题，对结果不负责")
    ExcelBanner[] banners() default {};

    @ReadyToDevelop("表头风格")
    Style theadStyle() default @Style(
            font = @Font(
                    fontName = Fonter.DEF_NAME_HEADER,
                    fontSize = Fonter.DEF_SIZE_TABLEHEADER
            )
    );

    @ReadyToDevelop("表体风格")
    Style tbodyStyle() default @Style;

    int startRowIndex() default 0;

    int effectiveRows() default 20;

    boolean autoColumnIndex() default true;

}
