package cn.kerninventory.tools.data.bean.convert;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Kern
 * @date 2020/5/19 15:52
 * @description
 */
public class BidMapperDiplomatist<BM extends BidMapper<K, V>, K, V> {

    private BidiMap<K, V> bidimap;

    public BidMapperDiplomatist(Collection<BM> bmCollection) {
        Map map = bmCollection.stream().collect(Collectors.toMap(BidMapper::uniqueKey, BidMapper::uniqueValue));
        bidimap = new DualHashBidiMap(map);
    }

    public V getValue(K key) {
        return bidimap.get(key);
    }

    public K getKey(V value) {
        return bidimap.getKey(value);
    }

    public void replace(K key, V value) {
        bidimap.replace(key, value);
    }

    public BidiMap<K, V> getBidimap() {
        return bidimap;
    }
}
