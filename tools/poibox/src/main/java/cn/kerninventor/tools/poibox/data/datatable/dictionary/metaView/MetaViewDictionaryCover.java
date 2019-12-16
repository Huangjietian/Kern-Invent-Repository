package cn.kerninventor.tools.poibox.data.datatable.dictionary.metaView;

import cn.kerninventor.tools.poibox.data.datatable.dictionary.ExcelDictionary;
import cn.kerninventor.tools.poibox.data.datatable.dictionary.ExcelDictionaryLibrary;

import java.util.List;

/**
 * @Title: MetaViewDictionary
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.data.datatable.translation
 * @Author Kern
 * @Date 2019/12/10 18:33
 * @Description: TODO
 */
public abstract class MetaViewDictionaryCover<T extends MetaViewBody> implements ExcelDictionary {

    public MetaViewDictionaryCover() {
        ExcelDictionaryLibrary.attach(this);
    }

    /**
     *
     * @return
     */
    public abstract List<T> obtainData();
}
