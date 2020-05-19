package cn.kerninventory.tools.data.bean.convert;

/**
 * @author Kern
 * @date 2020/5/19 15:50
 * @description
 */
public interface BidMapper<K, V> {

    K uniqueKey();

    V uniqueValue();

}
