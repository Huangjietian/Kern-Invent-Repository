package cn.kerninventor.tools.poibox.testdemo;

import cn.kerninventor.tools.poibox.POIBox;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        POIBox myBox = POIBox.open();

        List<Test> tests = new ArrayList<>();
        tests.add(new Test("name1","123", 3 , new Date(), LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()),123456, 21324, BigDecimal.valueOf(11.13),1L, true));
        tests.add(new Test("name2", "123", 2 , new Date(), LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()),123456, 2135423, BigDecimal.ZERO,2L, false));
        myBox.dataTabulator().templateTo("人员信息导入模板1", Test.class);
        myBox.dataTabulator().templateTo("人员信息导入模板2", Test.class);
        Sheet sheet = myBox.workbook().createSheet("人员信息导入模板3");
        myBox.dataTabulator().writeTo(sheet, Test.class, tests);
        List<Test> tests1 = myBox.dataTabulator().readDatasFrom("人员信息导入模板3", Test.class);
        myBox.wirteToLocal("C:\\Users\\82576\\Desktop\\人员信息导入模板.xlxs");
    }
}


