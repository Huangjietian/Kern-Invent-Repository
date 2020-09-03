package cn.kerninventory.tools.excel.fluexcel.writer;

import cn.kerninventory.tools.excel.fluexcel.constants.DocumentType;

/**
 * <p>一句话描述</p>
 *
 * @author Kern
 */
public interface WriteBreakpoint {

    <T> Writer<T> resume(Class<T> tClass, DocumentType documentType);
}
