package cn.kerninventor.tools.spring.multithreadedtransaction.test;

/**
 * @Title ProvincePO
 * @ProjectName kerninventresp
 * @PackageName cn.kerninventor.tools.spring.multithreadedtransaction.test
 * @Author Kern
 * @Date 2020/1/20 15:14
 * @Description TODO
 */
public class TestProvincePO {

    private Long id;
    private String provinceCode;
    private String provinceName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    @Override
    public String toString() {
        return "TestProvincePO{" +
                "id=" + id +
                ", provinceCode='" + provinceCode + '\'' +
                ", provinceName='" + provinceName + '\'' +
                '}';
    }
}
