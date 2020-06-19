package cn.kerninventor.tools.poibox.demo;

import cn.kerninventor.tools.poibox.data.tabulation.annotations.ExcelBanner;
import cn.kerninventor.tools.poibox.data.tabulation.annotations.ExcelColumn;
import cn.kerninventor.tools.poibox.data.tabulation.annotations.ExcelTabulation;
import cn.kerninventor.tools.poibox.data.tabulation.annotations.Font;
import cn.kerninventor.tools.poibox.data.tabulation.annotations.Range;
import cn.kerninventor.tools.poibox.data.tabulation.annotations.Style;
import cn.kerninventor.tools.poibox.data.tabulation.validation.CompareType;
import cn.kerninventor.tools.poibox.data.tabulation.validation.array.ExplicitListDataValid;
import cn.kerninventor.tools.poibox.data.tabulation.validation.date.DateDataValid;
import cn.kerninventor.tools.poibox.data.tabulation.validation.decimal.DecimalDataValid;
import cn.kerninventor.tools.poibox.data.tabulation.validation.integer.IntDataValid;
import cn.kerninventor.tools.poibox.data.tabulation.validation.textlength.TextLengthDataValid;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.FillPatternType;

import java.math.BigDecimal;
import java.util.Date;


/**
 * @author Kern
 * @date 2020/4/14 9:52
 * @description
 */
@ExcelTabulation(
        banners = {
                @ExcelBanner(value = "第一个标题", range = @Range(fistRow = 0, lastRow = 1)),
                @ExcelBanner(value = "第二个标题", range = @Range(fistRow = 2, lastRow = 5))
        },
        theadStyles = {
                @Style(
                        index = 0,
                        font = @Font(color = HSSFColor.HSSFColorPredefined.WHITE),
                        fillPatternType = FillPatternType.SOLID_FOREGROUND,
                        foregroudColor = HSSFColor.HSSFColorPredefined.ROYAL_BLUE,
                        wrapText = true
                ),
                @Style(
                        index = 1,
                        font = @Font(color = HSSFColor.HSSFColorPredefined.WHITE),
                        fillPatternType = FillPatternType.SOLID_FOREGROUND,
                        foregroudColor = HSSFColor.HSSFColorPredefined.RED,
                        wrapText = true
                )
        },
        tbodyStyles =
                @Style(
                        index = 0,
                        font = @Font(fontName = "仿宋"),
                        fillPatternType = FillPatternType.SOLID_FOREGROUND,
                        foregroudColor = HSSFColor.HSSFColorPredefined.LIGHT_YELLOW
                        ),
        theadRowHeight = 30.0f,
        tbodyRowHeight = 18.0f,
        minimumColumnsWidth = 10
)
public class AthleteRosterEO {

    @IntDataValid
    @ExcelColumn(value = "序号", columnWidth = 5)
    private Integer serial;

    @ExplicitListDataValid(list = {"单位1", "单位2", "单位3"})
    @ExcelColumn(value = "共建单位", dataFormatEx = "0.00")
    private Long cooperativeUnit;

    @ExcelColumn(value = "1管理模式", columnWidth = 16, theadStyleIndex = 1)
    private String managementModeName;

    @TextLengthDataValid(value = 18, compareType = CompareType.ET)
    @ExcelColumn(value = "身份证号", dataFormatEx = "@", columnWidth = 20, theadStyleIndex = 1)
    private String identityCard;

    @TextLengthDataValid(value = 50)
    @ExcelColumn(value = "姓名", theadStyleIndex = 1)
    private String name;

    @ExcelColumn(value = "性别", theadStyleIndex = 1)
    private String genderName;

    @DateDataValid(date = DateDataValid.NOW, compareType = CompareType.LT)
    @ExcelColumn(value = "出生日期", dataFormatEx = "yyyy-MM-dd", theadStyleIndex = 1)
    private Date birthDate;

    @IntDataValid
    @ExcelColumn(value = "身高（CM）", dataFormatEx = "0", theadStyleIndex = 1)
    private Integer height;
//
    @IntDataValid
    @ExcelColumn(value = "体重（KG）", dataFormatEx = "0", theadStyleIndex = 1)
    private Integer weight;
//
    @TextLengthDataValid(value = 100)
    @ExcelColumn(value = "籍贯", theadStyleIndex = 1)
    private String nativePlace;

