package cn.kerninventor.tools.poibox.data.datatable.dictionary.view;

import cn.kerninventor.tools.poibox.data.datatable.dictionary.ExcelDictionaryLibrary;

import java.util.List;

/**
 * @Title: ViewDictionaryCover
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.data.datatable.dict
 * @Author Kern
 * @Date 2019/12/13 16:01
 * @Description: TODO
 */
public abstract class ViewDictionaryCover<T extends ViewBody> implements ViewDictionary {

    public ViewDictionaryCover() {
        ExcelDictionaryLibrary.attach(this);
    }

    /**
     *
     * @return
     */
    public abstract List<T> obtainData();
}
