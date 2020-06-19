package cn.kerninventor.tools.poibox.data.tabulation.context;

import cn.kerninventor.tools.poibox.Poibox;

/**
 * <h1>中文注释</h1>
 * <p>
 *     表格配置类工厂，返回配置类，配置类转储并解析了用户在Bean上的配置
 * </p>
 *
 * @author Kern
 * @version 1.0
 */
public class TabulationConfigurationFactory {

    public static <T> TabulationConfiguration<T> parse(Class<T> tabulationClass, Poibox poibox) {
        TabulationConfiguration<T> configuration = new TabulationBeanConfiguration<T>(tabulationClass, poibox);

        return configuration;
    }

}