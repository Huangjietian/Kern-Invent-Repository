package cn.kerninventor.tools.poibox.demo;

import cn.kerninventor.tools.poibox.data.templated.ExcelColumn;
import cn.kerninventor.tools.poibox.data.templated.ExcelTabulation;
import cn.kerninventor.tools.poibox.data.templated.element.Font;
import cn.kerninventor.tools.poibox.data.templated.element.Style;
import cn.kerninventor.tools.poibox.data.templated.validation.CompareType;
//import cn.kerninventor.tools.poibox.data.templated.validation.array.NameNameDataValid;
import cn.kerninventor.tools.poibox.data.templated.validation.date.DateDataValid;
import cn.kerninventor.tools.poibox.data.templated.validation.integer.IntDataValid;
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
        theadStyle = @Style(
                font = @Font(
                        fontSize = 14,
                        color = HSSFColor.HSSFColorPredefined.WHITE,
                        bold = true
                ),
                fillPatternType = FillPatternType.SOLID_FOREGROUND,
                foregroudColor = HSSFColor.HSSFColorPredefined.ROYAL_BLUE
        ),
        tbodyStyle = @Style(
                font = @Font(
                        fontSize = 12,
                        fontName = "仿宋"
                ),
                fillPatternType = FillPatternType.SOLID_FOREGROUND,
                foregroudColor = HSSFColor.HSSFColorPredefined.LIGHT_YELLOW
        ),
        theadRowHeight = 25.0f,
        tbodyRowHeight = 18.0f
)
public class AthleteRosterEO {

    @ExcelColumn("再加一个")
    private Integer sss;

    @ExcelColumn(value = "序号")
    private Integer serial;

    @ExcelColumn(value = "共建单位")
    private String cooperativeUnit;

//    @NameNameDataValid("MANAGEMENT_MODE")
    @ExcelColumn("1管理模式")
    private String managementMode;

    @ExcelColumn(value = "身份证号", dataFormatEx = "@")
    private String idCard;

    @ExcelColumn("姓名")
    private String name;

//    @NameNameDataValid("SEX")
    @ExcelColumn("性别")
    private String gender;

    @DateDataValid(date = DateDataValid.NOW, compareType = CompareType.LT)
    @ExcelColumn(value = "出生日期", dataFormatEx = "yyyy-MM-dd")
    private Date birthDate;

    @IntDataValid
    @ExcelColumn(value = "身高（CM）", dataFormatEx = "0")
    private Integer height;

    @IntDataValid
    @ExcelColumn(value = "体重（KG）", dataFormatEx = "0")
    private Integer weight;

    @ExcelColumn("籍贯")
    private String nativePlace;

//    @NameNameDataValid("EDUCATIONAL_LEVEL")
    @ExcelColumn("学历")
    private String educationalLevelName;

//    @NameNameDataValid("DEGREE")
    @ExcelColumn("2学位")
    private String degreeName;

//    @NameNameDataValid("POLITICS_STATUS")
    @ExcelColumn("政治面貌")
    private String politicsStatusName;

//    @NameNameDataValid("SPORT_LEVEL")
    @ExcelColumn("运动员技术等级")
    private String sportLevelName;

    @DateDataValid(compareType = CompareType.LTE, date = DateDataValid.NOW)
    @ExcelColumn(value = "入队时间", dataFormatEx = "yyyy-MM-dd")
    private Date selectedDate;

    @ExcelColumn("队伍（组）")
    private String teamName;

//    @NameNameDataValid("DISCIPLINE_TYPE")
    @ExcelColumn("大项")
    private String discipline;

//    @NameNameDataValid("PROJECT")
    @ExcelColumn("分项")
    private String sport;

    @ExcelColumn("主项")
    private String majorSport;

//    @NameNameDataValid("TRAINING_LEVEL")
    @ExcelColumn("3训练层次")
    private String trainingLevelName;

//    @NameNameDataValid("REGISTERED_UNIT_AREA")
    @ExcelColumn("注册单位所在省市区(含解放军)")
    private String registeredUnitAreaName;

    @ExcelColumn("注册单位")
    private String registeredUnit;

    @ExcelColumn("解放军运动员输送单位")
    private String plaAthleteSourceUnit;

    @ExcelColumn("双重注册单位")
    private String doalSourceUnit;

    @ExcelColumn("是否属冬季项目跨界跨项跨季运动员")
    private Boolean cbsAthleteTag;

    @ExcelColumn("国家队津贴标准")
    private String subsidyStandard;

    @ExcelColumn("上月训练天数")
    private BigDecimal lastMonthTraniningDays;

    @ExcelColumn("上月实发国家队津贴")
    private BigDecimal lastMonthSubsidy;

    @ExcelColumn(value = "本年度入选时间", dataFormatEx = "@")
    private String selectedDateInterval;

    @ExcelColumn("主管教练员")
    private String chiefCoach;

//    @NameNameDataValid("CURRENT_SITUATION")
    @ExcelColumn("4现状")
    private String currentSituationName;

    @ExcelColumn("在训队员训练地")
    private String trainingPlace;

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

    @ExcelColumn("填报人")
    private String informant;

    @ExcelColumn(value = "填报人手机号", dataFormatEx = "@")
    private String informantPhone;

    @ExcelColumn("备注")
    private String remark;

    @ExcelColumn("最新驻地")
    private String latestLocation;

    @ExcelColumn("最新状态")
    private String latestStatus;

}

