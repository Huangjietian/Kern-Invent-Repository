package cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.array.dictionary;

import cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.array.dictionary.api.Dictionary;
import cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.array.dictionary.api.DictionaryProvider;
import cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.array.dictionary.api.DictionaryEntry;
import cn.kerninventor.tools.poibox.utils.ReflectUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Kern
 * @date 2019/12/10 18:58
 */
public final class DictionaryLibrary {

    private static ConcurrentHashMap<Class<? extends Dictionary>, Dictionary> dictionaryMap = new ConcurrentHashMap<>();

    public static void attach(Dictionary dictionary){
        dictionaryMap.put(dictionary.getClass(), dictionary);
    }

    public static List<? extends DictionaryEntry> lookup(Class<? extends Dictionary> dictionaryClass) {
        if (dictionaryClass.isEnum()){
            return getEnumClassConstants(dictionaryClass);
        }
        return getDatasFromProvider(dictionaryClass);
    }

    private static List<? extends DictionaryEntry> getEnumClassConstants(Class<? extends Dictionary> enumClass){
        if (!DictionaryEntry.class.isAssignableFrom(enumClass)){
            throw new DictionaryConfigException("Illegal dictionary!",enumClass);
        }
        DictionaryEntry[] enums = (DictionaryEntry[]) enumClass.getEnumConstants();
        if (enums == null || enums.length == 0 ){
            throw new DictionaryConfigException("An enumerated class that ACTS as a dictionary does not have any elements.", enumClass);
        }
        return new ArrayList(Arrays.asList(enums));
    }

    private static List<? extends DictionaryEntry> getDatasFromProvider(Class<? extends Dictionary> providerClass){
        if (!DictionaryProvider.class.isAssignableFrom(providerClass)){
            throw new DictionaryConfigException("Illegal dictionary" , providerClass);
        }
        DictionaryProvider provider = (DictionaryProvider) dictionaryMap.get(providerClass);
        /**
         * if dictionary is null ， POIBOX will try to get a new instance， then put in map!
         */
        if (provider == null){
            try {
                provider = (DictionaryProvider) ReflectUtil.newInstance(providerClass);
                dictionaryMap.put(providerClass, provider);
            } catch (Exception e) {
                throw new DictionaryConfigException("Dictionary instantiation failed. Check for an unparameterized constructor! ", providerClass);
            }
        }
        return provider.obtainData();
    }

}
