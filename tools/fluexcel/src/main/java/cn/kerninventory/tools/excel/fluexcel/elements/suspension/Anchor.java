package cn.kerninventory.tools.excel.fluexcel.elements.suspension;

import java.lang.annotation.*;

/**
 * <p>
 *     Anchor coordinates definition.
 * </p>
 * @author Kern
 */
@Documented
@Target(ElementType.TYPE_PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface Anchor {

    /**
     * 起始列 left
     * @return
     */
    int col1();

    /**
     * 起始行 top
     * @return
     */
    int row1();

    /**
     * 结束列 right
     * @return
     */
    int col2();

    /**
     * 结束行 bottom
     * @return
     */
    int row2();
}
