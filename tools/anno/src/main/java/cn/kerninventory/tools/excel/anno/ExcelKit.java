package cn.kerninventory.tools.excel.anno;

import cn.kerninventory.tools.excel.anno.reader.Reader;
import cn.kerninventory.tools.excel.anno.writer.Writer;

/**
 * <p>一句话描述</p>
 *
 * @author Kern
 */
public interface ExcelKit {

    <T> Writer callWriter(Class<T> excelBeanClass, SteamTarget steamTarget);

    <T> Reader callReader(Class<T> excelBeanClass, SteamSource steamSource);

    ExcelToolbox toolbox();
}
