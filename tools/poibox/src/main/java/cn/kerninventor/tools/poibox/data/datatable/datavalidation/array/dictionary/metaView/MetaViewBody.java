package cn.kerninventor.tools.poibox.data.datatable.datavalidation.array.dictionary.metaView;

import cn.kerninventor.tools.poibox.data.datatable.datavalidation.array.dictionary.view.ViewBody;

/**
 * @Title: WordEntries
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.data.datatable.translation
 * @Author Kern
 * @Date 2019/12/10 18:30
 * @Description: Word Entries
 */
public interface MetaViewBody <M ,V> extends ViewBody<V> {
    /**
     *
     * @return
     */
    M getMetadata();

    default Class<M> getMType(){
        return (Class<M>) (getBodyType()).getActualTypeArguments()[0];
    }

    @Override
    default Class<V> getVType(){
        return (Class<V>) (getBodyType()).getActualTypeArguments()[1];
    }
}
