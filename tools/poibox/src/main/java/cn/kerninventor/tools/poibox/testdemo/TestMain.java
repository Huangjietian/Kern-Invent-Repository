package cn.kerninventor.tools.poibox.testdemo;

import cn.kerninventor.tools.poibox.POIBox;
import cn.kerninventor.tools.poibox.data.DataTabulator;
import cn.kerninventor.tools.poibox.data.datatable.templator.Templator;

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


        List<Test> tests = new ArrayList<>();
        tests.add(new Test("Kern","350521199301144444", 1 , new Date(), LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()),180, 70, BigDecimal.valueOf(195.11),1L, true));
        tests.add(new Test("Jack", "350521199301144445", 2 , new Date(), LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()),190, 75, BigDecimal.valueOf(210.33),2L, false));

        for (int i= 0 ; i <= 100 ; i ++ ){
            tests.add(new Test("Jack", "350521199301144445", 2 , new Date(), LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()),190, 75, BigDecimal.valueOf(210.33),2L, false));
        }

        POIBox poiBox = POIBox.open();
        DataTabulator dataTabulator = poiBox.dataTabulator();

        Templator templator = dataTabulator.templateTo("人员信息导入模板1", Test.class);

        dataTabulator.templateTo("人员信息导入模板2", Test.class);
        dataTabulator.writeDataTo("人员信息导入模板3", tests, null);

        poiBox.wirteToLocal("C:\\Users\\kern\\Desktop\\人员信息导入模板.xlxs");

        List<Test> tests1 = dataTabulator.readDatasFrom("人员信息导入模板3", Test.class);

        System.out.println("succeed!");
    }


}


