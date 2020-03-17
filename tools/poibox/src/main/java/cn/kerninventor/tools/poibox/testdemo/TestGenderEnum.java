package cn.kerninventor.tools.poibox.testdemo;

import cn.kerninventor.tools.poibox.data.datatable.datavalidation.array.dictionary.metaView.MetaViewEnum;

import java.io.Serializable;

/**
 * @Title: SexGender
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox
 * @Author Kern
 * @Date 2019/12/13 18:42
 * @Description: TODO
 */
public enum TestGenderEnum implements MetaViewEnum<Integer, String>, Serializable {
    MAN(1,"男"),
    WOMEN(2,"女"),
    ;

    TestGenderEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private int code;

    private String msg;


    @Override
    public Integer getMetadata() {
        return code;
    }

    @Override
    public String getViewdata() {
        return msg;
    }
}
