package cn.kerninventor.tools.poibox.opensource.data.tabulation.element;

import org.apache.poi.ss.usermodel.VerticalAlignment;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Kern
 * @date 2020/5/21 10:34
 * @description
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Textbox {

    String value();

    Anchor anchorIndex();

    VerticalAlignment verticalAlignment() default VerticalAlignment.CENTER;

    Margins margins() default @Margins(left = 0, top = 0, right = 0, bottom = 0);

    Palette fillColor() default @Palette(red = 255, green = 255, bule = 255);

    Palette lineColor() default @Palette(red = 0, green = 0, bule = 0);
}
