package cn.kerninventor.tools.poibox.data.datatable.datavalidation.array.dictionary.view;

import cn.kerninventor.tools.poibox.data.datatable.datavalidation.array.dictionary.ExcelDictionaryLibrary;

import java.util.List;

/**
 * @Title: ViewDictionaryCover
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.data.datatable.dict
 * @Author Kern
 * @Date 2019/12/13 16:01
 * @Description: TODO
 */
public abstract class ViewDictionaryCover<T extends ViewBody> implements ViewDictionary<T> {

    public ViewDictionaryCover() {
        ExcelDictionaryLibrary.attach(this);
    }

    /**
     *
     * @return
     */
    public abstract List<T> obtainData();
}
