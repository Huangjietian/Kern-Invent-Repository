package cn.kerninventor.tools.poibox.demo;

import cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.array.dictionary.api.DictionaryProvider;
import cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.array.dictionary.api.DictionaryEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Kern
 * @Date 2019/12/18 10:39
 */
public class TestCountryDictionary implements DictionaryProvider {

    @Override
    public List<? extends DictionaryEntry> obtainData() {
        List<TestCountryBO> countries = new ArrayList<>();
        countries.add(new TestCountryBO(1L, "中国"));
        countries.add(new TestCountryBO(2L, "美国"));
        countries.add(new TestCountryBO(3L, "俄罗斯"));
        countries.add(new TestCountryBO(4L,"英国"));
        countries.add(new TestCountryBO(5L,"德国"));
        countries.add(new TestCountryBO(6L,"奥地利"));
        countries.add(new TestCountryBO(7L,"瑞士"));
        return countries;
    }
}
