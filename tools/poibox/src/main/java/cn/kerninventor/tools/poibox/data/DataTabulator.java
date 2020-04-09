package cn.kerninventor.tools.poibox.data;

import cn.kerninventor.tools.poibox.data.templatedtable.reader.Reader;
import cn.kerninventor.tools.poibox.data.templatedtable.templator.Templator;
import cn.kerninventor.tools.poibox.data.templatedtable.writer.Writer;

/**
 * @Author Kern
 * @Date 2019/12/9 14:50
 * @Description: TODO
 */
public interface DataTabulator {

    /**
     * 模板
     * @param sourceClass
     * @return
     */
    <T> Templator<T> templator(Class<T> sourceClass);

    /**
     * 写入器
     * @return
     */
    Writer writer();

    /**
     * 读取器
     * @return
     */
    Reader reader();
}
