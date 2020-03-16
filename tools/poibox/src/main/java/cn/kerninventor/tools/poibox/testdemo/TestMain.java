package cn.kerninventor.tools.poibox.testdemo;

import cn.kerninventor.tools.poibox.POIBox;
import cn.kerninventor.tools.poibox.data.DataTabulator;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.array.dictionary.view.ViewDictionary;
import cn.kerninventor.tools.poibox.data.datatable.Templator;
import cn.kerninventor.tools.poibox.elements.FonterElements;
import cn.kerninventor.tools.poibox.elements.StylerElements;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.reflections.Reflections;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * @Title: MainTest
 * @ProjectName tools
 * @Version 1.1.0.RELEASE
 * @Description: TODO
 * @Author Kern
 * @Date 2019/10/29 20:04
 */
public class TestMain {

    public static void main(String[] args) throws IOException {

        //模拟原始数据
        List<Test> tests = new ArrayList<>();
        tests.add(new Test("Kern","350521199301144444", 1 , new Date(), LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()),180, 70, BigDecimal.valueOf(195.11),1L, true));
        tests.add(new Test("Jack", "350521199301144445", 2 , new Date(), LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()),190, 75, BigDecimal.valueOf(210.33),2L, false));
        for (int i= 0 ; i <= 100 ; i ++ ){
            tests.add(new Test("Jack", "350521199301144445", 2 , new Date(), LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()),190, 75, BigDecimal.valueOf(210.33),2L, false));
        }
        System.out.println("数据总量: " + tests.size());

        //开启box
        POIBox poiBox = POIBox.open();
        DataTabulator dataTabulator = poiBox.dataTabulator();

        Templator templator = dataTabulator.templator(Test.class);
        CellStyle newHeadlineStyle = poiBox.styler().produce()
                .setBorder(StylerElements.CellDirection.SURROUND, BorderStyle.MEDIUM)
                .setFillPattern(FillPatternType.SOLID_FOREGROUND)
                .setFillBackgroundColor(HSSFColor.HSSFColorPredefined.BROWN)
                .setWrapText(true)
                .setFont(poiBox.fonter().simpleFont("黑体", 22, FonterElements.FontColor.RED))
                .setWholeCenter()
                .get();
        templator.getHeadline()
                .setContent("修改的标题： 人员信息模板")
                .setCellStyle(newHeadlineStyle);

        templator.tempalateTo("人员信息导入模板2");
        templator.writer().writeTo("人员信息导入模板3", tests);

        poiBox.writeToLocal("C:\\Users\\82576\\Desktop\\人员信息导入模板.xls");

        List<Test> readList = dataTabulator.reader().readFrom("人员信息导入模板3",templator);
        System.out.println("读取的数据总量： "+ readList.size());
        System.out.println("succeed!");



    }


}


