package cn.kerninventor.tools.poibox.demo;

import cn.kerninventor.tools.poibox.data.templatedtable.ExcelBanner;
import cn.kerninventor.tools.poibox.data.templatedtable.ExcelColumn;
import cn.kerninventor.tools.poibox.data.templatedtable.ExcelTabulation;
import cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.CompareType;
import cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.array.ArrayDataValid;
import cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.date.DateDataValid;
import cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.decimal.DecimalDataValid;
import cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.integer.IntDataValid;
import cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.textLength.TextLengthDataValid;
import cn.kerninventor.tools.poibox.data.templatedtable.element.Border;
import cn.kerninventor.tools.poibox.data.templatedtable.element.Font;
import cn.kerninventor.tools.poibox.data.templatedtable.element.Range;
import cn.kerninventor.tools.poibox.data.templatedtable.element.Style;
import cn.kerninventor.tools.poibox.style.enums.BorderDirection;
import cn.kerninventor.tools.poibox.style.enums.FontColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

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
        //大标题
        banners = {
                @ExcelBanner(
                        value = "人员信息",
                        style = @Style(font = @Font(fontName = "微软雅黑", fontSize = 24, bold = true), wrapText = true, border = @Border(borderStyle = BorderStyle.DOUBLE, direction = BorderDirection.SURROUND)),
                        range = @Range(fistRow = 0, lastRow = 0)
                ),
                @ExcelBanner(
                        value = "模板生成者： Kern",
                        style = @Style(font = @Font(color = FontColor.RED), alignment = HorizontalAlignment.LEFT),
                        range = @Range(fistRow = 1, lastRow = 1, firstCell = 0, lastCell = 2)
                ),
                @ExcelBanner(
                        value = "所属机构： 壹体体育",
                        style = @Style(font = @Font(color = FontColor.RED), alignment = HorizontalAlignment.LEFT),
                        range = @Range(fistRow = 1, lastRow = 1, firstCell = 3, lastCell = 5)
                ),
                @ExcelBanner(
                        value = "日期: 2020-04-13",
                        style = @Style(font = @Font(color = FontColor.RED), alignment = HorizontalAlignment.LEFT),
                        range = @Range(fistRow = 1, lastRow = 1, firstCell = 6, lastCell = 9)
                ),
        },
        //表头风格
        theadStyle = @Style(font = @Font(fontName = "黑体", fontSize = 16, bold = true)),
        //表体风格
        tbodyStyle = @Style(font = @Font(fontName = "宋体", fontSize = 12), wrapText = true),
        //有效行数
        effectiveRows = 10
)
public class TestBO {

    @TextLengthDataValid(value = 30, compareType = CompareType.LTE)
    @ExcelColumn(value = "姓名", dataFormatEx = "@")
    private String name;

    @TextLengthDataValid(compareType = CompareType.ET, value = 18)
    @ExcelColumn(value = "身份证", dataFormatEx = "@")
    private String iddentity;

    @ArrayDataValid(dictionary = TestGenderEnum.class)
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
    @ExcelColumn(value = "身高", styleEffictive = true, columnStyle = @Style(font = @Font(color = FontColor.RED)))
    private Integer height;

    @IntDataValid(value = 0, compareType = CompareType.GT)
    @ExcelColumn("体重")
    private Integer weight;

    @DecimalDataValid(value = 11.12)
    @ExcelColumn(value = "臂长")
    private BigDecimal brachium;

    @ArrayDataValid(dictionary = TestCountryDictionary.class,
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


