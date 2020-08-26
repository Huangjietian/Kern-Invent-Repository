package cn.kerninventory.tools.excel.anno.element;

/**
 * <p>
 *     The cell style in the scope.
 * </p>
 *
 * @author Kern
 */
public @interface Style {

    /**
     * The published ID, with a default value of -1, if specified otherwise, will publish the style to the Bean global.
     * @return
     */
    int publishId() default -1;
}
