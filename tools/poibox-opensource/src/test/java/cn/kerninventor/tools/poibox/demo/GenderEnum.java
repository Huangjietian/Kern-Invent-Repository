package cn.kerninventor.tools.poibox.demo;

import cn.kerninventor.tools.poibox.data.tabulation.translator.ColumnDataTranslator;
import cn.kerninventor.tools.poibox.data.tabulation.validation.array.EnumExplicitList;

/**
 * @author Kern
 * @date 2020/5/26 15:59
 * @description
 */
public enum  GenderEnum implements EnumExplicitList, ColumnDataTranslator<String, String> {

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

    @Override
    public String getValue(String key, String tag) {
        GenderEnum[] genderEnums = GenderEnum.values();
        for (GenderEnum genderEnum : genderEnums) {
            if (genderEnum.code.equals(key)) {
                return genderEnum.desc;
            }
        }
        return null;
    }

    @Override
    public String getKey(String value, String tag) {
        GenderEnum[] genderEnums = GenderEnum.values();
        for (GenderEnum genderEnum : genderEnums) {
            if (genderEnum.desc.equals(value)) {
                return genderEnum.code;
            }
        }
        return null;
    }
}
