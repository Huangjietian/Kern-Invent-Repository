package cn.kerninventor.tools.poibox.data.templated.validation.array;

import cn.kerninventor.tools.poibox.data.templated.validation.array.dictionary.Dictionary;

/**
 * @author Kern
 * @date 2020/4/14 16:14
 * @description
 */
public @interface DictionaryConf {

    Class<? extends Dictionary> dictionary();

    String[] flags() default {};

}
