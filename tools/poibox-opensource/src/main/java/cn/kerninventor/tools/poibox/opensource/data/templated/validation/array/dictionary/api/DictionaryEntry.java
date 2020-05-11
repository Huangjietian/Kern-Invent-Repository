package cn.kerninventor.tools.poibox.opensource.data.templated.validation.array.dictionary.api;

import cn.kerninventor.tools.poibox.opensource.data.templated.validation.array.dictionary.Dictionary;

/**
 * @author Kern
 * @date 2019/12/13 15:46
 */
public interface DictionaryEntry<V> extends Dictionary {

    /**
     *
     * @return
     */
    V getViewdata();

}
