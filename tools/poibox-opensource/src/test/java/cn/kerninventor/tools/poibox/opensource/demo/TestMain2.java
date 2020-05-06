package cn.kerninventor.tools.poibox.opensource.demo;

import cn.kerninventor.tools.poibox.opensource.Poibox;
import cn.kerninventor.tools.poibox.opensource.PoiboxFactory;
import cn.kerninventor.tools.poibox.opensource.data.templated.writer.Writer;
import cn.kerninventor.tools.poibox.opensource.layout.AnchorIndex;
import cn.kerninventor.tools.poibox.opensource.utils.ExcelLineStringJoiner;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.IOException;

/**
 * @author Kern
 * @date 2020/4/15 16:05
 */
public class TestMain2 {

    public static void main(String[] args) throws IOException {
        Poibox poiBox = PoiboxFactory.open();
        Writer writer = poiBox.dataTabulator().writer(AthleteRosterEO.class);

//        CellStyle cellStyle = poiBox.styler().defaultHeadline(16);
//        CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, 42);
//        writer.getConfiguration()
//                .addBanner("标题", cellStyle, cellRangeAddress)
//                .addBanner("第二个标题", cellStyle, 1,1,0,42);
        writer.writeTo("运动员");

        Sheet sheet = poiBox.getSheet("运动员");
        AnchorIndex index = new AnchorIndex(1, 26, 9, 32);
        ExcelLineStringJoiner joiner = new ExcelLineStringJoiner();
        joiner.append("    ")
                .append("      注意：")
                .append("      1. 请注意你的列太多了")
                .append("      2. 小心点你的屁眼子")
                .append("      3. 透你猴子")
                .append("     ");
        poiBox.layouter().addTextBox(sheet, joiner.toString(), index);


        poiBox.writeToLocal("C:\\Users\\82576\\Desktop\\国家集训队运动员花名册模板.xls");
    }
}
