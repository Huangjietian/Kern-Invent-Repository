package cn.kerninventory.tools.data.bean.convert;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Kern
 * @date 2020/5/19 16:17
 * @description
 */
public class BidMapperForeignOffice {

    private Map<String, BidMapper> keyInKeyValueMap = new ConcurrentHashMap<>();

    private Map<String, BidMapperDiplomatist> diplomatistMap = new ConcurrentHashMap<>();

    public BidMapperForeignOffice addBidMappers(String tag, Collection<? extends BidMapper> bmCollection) {
        BidMapperDiplomatist bidMapperDiplomatist = new BidMapperDiplomatist(bmCollection);
        diplomatistMap.put(tag, bidMapperDiplomatist);
        return this;
    }

    public Object queryValue(String tag, Object key) {
        return diplomatistMap.get(tag).getValue(key);
    }

    public Object queryKey(String tag, Object value) {
        return diplomatistMap.get(tag).getKey(value);
    }
}
