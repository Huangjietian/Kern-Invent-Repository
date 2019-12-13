package cn.kerninventor.tools.poibox;

import cn.kerninventor.tools.poibox.data.CellValuer;
import cn.kerninventor.tools.poibox.elements.FonterElements;
import cn.kerninventor.tools.poibox.elements.StylerElements;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;

import java.io.IOException;

/**
 * @Title: MainTest
 * @ProjectName tools
 * @Version 1.1.0.RELEASE
 * @Description: TODO
 * @Author Kern
 * @Date 2019/10/29 20:04
 */
public class TestMain {

    public static void main(String[] args) throws IOException, InvalidFormatException {
        POIBox poiBox = POIBox.open();

        CellStyle style = poiBox.styler().reset()
                .setBorder(StylerElements.CellDirection.SURROUND, BorderStyle.THIN)
                .setWholeCenter()
                .setFillPattern(FillPatternType.SOLID_FOREGROUND)
                .setFont(poiBox.fonter().reset().setFontName("黑体").setBold(true).setFontSize(20).setUnderline(FonterElements.UnderLine.DOUBLE).get())
                .get();
        poiBox.styler().putInStyle("title style", style);
        style = poiBox.styler().putOutStyle("title style");

        poiBox.styler().putInStyle("表头风格", poiBox.styler().reset()
                .setBorder(StylerElements.CellDirection.SURROUND, BorderStyle.THIN)
                .setWholeCenter()
                .setFillPattern(FillPatternType.SOLID_FOREGROUND)
                .setFont(poiBox.fonter().reset().setFontName("黑体").setBold(true).setFontSize(20).setUnderline(FonterElements.UnderLine.DOUBLE).get())
                .get());
        poiBox.styler().putInStyle("正文风格", poiBox.styler().reset()
                .setBorder(StylerElements.CellDirection.SURROUND, BorderStyle.THIN)
                .setWrapText(true)
                .setWholeCenter()
                .setFillPattern(FillPatternType.SOLID_FOREGROUND)
                .setFont(poiBox.fonter().reset().setFontName("宋体").setBold(true).setFontSize(12).setUnderline(FonterElements.UnderLine.DOUBLE).get())
                .get());
        poiBox.styler().putInStyle("表头风格", poiBox.styler().usualHeadLine(null));
        poiBox.styler().putInStyle("正文风格", poiBox.styler().usualTextPart(null));

        poiBox.layouter()
                .mergedOneColumn(poiBox.working().createSheet(), 1, 1, 10)//合并该sheet页的一个区域
                .setMergeRangeContent("大标题")//对该区域赋值
                .setMergeRangeStyle(poiBox.styler().usualHeadLine(30));//对该区域设置风格

        System.out.println(CellValuer.TransferExcelColumnIndex(17));

        //getFirstCellNum 指向 index  getLastCellNum 指向 数量


        POIBox myBox = POIBox.open();
        myBox.dataBox("测试页").template(Test.class);
        myBox.wirteToLocal("C:\\Users\\kern\\Desktop\\测试工作表.xls");

    }
}


