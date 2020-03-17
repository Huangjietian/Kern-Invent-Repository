package cn.kerninventor.tools.poibox.testdemo;

import cn.kerninventor.tools.poibox.POIBox;
import cn.kerninventor.tools.poibox.data.DataTabulator;
import cn.kerninventor.tools.poibox.data.datatable.initializer.ExcelTabulationInitializer;
import cn.kerninventor.tools.poibox.data.datatable.templator.Templator;
import cn.kerninventor.tools.poibox.data.utils.InstanceGetter;
import cn.kerninventor.tools.poibox.elements.FonterElements;
import cn.kerninventor.tools.poibox.elements.StylerElements;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;

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
        //开启box
        POIBox poiBox = POIBox.open();
        //数据处理器
        DataTabulator dataTabulator = poiBox.dataTabulator();

        //写入模板
        Templator templator = dataTabulator.templator(Test.class).tempalateTo("人员信息导入模板1");

        //新的大标题风格
        CellStyle newHeadlineStyle = poiBox.styler().produce()
                .setBorder(StylerElements.CellDirection.SURROUND, BorderStyle.MEDIUM)
                .setFillPattern(FillPatternType.SOLID_FOREGROUND)
                .setFillBackgroundColor(HSSFColor.HSSFColorPredefined.BROWN)
                .setWrapText(true)
                .setFont(poiBox.fonter().simpleFont("黑体", 22, FonterElements.FontColor.RED))
                .setWholeCenter()
                .get();
        //修改大标题的内容和风格
        templator.getHeadline()
                .setContent("修改的标题： 人员信息模板")
                .setStyle(newHeadlineStyle);

        //模拟原始数据
        List<Test> tests = new ArrayList<>();
        for (int i= 0 ; i <= 10 ; i ++ ){
            tests.add(new Test("Kern","350521199301144444", 1 , new Date(), LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()),180, 70, BigDecimal.valueOf(195.11),1L, true));
        }
        for (int i= 0 ; i <= 4 ; i ++ ){
            tests.add(new Test("Jack", "350521199301144445", 2 , new Date(), LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()),190, 75, BigDecimal.valueOf(210.33),2L, true));
        }
        for (int i= 0 ; i <= 7 ; i ++ ){
            tests.add(new Test("Tom", "350521199301144445", 2 , new Date(), LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()),178, 93, BigDecimal.valueOf(203.11),3L, true));
        }
        System.out.println("数据总量: " + tests.size());
        //写入数据到新的页面（两种方式）
        templator.writer().writeTo("人员信息导入模板2", tests);
        dataTabulator.writer().setDefaultMergedMode(false).writeTo("人员信息导入模板3", tests, templator);

        //写入到本地文件,采取覆盖文件的形式
        poiBox.writeToLocal("C:\\Users\\82576\\Desktop\\人员信息导入模板.xls");

        //读取流中的数据
        List<Test> readList = dataTabulator.reader().readFrom("人员信息导入模板3",templator);
        System.out.println("读取的数据总量： "+ readList.size());

        //打开本地文件的数据读取形式
        try {
            POIBox newBox = POIBox.open("C:\\Users\\82576\\Desktop\\人员信息导入模板.xls");
            List<Test> readList2 = newBox.dataTabulator().templator(Test.class).reader().readFrom("人员信息导入模板3");
            System.out.println("读取的数据总量： "+ readList2.size());
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }

        System.out.println("succeed!");



    }


}


