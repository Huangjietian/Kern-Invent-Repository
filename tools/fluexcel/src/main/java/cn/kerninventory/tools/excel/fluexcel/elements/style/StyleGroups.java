package cn.kerninventory.tools.excel.fluexcel.elements.style;

import java.lang.annotation.*;

/**
 * <p>
 *     Cell style groups definition.
 * </p>
 *
 * @author Kern
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface StyleGroups {

    StyleGroup[] value();
}
