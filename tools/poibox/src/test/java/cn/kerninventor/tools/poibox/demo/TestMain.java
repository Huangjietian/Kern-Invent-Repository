package cn.kerninventor.tools.poibox.demo;

import cn.kerninventor.tools.poibox.POIBox;
import cn.kerninventor.tools.poibox.POIBoxFactory;
import cn.kerninventor.tools.poibox.data.DataTabulator;
import cn.kerninventor.tools.poibox.data.templated.writer.Writer;
import cn.kerninventor.tools.poibox.layout.AnchorIndex;
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
 * @author Kern
 * @date 2019/10/29 20:04
 */
public class TestMain {

    public static void main(String[] args) throws IOException {

        //开启box
        POIBox poiBox = POIBoxFactory.open();
        //数据处理器
        DataTabulator dataTabulator = poiBox.dataTabulator();
        //写入模板
        Writer writer = dataTabulator.writer(TestBO.class).writeTo("人员信息导入模板1");

        Sheet sheet = poiBox.getSheet("人员信息导入模板1");
        AnchorIndex anchorIndex = new AnchorIndex(4,4,4,4,1,23,8,28);
        poiBox.layouter().addTextBox(sheet, anchorIndex,"注意");


        //模拟原始数据
        List<TestBO> testBOS = new ArrayList<>();
        for (int i= 0 ; i <= 10 ; i ++ ){
            testBOS.add(new TestBO("Kern","350521199301144444", 1 , new Date(), LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()),180, 70, BigDecimal.valueOf(195.11),1L, true));
        }
        for (int i= 0 ; i <= 4 ; i ++ ){
            testBOS.add(new TestBO("Jack", "350521199301144445", 2 , new Date(), LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()),190, 75, BigDecimal.valueOf(210.33),2L, true));
        }
        for (int i= 0 ; i <= 6 ; i ++ ){
            testBOS.add(new TestBO("Tom", "350521199301144445", 2 , new Date(), LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()),178, 93, BigDecimal.valueOf(203.11),3L, true));
        }

        System.out.println("数据总量: " + testBOS.size());
        //写入数据到新的页面（两种方式）
        writer.writeTo("人员信息导入模板2", testBOS);



        //写入到本地文件,采取覆盖文件的形式
        poiBox.writeToLocal("C:\\Users\\kern\\Desktop\\人员信息导入模板.xls");

        //读取流中的数据
        List<TestBO> readList = dataTabulator.reader(TestBO.class).readFrom("人员信息导入模板2");
        System.out.println("读取的数据总量： "+ readList.size());

        //打开本地文件的数据读取形式
        try {
            POIBox newBox = POIBoxFactory.open("C:\\Users\\kern\\Desktop\\人员信息导入模板.xls");
            List<TestBO> readList2 = newBox.dataTabulator().reader(TestBO.class).readFrom("人员信息导入模板2");
            System.out.println("读取的数据总量： "+ readList2.size());
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }

        System.out.println("succeed!");

    }

}


