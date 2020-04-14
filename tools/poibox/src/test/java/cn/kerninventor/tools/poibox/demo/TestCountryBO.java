package cn.kerninventor.tools.poibox.demo;

import cn.kerninventor.tools.poibox.data.templated.validation.array.dictionary.api.ReferDictionaryEntry;

/**
 * @Title: TestCountry
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox
 * @Author Kern
 * @Date 2019/12/18 10:39
 * @Description: TODO
 */
public class TestCountryBO implements ReferDictionaryEntry<String, Long> {

    private Long id;

    private String name;

    public TestCountryBO(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    @Override
    public Long getMetadata() {
        return id;
    }

    @Override
    public String getViewdata() {
        return name;
    }
}
