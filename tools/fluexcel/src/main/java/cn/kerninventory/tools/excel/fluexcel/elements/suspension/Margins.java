package cn.kerninventory.tools.excel.fluexcel.elements.suspension;

import java.lang.annotation.*;

/**
 * <p>
 *     Text box margin definition.
 * </p>
 * @author Kern
 */
@Documented
@Target(ElementType.TYPE_PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface Margins {

    int left() default 0;

    int top() default 0;

    int right() default 0;

    int bottom() default 0;
}
