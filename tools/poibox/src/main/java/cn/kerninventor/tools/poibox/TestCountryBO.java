package cn.kerninventor.tools.poibox;

import cn.kerninventor.tools.poibox.data.datatable.dictionary.metaView.MetaViewBody;

/**
 * @Title: TestCountry
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox
 * @Author Kern
 * @Date 2019/12/18 10:39
 * @Description: TODO
 */
public class TestCountryBO implements MetaViewBody {

    private Long id;

    private String name;

    public TestCountryBO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public Object getMetadata() {
        return id;
    }

    @Override
    public Object getViewdata() {
        return name;
    }
}
