package cn.kerninventor.tools.poibox.data.tabulation.translator;

/**
 * @author Kern
 * @date 2020/5/25 18:36
 * @description
 */
public interface ColumnDataTranslator<K, V> {

    V getValue(K key, String tag);

    K getKey(V value, String tag);

    default K getDefaultKey() {
        return null;
    }

    default V getDefaultValue() {
        return null;
    }

}
