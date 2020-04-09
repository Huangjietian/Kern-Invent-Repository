package cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.array.dictionary.api;

/**
 * @Title: WordEntries
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.data.datatable.translation
 * @Author Kern
 * @Date 2019/12/10 18:30
 * @Description: Word Entries
 */
public interface DictionaryReferEntry<M ,V> extends DictionaryEntry<V> {
    /**
     *
     * @return
     */
    M getMetadata();

    default Class<M> getMType(){
        return (Class<M>) (getEntryType()).getActualTypeArguments()[0];
    }

    @Override
    default Class<V> getVType(){
        return (Class<V>) (getEntryType()).getActualTypeArguments()[1];
    }
}
