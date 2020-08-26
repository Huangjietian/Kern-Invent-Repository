package cn.kerninventory.tools.excel.anno.element;

/**
 * <p>
 *     Table column definition.
 * </p>
 *
 * @author Kern
 */
public @interface Column {

    /**
     * <p>
     *     Content of the header.
     * </p>
     * <p>
     *     Using easy- Excel for reference, adopt the form of multi - stage header.
     * </p>
     * @return
     */
    String[] value();

    int subscribeStyle() default -1;

    int subscribeFont() default -1;


}
