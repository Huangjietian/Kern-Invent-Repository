package cn.kerninventor.tools.poibox.data.tabulation.annotations;

import cn.kerninventor.tools.poibox.style.enums.BorderDirection;
import org.apache.poi.ss.usermodel.BorderStyle;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Kern
 * @date 2020/4/9 12:32
 * @description
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Border {

    BorderStyle borderStyle() default BorderStyle.NONE;

    BorderDirection direction() default BorderDirection.SURROUND;

}
