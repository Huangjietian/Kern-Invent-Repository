package cn.kerninventor.tools.poibox.data.datatable.datavalidation.array.dictionary;

import cn.kerninventor.tools.poibox.data.datatable.datavalidation.array.dictionary.view.ViewBody;

import java.util.List;

/**
 * @Title Dictionary
 * @ProjectName kerninventresp
 * @PackageName cn.kerninventor.tools.poibox.data.datatable.datavalidation.array.dictionary
 * @Author Kern
 * @Date 2020/3/16 14:23
 * @Description TODO
 */
@Deprecated
public interface Dictionary<T extends ViewBody> {

    List<T> obtainDatas(Object... args);
}
