package cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.array.dictionary;

import cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.array.dictionary.api.Dictionary;

/**
 * @author Kern
 * @date 2019/12/11 14:57
 */
public class DictionaryConfigException extends RuntimeException {

    public DictionaryConfigException() {
    }

    public DictionaryConfigException(String message) {
        super(message);
    }

    public DictionaryConfigException(String message, Class<? extends Dictionary> dictionaryClass) {
        super(message + "  Class: " + dictionaryClass.getName());
    }

    public DictionaryConfigException(String message, Throwable cause) {
        super(message, cause);
    }

    public DictionaryConfigException(Throwable cause) {
        super(cause);
    }

    public DictionaryConfigException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}