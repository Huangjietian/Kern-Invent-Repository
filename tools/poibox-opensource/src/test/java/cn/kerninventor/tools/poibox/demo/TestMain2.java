package cn.kerninventor.tools.poibox.demo;

import cn.kerninventor.tools.poibox.Poibox;
import cn.kerninventor.tools.poibox.PoiboxFactory;
import cn.kerninventor.tools.poibox.data.tabulation.writer.TabulationWriter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;

/**
 * @author Kern
 * @date 2020/4/15 16:05
 */
public class TestMain2 {

    public static void main(String[] args) throws IOException {
        Workbook wb = new XSSFWorkbook();
        Poibox poiBox = PoiboxFactory.open(wb);
        TabulationWriter tabulationWriter = poiBox.dataTabulator().writer(AthleteRosterEO.class);
        tabulationWriter.getTabContextModifier().addBanner("第三个标题", poiBox.styler().defaultHeadline(15), 6, 9 );
        tabulationWriter.writeTo("运动员");
        poiBox.writeToLocal("C:\\Users\\82576\\Desktop\\国家集训队运动员花名册模板.xls");
    }
}
