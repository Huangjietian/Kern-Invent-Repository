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
@ExcelTabulation(headline = "人员信息", style = TestStyle.class)
public class Test {

    @ExcelColumn("姓名")
    private String name;

    @ExcelValid_TEXTLENGTH(compareType = CompareType.ET, value = 18, optionalVal = 18)
    @ExcelColumn(value = "身份证", dataFormatEx = "@")
    private String iddentity;

    @ExcelValid_ARRAY(dictionary = TestGender.class)
    @ExcelColumn("性别")
    private Integer sex;

    @ExcelValid_DATE(compareType = CompareType.BET, parseFormat = "dd/MM/yyyy", date = "01/01/2020", optionalDate = "31/12/2020",
    prompMessage = "请输入2020-01-01 至 2020-12-31 之间的日期",errorMessage = "输入错误，请检查格式是否正确")
    @ExcelColumn(value = "出生日期", dataFormatEx = "yyyy-mm-dd")
    private Date birthDay;

    @ExcelColumn("身高")
    private Integer height;

    @ExcelColumn("体重")
    private Integer weight;

    @ExcelValid_ARRAY(dictionary = TestCountryService.class, prompMessage = "请选择国籍", errorMessage = "请按照下拉框选择国籍")
    @ExcelColumn("国籍")
    private Long nationalityId;

    public Test(String name, String iddentity, Integer sex, Date birthDay, Integer height, Integer weight, Long nationalityId) {
        this.name = name;
        this.iddentity = iddentity;
        this.sex = sex;
        this.birthDay = birthDay;
        this.height = height;
        this.weight = weight;
        this.nationalityId = nationalityId;
    }
}


