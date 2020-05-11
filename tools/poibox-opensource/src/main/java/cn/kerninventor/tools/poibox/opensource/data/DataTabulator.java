package cn.kerninventor.tools.poibox.opensource.data;

import cn.kerninventor.tools.poibox.opensource.data.templated.reader.Reader;
import cn.kerninventor.tools.poibox.opensource.data.templated.writer.Writer;

/**
 * @author Kern
 * @date 2019/12/9 14:50
 */
public interface DataTabulator {

    /**
    * @author Kern
    * @date 2020/4/10
    */
    <T> Writer<T> writer(Class<T> sourceClass);

    /**
    * @author Kern
    * @date 2020/4/10
    */
    <T> Reader<T> reader(Class<T> sourceClass);
}
