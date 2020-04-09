package cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.array.scan;

import cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.array.dictionary.api.DictionaryEntry;

import java.util.List;

/**
 * @author Kern
 * @date 2020/3/16 14:23
 */
@Deprecated
public interface Dictionary<T extends DictionaryEntry> {

    List<T> obtainDatas(Object... args);
}
