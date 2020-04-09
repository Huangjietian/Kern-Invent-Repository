package cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.array.dictionary.api;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

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

    /**
     * FIXME 如果被重写，将得不到正确的结果，这个方法不应该放在这里
     * @return
     */
    default ParameterizedType getEntryType(){
        Type[] types = getClass().getGenericInterfaces();
        Type bodyType = null;
        for (Type type : types) {
            if (type instanceof ParameterizedType) {
                bodyType = type;
                break;
            }
        }
        if (bodyType == null ){
            throw new IllegalArgumentException("Dictionary entry must specified the generic!");
        }
        return (ParameterizedType)bodyType;
    }

    /**
     *
     * @return
     */
    default Class<V> getVType(){
        return (Class<V>)(getEntryType()).getActualTypeArguments()[0];
    }
}
