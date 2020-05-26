package cn.kerninventor.tools.poibox.data;

import cn.kerninventor.tools.poibox.data.tabulation.reader.TabulationReader;
import cn.kerninventor.tools.poibox.data.tabulation.writer.TabulationWriter;

/**
 * @author Kern
 * @date 2019/12/9 14:50
 */
public interface DataTabulator {

    /**
    * @author Kern
    * @date 2020/4/10
    */
    <T> TabulationWriter<T> writer(Class<T> sourceClass);

    /**
    * @author Kern
    * @date 2020/4/10
    */
    <T> TabulationReader<T> reader(Class<T> sourceClass);
}
