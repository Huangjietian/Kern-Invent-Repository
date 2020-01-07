package cn.kerninventor.tools.poibox;

import cn.kerninventor.tools.poibox.data.datatable.dictionary.metaView.MetaViewDictionaryCover;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title: TestService
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox
 * @Author Kern
 * @Date 2019/12/18 10:39
 * @Description: TODO
 */
public class TestCountryService extends MetaViewDictionaryCover<TestCountryBO> {
    @Override
    public List<TestCountryBO> obtainData() {
        //
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
