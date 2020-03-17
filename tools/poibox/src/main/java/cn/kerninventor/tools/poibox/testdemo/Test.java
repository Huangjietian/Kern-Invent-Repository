package cn.kerninventor.tools.poibox.testdemo;

import cn.kerninventor.tools.poibox.data.datatable.ExcelColumn;
import cn.kerninventor.tools.poibox.data.datatable.ExcelTabulation;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.CompareType;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.array.ExcelValidArray;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.date.ExcelValidDate;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.decimal.ExcelValidDecimal;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.integer.ExcelValidInt;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.textLength.ExcelValidTextlength;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    @ExcelValidTextlength(value = 30, compareType = CompareType.LTE)
    @ExcelColumn(value = "姓名", dataFormatEx = "@")
    private String name;

    @ExcelValidTextlength(compareType = CompareType.ET, value = 18)
    @ExcelColumn(value = "身份证", dataFormatEx = "@")
    private String iddentity;

    @ExcelValidArray(dictionary = TestGender.class, body = TestGender.class)
    @ExcelColumn("性别")
    private Integer sex;

    @ExcelValidDate(compareType = CompareType.BET, parseFormat = "dd/MM/yyyy", date = "01/01/2020", optionalDate = "31/12/2020",
    prompMessage = "请输入2020-01-01 至 2020-12-31 之间的日期",errorMessage = "输入错误，请检查格式是否正确")
    @ExcelColumn(value = "出生日期", dataFormatEx = "yyyy-MM-dd")
    private Date birthDay;

    @ExcelValidDate(compareType = CompareType.BET, parseFormat = "dd/MM/yyyy", date = "01/01/2020", optionalDate = "31/12/2020",
            prompMessage = "请输入2020-01-01 至 2020-12-31 之间的日期",errorMessage = "输入错误，请检查格式是否正确")
    @ExcelColumn(value = "加入日期", dataFormatEx = "yyyy-MM-dd HH:mm:ss", columnWidth = 26)
    private LocalDateTime joinDate;

    @ExcelValidInt(value = 0, optionalVal = 300, compareType = CompareType.BET)
    @ExcelColumn(value = "身高", dataFormatEx = "#0")
    private Integer height;

    @ExcelValidInt(value = 0, compareType = CompareType.GT)
    @ExcelColumn("体重")
    private Integer weight;

    @ExcelValidDecimal(value = 11.12)
    @ExcelColumn(value = "臂长")
    private BigDecimal brachium;

    @ExcelValidArray(dictionary = TestCountryService.class, body = TestCountryBO.class, prompMessage = "请选择国籍", errorMessage = "请按照下拉框选择国籍")
    @ExcelColumn(value = "国籍", mergeByContent = true)
    private Long nationalityId;

    @ExcelColumn("有效性")
    private Boolean availability;

    public Test(String name, String iddentity, Integer sex, Date birthDay, LocalDateTime joinDate, Integer height, Integer weight, BigDecimal brachium, Long nationalityId, Boolean availability) {
        this.name = name;
        this.iddentity = iddentity;
        this.sex = sex;
        this.birthDay = birthDay;
        this.joinDate = joinDate;
        this.height = height;
        this.weight = weight;
        this.brachium = brachium;
        this.nationalityId = nationalityId;
        this.availability = availability;
    }

    public Test() {
    }
}


