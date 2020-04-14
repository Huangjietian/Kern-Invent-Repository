package cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.array.dictionary.api;

/**
 * @author Kern
 * @date 2020/4/14 9:03
 * @description
 */
public interface CascadeDictionaryEntry<V, P> extends DictionaryEntry<V> {

    P getUpstream();

}
