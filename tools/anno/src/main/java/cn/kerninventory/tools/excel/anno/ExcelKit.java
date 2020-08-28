package cn.kerninventory.tools.excel.anno;

import cn.kerninventory.tools.excel.anno.enums.ExcelType;
import cn.kerninventory.tools.excel.anno.reader.Reader;
import cn.kerninventory.tools.excel.anno.writer.Writer;

/**
 * <p>一句话描述</p>
 *
 * @author Kern
 */
public interface ExcelKit {

    <T> Writer<T> callWriter(Class<T> tClass, ExcelType excelType);

    <T> Reader<T> callReader(Class<T> tClass, SteamSource source);
}
