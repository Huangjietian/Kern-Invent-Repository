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
     * TODO 借鉴easy-excel，采用多级表头的形式
     * @return
     */
    String[] value();
}
