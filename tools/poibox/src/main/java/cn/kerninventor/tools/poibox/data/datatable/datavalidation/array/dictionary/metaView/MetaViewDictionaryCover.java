package cn.kerninventor.tools.poibox.data.datatable.datavalidation.array.dictionary.metaView;

import cn.kerninventor.tools.poibox.data.datatable.datavalidation.array.dictionary.ExcelDictionaryLibrary;

import java.util.List;

/**
 * @Title: MetaViewDictionary
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.data.datatable.translation
 * @Author Kern
 * @Date 2019/12/10 18:33
 * @Description: TODO
 */
public abstract class MetaViewDictionaryCover<T extends MetaViewBody> implements MetaViewDictionary<T> {

    public MetaViewDictionaryCover() {
        ExcelDictionaryLibrary.attach(this);
    }

    /**
     *
     * @return
     */
    public abstract List<T> obtainData();
}

