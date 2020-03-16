package cn.kerninventor.tools.poibox.data.datatable.datavalidation.array.dictionary;

import cn.kerninventor.tools.poibox.data.datatable.datavalidation.array.dictionary.view.ViewDictionary;
import org.reflections.Reflections;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Title DictionaryInterfacesScanConfig
 * @ProjectName kerninventresp
 * @PackageName cn.kerninventor.tools.poibox.data.datatable.datavalidation.array.dictionary
 * @Author Kern
 * @Date 2020/3/16 14:16
 * @Description TODO
 */
@Deprecated
public abstract class DictionaryInterfacesScanConfig {

    public abstract String scanPackageName();

    public DictionaryInterfacesScanConfig() {
//        Reflections reflections = new Reflections(scanPackageName());
//        Set<Class<? extends Dictionary>> classes = reflections.getSubTypesOf(Dictionary.class);
//
//        classes.forEach(e -> {
//            e.isEnum()
//        });
    }
}
