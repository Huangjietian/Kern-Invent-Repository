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
    * @author Kern
    * @date 2020/4/10
    * @description
    * @中文描述 模板生成器
    */
    <T> Templator<T> templator(Class<T> sourceClass);

    /**
    * @author Kern
    * @date 2020/4/10
    * @description
    * @中文描述 数据写入器. 考虑新的工厂方法， 参数为Templator
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
