package cn.kerninventor.tools.poibox.data.datatable.datavalidation.array.dictionary.view;

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
public interface ViewBody<V> {

    /**
     *
     * @return
     */
    V getViewdata();

    /**
     *
     * @return
     */
    default ParameterizedType getBodyType(){
        Type[] types = getClass().getGenericInterfaces();
        Type bodyType = null;
        for (Type type : types) {
            if (type instanceof ParameterizedType) {
                bodyType = type;
            }
        }
        if (bodyType == null ){
            throw new IllegalArgumentException("Dictionary Body must be specifies the generic!");
        }
        return (ParameterizedType)bodyType;
    }

    /**
     *
     * @return
     */
    default Class<V> getVType(){
        return (Class<V>)(getBodyType()).getActualTypeArguments()[0];
    }
}
