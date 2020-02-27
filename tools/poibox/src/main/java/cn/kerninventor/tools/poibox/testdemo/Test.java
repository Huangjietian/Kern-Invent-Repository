package cn.kerninventor.tools.poibox.testdemo;

import cn.kerninventor.tools.poibox.data.datatable.ExcelColumn;
import cn.kerninventor.tools.poibox.data.datatable.ExcelTabulation;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.CompareType;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.array.ExcelValid_ARRAY;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.date.ExcelValid_DATE;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.textLength.ExcelValid_TEXTLENGTH;

import java.util.Date;

/**
 * @Title: Test
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox
 * @Author Kern
 * @Date 2019/12/13 18:05
 * @Description: TODO
 */
@ExcelTabulation(headline = "人员信息导入模板", style = TestStyle.class)
public class Test {

    @ExcelColumn("姓名")
    private String name;

    @ExcelValid_TEXTLENGTH(compareType = CompareType.ET, value = 18, optionalVal = 18)
    @ExcelColumn("身份证")
    private String iddentity;

    @ExcelValid_ARRAY(dictionary = TestGender.class)
    @ExcelColumn("性别")
    private Integer sex;

    @ExcelValid_DATE(compareType = CompareType.LT, date = "now()")
    @ExcelColumn("出生日期")
    private Date birthDay;

    @ExcelColumn("身高")
    private Integer height;

    @ExcelColumn("体重")
    private Integer weight;

    @ExcelValid_ARRAY(dictionary = TestCountryService.class, prompMessage = "请选择国籍", errorMessage = "请按照下拉框选择国籍")
    @ExcelColumn("国籍")
    private Long nationalityId;



}


