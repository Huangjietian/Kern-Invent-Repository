package cn.kerninventor.tools.poibox.data.templatedtable.initializer.configuration;

import cn.kerninventor.tools.poibox.developer.ReadyToDevelop;

/**
 * @author Kern
 * @date 2020/4/13 11:58
 * @description
 */
@ReadyToDevelop("预制的配置项接口，通过返回该对象供用户动态的修改已解析的内容")
public interface ExcelTabulationConfiguration {

    void addBanners(ExcelBannerDefinition... banners);

    void addColumns(ExcelColumnDefinition... columns);

    ExcelColumnDefinition getColumn(String fieldName);

    ExcelBannerDefinition getBanner(String bannerContent);
}
