package cn.kerninventor.tools.poibox.opensource.data.templated.validation.array.dictionary.api;

/**
* @author Kern
* @date 2020/4/14
*/
public interface ReferDictionaryEntry<V, M> extends DictionaryEntry<V> {
    /**
     *
     * @return
     */
    M getMetadata();

}
