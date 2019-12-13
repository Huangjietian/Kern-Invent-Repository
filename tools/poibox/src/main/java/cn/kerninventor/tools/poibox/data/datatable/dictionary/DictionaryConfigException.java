package cn.kerninventor.tools.poibox.data.datatable.dictionary;

/**
 * @Title: DictionaryConfigException
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.data.datatable.translation
 * @Author Kern
 * @Date 2019/12/11 14:57
 * @Description: TODO
 */
public class DictionaryConfigException extends RuntimeException {

    public DictionaryConfigException() {
    }

    public DictionaryConfigException(String message) {
        super(message);
    }

    public DictionaryConfigException(String message, Class<? extends ExcelDictionary> dictionaryClass) {
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