    @ExcelColumn(value = "学历", theadStyleIndex = 1)
    private String educationalLevelName;

    @ExcelColumn(value = "2学位", theadStyleIndex = 1)
    private String degreeName;

    @ExcelColumn(value = "政治面貌", theadStyleIndex = 1)
    private String politicsStatusName;

    @ExcelColumn(value = "运动员技术等级", theadStyleIndex = 1)
    private String sportLevelName;

    @DateDataValid(compareType = CompareType.LTE, date = DateDataValid.NOW)
    @ExcelColumn(value = "入队时间", dataFormatEx = "yyyy-MM-dd", theadStyleIndex = 1)
    private Date selectedDate;

    @TextLengthDataValid(value = 20)
    @ExcelColumn(value = "队伍（组）", theadStyleIndex = 1)
    private String teamName;

    @ExcelColumn(value = "大项", theadStyleIndex = 1)
    private String sportName;

    @ExcelColumn(value = "分项", columnWidth = 25, theadStyleIndex = 1)
    private String subentryName;

    @TextLengthDataValid(value = 20)
    @ExcelColumn(value = "主项", theadStyleIndex = 1)
    private String majorSport;

    @ExcelColumn(value = "3训练层次", theadStyleIndex = 1)
    private String trainingLevelName;

    @ExcelColumn(value = "注册单位所在省市区(含解放军)", theadStyleIndex = 1)
    private String registeredUnitAreaName;

    @TextLengthDataValid(value = 50)
    @ExcelColumn(value = "注册单位", theadStyleIndex = 1)
    private String registeredUnit;

    @TextLengthDataValid(value = 50)
    @ExcelColumn("解放军运动员输送单位")
    private String plaAthleteSourceUnit;

    @TextLengthDataValid(value = 50)
    @ExcelColumn("双重注册单位")
    private String doalSourceUnit;

    @ExcelColumn(value = "是否属冬季项目跨界跨项跨季运动员",columnWidth = 17, theadStyleIndex = 1)
    private String isCbsAthlete;

    @TextLengthDataValid(value = 50)
    @ExcelColumn("国家队津贴标准")
    private String subsidyStandard;

    @DecimalDataValid(value = 0, optionalVal = 31, compareType = CompareType.BET)
    @ExcelColumn(value = "上月训练天数", dataFormatEx = "0.0")
    private BigDecimal lastMonthTraniningDays;

    @DecimalDataValid(value = -10000.0D, compareType = CompareType.GTE)
    @ExcelColumn(value = "上月实发国家队津贴", dataFormatEx = "0.00")
    private BigDecimal lastMonthSubsidy;

    @TextLengthDataValid(value = 500)
    @ExcelColumn(value = "本年度入选时间", dataFormatEx = "@")
    private String selectedDateInterval;

    @TextLengthDataValid(value = 20)
    @ExcelColumn(value = "主管教练员", theadStyleIndex = 1)
    private String chiefCoach;

    @ExcelColumn(value = "4现状", theadStyleIndex = 1)
    private String currentSituationName;

    @TextLengthDataValid(value = 50)
    @ExcelColumn("在训队员训练地")
    private String trainingPlace;

    @TextLengthDataValid(value = 20)
    @ExcelColumn("运动年限")
    private String trainingPeriod;

    @ExcelColumn("其他1")
    private String other1;

    @ExcelColumn("其他2")
    private String other2;

    @ExcelColumn("其他3")
    private String other3;

    @ExcelColumn("其他4")
    private String other4;

    @ExcelColumn("突出经历或成绩")
    private String experience;

    @TextLengthDataValid(value = 20)
    @ExcelColumn(value = "填报人", theadStyleIndex = 1)
    private String informant;

    @TextLengthDataValid(value = 11, compareType = CompareType.ET)
    @ExcelColumn(value = "填报人手机号", dataFormatEx = "@", theadStyleIndex = 1)
    private String informantPhone;

    @ExcelColumn(value = "备注", columnWidth = 40)
    private String remark;

    @TextLengthDataValid(value = 50)
    @ExcelColumn("最新驻地")
    private String latestLocation;

    @TextLengthDataValid(value = 50)
    @ExcelColumn("最新状态")
    private String latestStatus;

    private String iconUri;


}

