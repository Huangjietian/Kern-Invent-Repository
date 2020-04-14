package cn.kerninventor.tools.poibox.data;

import cn.kerninventor.tools.poibox.data.templated.reader.Reader;
import cn.kerninventor.tools.poibox.data.templated.writer.Writer;

/**
 * @Author Kern
 * @Date 2019/12/9 14:50
 * @Description: TODO
 */
public interface DataTabulator {

    /**
    * @author Kern
    * @date 2020/4/10
    * @description
    * @中文描述
    */
    <T> Writer<T> writer(Class<T> sourceClass);

    /**
    * @author Kern
    * @date 2020/4/10
    * @description
    * @中文描述 数据读取器. 考虑新的工厂方法， 参数为Templator
    */
    <T> Reader<T> reader(Class<T> sourceClass);
}
