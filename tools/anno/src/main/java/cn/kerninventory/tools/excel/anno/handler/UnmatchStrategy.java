package cn.kerninventory.tools.excel.anno.handler;

/**
 * <h1>中文注释</h1>
 * <p>
 *     翻译器匹配失败处理策略
 * </p>
 * @author Kern
 * @version 1.0
 */
public enum UnmatchStrategy {

    IGNORE,

    EXCEPTION,

    CONSOLE,

    DEFAULT,

    ;
}
