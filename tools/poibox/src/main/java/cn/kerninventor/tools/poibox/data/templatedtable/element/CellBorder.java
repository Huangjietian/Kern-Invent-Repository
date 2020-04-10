package cn.kerninventor.tools.poibox.data.templatedtable.element;

import cn.kerninventor.tools.poibox.style.enums.BorderDirection;
import org.apache.poi.ss.usermodel.BorderStyle;

/**
 * @author Kern
 * @date 2020/4/9 12:32
 * @description
 */
public @interface CellBorder {

    BorderStyle borderStyle() default BorderStyle.NONE;

    BorderDirection direction() default BorderDirection.SURROUND;

}
