package cn.kerninventory.tools.excel.fluexcel.elements;

import cn.kerninventory.tools.excel.fluexcel.constants.Usage;
import cn.kerninventory.tools.excel.fluexcel.parser.ElementParser;

import java.lang.annotation.*;

/**
 * <p>一句话描述</p>
 *
 * @author Kern
 */
@Documented
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelElements {

    Class<? extends ElementParser> parser();

    Usage usage();
}
