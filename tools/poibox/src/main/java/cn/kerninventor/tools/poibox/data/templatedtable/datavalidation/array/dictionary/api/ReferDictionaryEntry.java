package cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.array.dictionary.api;

/**
* @author Kern
* @date 2020/4/14
* @description
* @中文描述  可以翻译的的元数据和视图数据的对象， 字典服务提供的数据载体。
*/
public interface ReferDictionaryEntry<V, M> extends DictionaryEntry<V> {
    /**
     *
     * @return
     */
    M getMetadata();

}
