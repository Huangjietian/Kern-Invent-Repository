package cn.kerninventor.tools.poibox.opensource.data.templated.validation.array.dictionary.api;

import cn.kerninventor.tools.poibox.opensource.data.templated.validation.array.dictionary.Dictionary;

import java.util.List;

/**
 * @Title: ViewDictionaryCover
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.data.datatable.dict
 * @Author Kern
 * @Date 2019/12/13 16:01
 * @Description: TODO
 */
public interface DictionaryProvider extends Dictionary {

    List<? extends DictionaryEntry> obtainData();

}
