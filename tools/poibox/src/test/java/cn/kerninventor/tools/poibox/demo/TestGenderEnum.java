package cn.kerninventor.tools.poibox.demo;

import cn.kerninventor.tools.poibox.data.templated.validation.array.dictionary.api.ReferDictionaryEntry;

/**
 * @Title: SexGender
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox
 * @Author Kern
 * @Date 2019/12/13 18:42
 * @Description: TODO
 */
public enum TestGenderEnum implements ReferDictionaryEntry<String, Integer> {
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
