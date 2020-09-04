package cn.kerninventory.tools.excel.fluexcel.parser;

import java.lang.annotation.Annotation;

/**
 * <p>一句话描述</p>
 *
 * @author Kern
 */
@FunctionalInterface
public interface ElementParser<T extends Annotation, R> {

    R parse(T t);

}
