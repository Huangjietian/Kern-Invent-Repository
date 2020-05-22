package cn.kerninventor.tools.poibox.opensource.demo;

import cn.kerninventor.tools.poibox.opensource.Poibox;
import cn.kerninventor.tools.poibox.opensource.PoiboxFactory;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.writer.TabulationWriter;
import cn.kerninventor.tools.poibox.opensource.layout.AnchorIndex;
import cn.kerninventor.tools.poibox.opensource.layout.Palette;
import cn.kerninventor.tools.poibox.opensource.layout.SimpleTextBox;
import cn.kerninventor.tools.poibox.opensource.utils.ExcelLineStringJoiner;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
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

//        CellStyle cellStyle = poiBox.styler().defaultHeadline(16);
//        CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, 42);
//        writer.getConfiguration()
//                .addBanner("标题", cellStyle, cellRangeAddress)
//                .addBanner("第二个标题", cellStyle, 1,1,0,42);
        tabulationWriter.writeTo("运动员");

        Sheet sheet = poiBox.getSheet("运动员");
        AnchorIndex index = new AnchorIndex(1, 26, 9, 32);
        ExcelLineStringJoiner joiner = new ExcelLineStringJoiner();
        joiner
//                .append("    ")
                .append("注意：")
                .append("1. 请注意你的列太多了")
                .append("2. 小心点你的屁眼子")
                .append("3. 透你猴子")
//                .append("     ")
        ;
        SimpleTextBox textBox = poiBox.layouter().addTextBox(sheet, joiner.toString(), index);
        textBox.setFillColor(Palette.PURPLE);
        textBox.setLineColor(Palette.CYAN);
        textBox.setMargins(20,20,20,20);
        textBox.setVerticalAlignment(VerticalAlignment.CENTER);

        File file = new File("C:\\Users\\82576\\Desktop\\20171103213127964.png");

        AnchorIndex index2 = new AnchorIndex(1, 36, 9, 50);
        poiBox.layouter().addPicture(sheet, file, index2);

        poiBox.writeToLocal("C:\\Users\\82576\\Desktop\\国家集训队运动员花名册模板.xls");
    }
}
