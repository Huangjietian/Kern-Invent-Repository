package cn.kerninventory.tools.excel.anno.element;

/**
 * <p>
 *     Table title, subtitles, description, etc.
 * </p>
 *
 * @author Kern
 */
public @interface Caption {

    String value();

    int subscribeStyle() default -1;

    int subscribeFont() default -1;
}
