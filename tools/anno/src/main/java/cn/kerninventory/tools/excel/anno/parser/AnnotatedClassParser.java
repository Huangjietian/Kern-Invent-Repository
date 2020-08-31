package cn.kerninventory.tools.excel.anno.parser;

import cn.kerninventory.tools.excel.anno.Tabulation;

import java.util.Objects;

/**
 * <p>一句话描述</p>
 *
 * @author Kern
 */
public class AnnotatedClassParser<T> {

    private AnnotatedClassParser() {
    }

    public static <T> AnnotatedClassParser<T> of(Class<T> tClass) {
        Tabulation tabulation = Objects.requireNonNull(
                Objects.requireNonNull(tClass,
                        "Invalid configuration bean, the configuration bean is null!").getDeclaredAnnotation(Tabulation.class),
                "Invalid configuration bean, The beans must be annotated with the " + Tabulation.class.getName()
        );

        /**
         * TODO do parse work!!!
         */




        return new AnnotatedClassParser<T>();
    }
}
