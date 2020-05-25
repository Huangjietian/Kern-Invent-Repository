package cn.kerninventor.tools.poibox.opensource.data.tabulation.translator;

/**
 * @author Kern
 * @date 2020/5/25 18:36
 * @description
 */
public interface ColumnDataTranslator<K, V> {

    V getValue(String key);

    K getKey(String value);
}
