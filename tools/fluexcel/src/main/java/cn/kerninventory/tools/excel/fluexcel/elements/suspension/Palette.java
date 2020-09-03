package cn.kerninventory.tools.excel.fluexcel.elements.suspension;

import java.lang.annotation.*;

/**
 * <p>
 *     Text box palette definition(three primary colors).
 * </p>
 * @author Kern
 */
@Documented
@Target(ElementType.TYPE_PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface Palette {

    int red();

    int green();

    int blue();
}
