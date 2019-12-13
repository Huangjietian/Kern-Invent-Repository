package cn.kerninventor.tools.poibox.data.datatable.dictionary;

import cn.kerninventor.tools.poibox.data.datatable.dictionary.metaView.MetaViewBody;
import cn.kerninventor.tools.poibox.data.datatable.dictionary.metaView.MetaViewDictionary;
import cn.kerninventor.tools.poibox.data.datatable.dictionary.metaView.MetaViewDictionaryCover;
import cn.kerninventor.tools.poibox.data.datatable.dictionary.view.ViewBody;
import cn.kerninventor.tools.poibox.data.datatable.dictionary.view.ViewDictionary;
import cn.kerninventor.tools.poibox.data.datatable.dictionary.view.ViewDictionaryCover;

import java.util.*;

/**
 * @Title: DictionaryTransfer
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.data.datatable.translation
 * @Author Kern
 * @Date 2019/12/10 18:58
 */
public final class ExcelDictionaryLibrary<T extends MetaViewDictionaryCover> {

    private static Map<Class<? extends ExcelDictionary>, ExcelDictionary> dictionaryMap = new HashMap<>();

    public static void attach(ExcelDictionary dictionary){
        dictionaryMap.put(dictionary.getClass(), dictionary);
    }

    public static List<MetaViewBody> referMetaViewDict(Class<? extends MetaViewDictionary> dictionaryClass) {
        /**
         * load data into container.
         */
        //enum 如果是枚举类，直接获取全部枚举值
        if (dictionaryClass.isEnum()){
            MetaViewBody[] enums = (MetaViewBody[]) dictionaryClass.getEnumConstants();
            if (enums == null || enums.length == 0 ){
                throw new DictionaryConfigException("An enumerated class that ACTS as a dictionary does not have any elements.", dictionaryClass);
            }
            return new ArrayList(Arrays.asList(enums));
        }
        // 如果不是枚举且不是MetaViewDictionaryCover的子类，抛出异常
        if (!MetaViewDictionaryCover.class.isAssignableFrom(dictionaryClass)){
            throw new DictionaryConfigException("The dictionary must be an enumeration that implements the interface MetaViewEnum or a concrete implementation that inherits the abstarct class MetaViewDictionaryCover." , dictionaryClass);
        }
        // 获取字典类
        MetaViewDictionaryCover dictionary = (MetaViewDictionaryCover) dictionaryMap.get(dictionaryClass);
        /**
         * if dictionary is null ， POIBOX will try to get a new instance， then put in map!
         */
        if (dictionary == null){
            try {
                dictionary = (MetaViewDictionaryCover) dictionaryClass.newInstance();
                dictionaryMap.put(dictionaryClass, dictionary);
            } catch (Exception e) {
                throw new DictionaryConfigException("Dictionary instantiation failed. Check for an unparameterized constructor! ", dictionaryClass);
            }
        }
        return dictionary.obtainData();
    }

    public static List<ViewBody> referViewDict(Class<? extends ViewDictionary> dictionaryClass) {
        /**
         * load data into container.
         */
        //enum
        if (dictionaryClass.isEnum()){
            ViewBody[] enums = (ViewBody[]) dictionaryClass.getEnumConstants();
            if (enums == null || enums.length == 0 ){
                throw new DictionaryConfigException("An enumerated class that ACTS as a dictionary does not have any elements.", dictionaryClass);
            }
            return new ArrayList(Arrays.asList(enums));
        }
        if (!ViewDictionaryCover.class.isAssignableFrom(dictionaryClass)){
            throw new DictionaryConfigException("The dictionary must be an enumeration that implements the interface viewEnum or a concrete implementation that inherits the abstarct class ViewDictionaryCover." , dictionaryClass);
        }
        ViewDictionaryCover dictionary = (ViewDictionaryCover) dictionaryMap.get(dictionaryClass);
        /**
         * if dictionary is null ， POIBOX will try to get a new instance， then put in map!
         */
        if (dictionary == null){
            try {
                dictionary = (ViewDictionaryCover) dictionaryClass.newInstance();
                dictionaryMap.put(dictionaryClass, dictionary);
            } catch (Exception e) {
                throw new DictionaryConfigException("Dictionary instantiation failed. Check for an unparameterized constructor! ", dictionaryClass);
            }
        }
        return dictionary.obtainData();
    }
}
