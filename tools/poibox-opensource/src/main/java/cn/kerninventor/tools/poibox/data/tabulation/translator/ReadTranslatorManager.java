package cn.kerninventor.tools.poibox.data.tabulation.translator;

/**
 * <h1>中文注释</h1>
 * <p>一句话描述</p>
 *
 * @author Kern
 * @version 1.0
 */
public class ReadTranslatorManager extends TranslatorManager {

    @Override
    public Object translate(ColumnDataTranslate translate, Object searchCondition) {
        if (translate.open()) {
            return getKey(translate.translator(), translate.tag(), translate.unmatchStrategy(), searchCondition);
        }
        return searchCondition;
    }

}
