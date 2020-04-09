package cn.kerninventor.tools.poibox.testdemo;

import cn.kerninventor.tools.poibox.data.templatedtable.ExcelColumn;
import cn.kerninventor.tools.poibox.data.templatedtable.ExcelBanner;
import cn.kerninventor.tools.poibox.data.templatedtable.ExcelTabulation;
import cn.kerninventor.tools.poibox.data.templatedtable.cellstyle.CellBorder;
import cn.kerninventor.tools.poibox.data.templatedtable.cellstyle.CellStyle;
import cn.kerninventor.tools.poibox.data.templatedtable.cellstyle.Font;
import cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.CompareType;
import cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.array.ArrayDataValid;
import cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.date.DateDataValid;
import cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.decimal.DecimalDataValid;
import cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.integer.IntDataValid;
import cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.textLength.TextLengthDataValid;
import cn.kerninventor.tools.poibox.style.enums.BorderDirection;
import org.apache.poi.ss.usermodel.BorderStyle;

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
@ExcelTabulation(
        headline = "人员信息",
        style = TestStyle.class,
        //大标题
        banners = @ExcelBanner(
                value = "人员信息",
                style = @CellStyle(
                        font = @Font(fontName = "微软雅黑", fontSize = 26, bold = true),
                        wrapText = true,
                        border = @CellBorder(borderStyle = BorderStyle.DOUBLE, direction = BorderDirection.SURROUND)
                )
        ),
        //表头风格
        tHeadStyle = @CellStyle(font = @Font(fontName = "黑体", fontSize = 20, bold = true)),
        //表体风格
        tBodyStyle = @CellStyle(font = @Font(fontName = "宋体", fontSize = 16), wrapText = true)
)
public class TestBO {

    @TextLengthDataValid(value = 30, compareType = CompareType.LTE)
    @ExcelColumn(value = "姓名", dataFormatEx = "@")
    private String name;

    @TextLengthDataValid(compareType = CompareType.ET, value = 18)
    @ExcelColumn(value = "身份证", dataFormatEx = "@")
    private String iddentity;

    @ArrayDataValid(dictionary = TestGenderEnum.class, body = TestGenderEnum.class)
    @ExcelColumn("性别")
    private Integer sex;

    @DateDataValid(compareType = CompareType.BET, parseFormat = "dd/MM/yyyy", date = "01/01/2020", optionalDate = "31/12/2020",
            prompMessage = "请输入2020-01-01 至 2020-12-31 之间的日期",errorMessage = "输入错误，请检查格式是否正确")
    @ExcelColumn(value = "出生日期", dataFormatEx = "yyyy-MM-dd")
    private Date birthDay;

    @DateDataValid(compareType = CompareType.BET, parseFormat = "dd/MM/yyyy", date = "01/01/2020", optionalDate = "31/12/2020",
            prompMessage = "请输入2020-01-01 至 2020-12-31 之间的日期",errorMessage = "输入错误，请检查格式是否正确")
    @ExcelColumn(value = "加入日期", dataFormatEx = "yyyy-MM-dd HH:mm:ss", columnWidth = 26)
    private LocalDateTime joinDate;

    @IntDataValid(value = 0, optionalVal = 300, compareType = CompareType.BET)
    @ExcelColumn(value = "身高")
    private Integer height;

    @IntDataValid(value = 0, compareType = CompareType.GT)
    @ExcelColumn("体重")
    private Integer weight;

    @DecimalDataValid(value = 11.12)
    @ExcelColumn(value = "臂长")
    private BigDecimal brachium;

    @ArrayDataValid(dictionary = TestCountryDictionary.class, body = TestCountryBO.class,
            prompMessage = "请选择国籍", errorMessage = "请按照下拉框选择国籍")
    @ExcelColumn(value = "国籍", mergeByContent = true)
    private Long nationalityId;

    @ExcelColumn("有效性")
    private Boolean availability;

    public TestBO(String name, String iddentity, Integer sex, Date birthDay, LocalDateTime joinDate, Integer height, Integer weight, BigDecimal brachium, Long nationalityId, Boolean availability) {
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

    public TestBO() {
    }
}


