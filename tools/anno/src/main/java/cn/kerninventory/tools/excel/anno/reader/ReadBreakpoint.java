package cn.kerninventory.tools.excel.anno.reader;

import cn.kerninventory.tools.excel.anno.DocumentSource;

/**
 * <p>一句话描述</p>
 *
 * @author Kern
 */
public interface ReadBreakpoint {

    <T> Reader<T> resume(Class<T> tClass, DocumentSource documentSource);
}
