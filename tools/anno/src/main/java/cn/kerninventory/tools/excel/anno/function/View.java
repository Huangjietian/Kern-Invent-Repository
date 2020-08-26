package cn.kerninventory.tools.excel.anno.function;

import cn.kerninventory.tools.excel.anno.handler.ExceptionStrategy;

/**
 * <p>
 *     注解到配置Bean的字段中，当写入到Excel表格中时，调用View注解中value指定的无参方法，获取复杂对象的视图字符表示
 * </p>
 *
 * @author Kern
 */
public @interface View {

    String value();

    ExceptionStrategy exceptionStrategy() default ExceptionStrategy.IGNORE;
}
