package cn.kerninventor.tools.poibox.opensource.supportter;

import cn.kerninventor.tools.poibox.opensource.data.tabulation.validation.array.dictionary.DictionaryLibrary;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.validation.array.dictionary.Dictionary;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

/**
 * @author Kern
 * @date 2020/4/9 17:20
 * @description If you want to use @ArraysDataValid in the Spring project, inherit the class and add it to the Bean container.
 */
public class POIDictionaryForSpringSupportter implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Dictionary> map = applicationContext.getBeansOfType(Dictionary.class);
        map.values().forEach(e -> {
            DictionaryLibrary.attach(e);
        });
    }
}
