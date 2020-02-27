package cn.kerninventor.tools.poibox.testdemo;

import cn.kerninventor.tools.poibox.data.datatable.dictionary.metaView.MetaViewEnum;

/**
 * @Title: SexGender
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox
 * @Author Kern
 * @Date 2019/12/13 18:42
 * @Description: TODO
 */
public enum TestGender implements MetaViewEnum {
    MAN(1,"男"),
    WOMEN(2,"女"),
    ;

    TestGender(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private int code;

    private String msg;


    @Override
    public Object getMetadata() {
        return code;
    }

    @Override
    public Object getViewdata() {
        return msg;
    }
}
