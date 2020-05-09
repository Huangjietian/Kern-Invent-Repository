package cn.kerninventor.tools.poibox.opensource.demo;

import cn.kerninventor.tools.poibox.opensource.data.templated.ExcelColumn;
import cn.kerninventor.tools.poibox.opensource.data.templated.ExcelTabulation;

/**
 * @author Kern
 * @date 2020/5/7 12:48
 * @description
 */
@ExcelTabulation
public class Humen {

    @ExcelColumn("国家")
    private String country;

    @ExcelColumn("姓名")
    private String name;

    @ExcelColumn("性别")
    private String gender;

    public Humen(String country, String name, String gender) {
        this.country = country;
        this.name = name;
        this.gender = gender;
    }
}
