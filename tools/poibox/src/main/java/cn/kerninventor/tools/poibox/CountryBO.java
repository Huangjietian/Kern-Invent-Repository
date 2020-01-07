package cn.kerninventor.tools.poibox;

import cn.kerninventor.tools.poibox.data.datatable.dictionary.metaView.MetaViewBody;

import java.util.concurrent.CountDownLatch;

/**
 * @Title CountryBO
 * @ProjectName kerninventresp
 * @PackageName cn.kerninventor.tools.poibox
 * @Author Kern
 * @Date 2019/12/30 20:29
 * @Description TODO
 */
public class CountryBO implements MetaViewBody {

    private String countryName;

    private String countryCode;

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public static CountryBO getInstance(String code, String name){
        CountryBO countryBO = new CountryBO();
        countryBO.countryCode = code;
        countryBO.countryName = name;
        return countryBO;
    }

    @Override
    public Object getMetadata() {
        return countryCode;
    }

    @Override
    public Object getViewdata() {
        return countryName;
    }
}
