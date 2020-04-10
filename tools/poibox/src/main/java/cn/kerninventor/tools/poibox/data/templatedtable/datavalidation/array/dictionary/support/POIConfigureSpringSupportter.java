package cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.array.dictionary.support;

import cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.array.dictionary.DictionaryLibrary;
import cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.array.dictionary.api.Dictionary;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

/**
 * @author Kern
 * @date 2020/4/9 17:20
 * @description
 */
public class POIConfigureSpringSupportter implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Dictionary> map = applicationContext.getBeansOfType(Dictionary.class);
        map.values().forEach(e -> {
            DictionaryLibrary.attach(e);
        });
    }
}
