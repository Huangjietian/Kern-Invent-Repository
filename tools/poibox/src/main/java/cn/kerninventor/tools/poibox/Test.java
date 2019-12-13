package cn.kerninventor.tools.poibox;

import cn.kerninventor.tools.poibox.data.datatable.ExcelColumn;
import cn.kerninventor.tools.poibox.data.datatable.ExcelTabulation;
import cn.kerninventor.tools.poibox.data.datatable.validation.array.ExcelValid_ARRAY;

/**
 * @Title: Test
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox
 * @Author Kern
 * @Date 2019/12/13 18:05
 * @Description: TODO
 */
@ExcelTabulation(headline = "测试页", style = TestStyle.class)
public class Test {

    @ExcelColumn("姓名")
    private String name;

    @ExcelColumn("身份证")
    private String iddentity;

    @ExcelValid_ARRAY(dictionary = TestGender.class)
    @ExcelColumn("性别")
    private int sex;
}


