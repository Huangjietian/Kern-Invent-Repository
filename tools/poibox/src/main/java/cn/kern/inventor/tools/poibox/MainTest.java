package cn.kern.inventor.tools.poibox;

import cn.kern.inventor.tools.poibox.enums.FonterElements;
import cn.kern.inventor.tools.poibox.enums.StylerElements;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;

/**
 * @Title: MainTest
 * @ProjectName tools
 * @Version 1.1.0.RELEASE
 * @Description: TODO
 * @Author Kern
 * @Date 2019/10/29 20:04
 */
public class MainTest{

    public static void main(String[] args) {
        POIBox poiBox = POIBoxOpener.open();

        CellStyle style = poiBox.styler().reset()
                .setBorder(StylerElements.CellDirection.SURROUND, StylerElements.BorderLine.BORDER_THIN)
                .setWholeCenter()
                .setFillBackgroundColor(new HSSFColor.BLUE())
                .setFillPattern(StylerElements.FillPattern.SOLID_FOREGROUND)
                .setFont(poiBox.fonter().reset().setFontName("黑体").setBold(true).setFontSize(20).setUnderline(FonterElements.UnderLine.DOUBLE).get())
                .get();
        poiBox.styler().putInStyle("title style", style);
        style = poiBox.styler().putOutStyle("title style");

        poiBox.styler().putInStyle("表头风格", poiBox.styler().reset()
                .setBorder(StylerElements.CellDirection.SURROUND, StylerElements.BorderLine.BORDER_THIN)
                .setWholeCenter()
                .setFillBackgroundColor(new HSSFColor.BLUE())
                .setFillPattern(StylerElements.FillPattern.SOLID_FOREGROUND)
                .setFont(poiBox.fonter().reset().setFontName("黑体").setBold(true).setFontSize(20).setUnderline(FonterElements.UnderLine.DOUBLE).get())
                .get());
        poiBox.styler().putInStyle("正文风格", poiBox.styler().reset()
                .setBorder(StylerElements.CellDirection.SURROUND, StylerElements.BorderLine.BORDER_THIN)
                .setWrapText(true)
                .setWholeCenter()
                .setFillPattern(StylerElements.FillPattern.SOLID_FOREGROUND)
                .setFont(poiBox.fonter().reset().setFontName("宋体").setBold(true).setFontSize(12).setUnderline(FonterElements.UnderLine.DOUBLE).get())
                .get());
        poiBox.styler().putInStyle("表头风格", poiBox.styler().commonHeadLine(null));
        poiBox.styler().putInStyle("正文风格", poiBox.styler().commonTextPart(null));

        poiBox.layouter()
                .mergedOneColumn(poiBox.working().createSheet(), 1, 1, 10)//合并该sheet页的一个区域
                .setContent("大标题")//对该区域赋值
                .setMergeRangeStyle(poiBox.styler().commonHeadLine(30));//对该区域设置风格

        System.out.println(POIGadget.TransferExcelColumnIndex(17));
    }
}


