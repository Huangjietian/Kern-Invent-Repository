package cn.kerninventory.tools.excel.anno.elements;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 *     The title;Subtitles;instructions.
 * </p>
 * <p>
 *     如果标注在类上，则默认由第一行第一列开始计算；
 *     如果标注在字段上，则默认由该字段所表示的列开始计算。
 * </p>
 *
 * @author Kern
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface Caption {

    /**
     * 内容
     * @return
     */
    String value();

    /**
     * 占据的列数
     * @return
     */
    int length();

    /**
     * 内部偏移量
     * @return
     */
    int intraOffset() default 0;

    /**
     * 占据的行数
     * @return
     */
    int height() default 1;

    /**
     * 优先权
     * @return
     */
    int priority() default 0;

    /**
     * 表体前
     * @return
     */
    boolean isFront() default true;
}
