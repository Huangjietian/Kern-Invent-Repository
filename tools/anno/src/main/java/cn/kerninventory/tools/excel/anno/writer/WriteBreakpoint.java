package cn.kerninventory.tools.excel.anno.writer;

import cn.kerninventory.tools.excel.anno.constants.DocumentType;

/**
 * <p>一句话描述</p>
 *
 * @author Kern
 */
public interface WriteBreakpoint {

    <T> Writer<T> resume(Class<T> tClass, DocumentType documentType);
}
