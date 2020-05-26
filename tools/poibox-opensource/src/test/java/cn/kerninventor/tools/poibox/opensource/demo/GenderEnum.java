package cn.kerninventor.tools.poibox.opensource.demo;

import cn.kerninventor.tools.poibox.opensource.data.tabulation.validation.array.EnumExplicitList;

/**
 * @author Kern
 * @date 2020/5/26 15:59
 * @description
 */
public enum  GenderEnum implements EnumExplicitList {
    MEN("1","男"),
    WOMEN("2", "女"),
    ;

    private String code;

    private String desc;

    GenderEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String explicitList() {
        return desc;
    }
}
