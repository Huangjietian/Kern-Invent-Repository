package cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.array.dictionary.api;

import cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.array.dictionary.Dictionary;

/**
 * @Title: ViewBody
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.data.datatable.translation
 * @Author Kern
 * @Date 2019/12/13 15:46
 * @Description: TODO
 */
public interface DictionaryEntry<V> extends Dictionary {

    /**
     *
     * @return
     */
    V getViewdata();

}
