package cn.kerninventory.tools.excel.anno.element;

/**
 * <p>
 *     Font style within the scope.
 * </p>
 *
 * @author Kern
 */
public @interface Font {

    /**
     * The published ID, with a default value of -1, if specified otherwise, will publish the font to the Bean global.
     * @return
     */
    int publishId() default -1;
}
