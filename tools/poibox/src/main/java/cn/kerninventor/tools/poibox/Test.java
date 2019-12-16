package cn.kerninventor.tools.poibox;

import cn.kerninventor.tools.poibox.data.datatable.ExcelColumn;
import cn.kerninventor.tools.poibox.data.datatable.ExcelTabulation;
import cn.kerninventor.tools.poibox.data.datatable.validation.CompareType;
import cn.kerninventor.tools.poibox.data.datatable.validation.array.ExcelValid_ARRAY;
import cn.kerninventor.tools.poibox.data.datatable.validation.date.ExcelValid_DATE;
import cn.kerninventor.tools.poibox.data.datatable.validation.integer.ExcelValid_INT;
import cn.kerninventor.tools.poibox.data.datatable.validation.textLength.ExcelValid_TEXTLENGTH;

import java.util.Date;

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

    @ExcelValid_TEXTLENGTH(compareType = CompareType.ET, value = 18, optionalVal = 18)
    @ExcelColumn("身份证")
    private String iddentity;

    @ExcelValid_ARRAY(dictionary = TestGender.class)
    @ExcelColumn("性别")
    private int sex;

    @ExcelValid_DATE(compareType = CompareType.LT, date = "now()")
    @ExcelColumn("出生日期")
    private Date birthDay;

    @ExcelValid_INT(0)
    @ExcelColumn("身高")
    private Integer height;

}

