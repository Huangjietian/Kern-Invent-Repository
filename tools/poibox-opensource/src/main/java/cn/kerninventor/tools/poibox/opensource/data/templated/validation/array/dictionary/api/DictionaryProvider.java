package cn.kerninventor.tools.poibox.opensource.data.templated.validation.array.dictionary.api;

import cn.kerninventor.tools.poibox.opensource.data.templated.validation.array.dictionary.Dictionary;

import java.util.List;

/**
 * @author Kern
 * @date 2019/12/13 16:01
 */
public interface DictionaryProvider extends Dictionary {

    List<? extends DictionaryEntry> obtainData();

}
