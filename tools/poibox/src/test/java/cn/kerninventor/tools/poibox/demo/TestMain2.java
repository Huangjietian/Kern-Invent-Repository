package cn.kerninventor.tools.poibox.demo;

import cn.kerninventor.tools.poibox.POIBox;
import cn.kerninventor.tools.poibox.POIBoxFactory;
import cn.kerninventor.tools.poibox.data.templated.writer.Writer;
import cn.kerninventor.tools.poibox.layout.AnchorIndex;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.IOException;

/**
 * @author Kern
 * @date 2020/4/15 16:05
 */
public class TestMain2 {

    public static void main(String[] args) throws IOException {
        POIBox poiBox = POIBoxFactory.open();
        Writer writer = poiBox.dataTabulator().writer(AthleteRosterEO.class);

        CellStyle cellStyle = poiBox.styler().defaultHeadline(16);
        CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, 42);

        writer.getConfiguration()
                .addBanner("标题", cellStyle, cellRangeAddress)
                .addBanner("第二个标题", cellStyle, 1,1,0,42);
        writer.writeTo("运动员");

        Sheet sheet = poiBox.getSheet("运动员");
        AnchorIndex index = new AnchorIndex(1, 22, 9, 26, 1, 22, 9, 28);
        StringBuilder builder = new StringBuilder();
        builder.append("    ").append(System.lineSeparator())
                .append("      注意：").append(System.lineSeparator())
                .append("      1. 请注意你的列太多了").append(System.lineSeparator())
                .append("      2. 小心点你的屁眼子").append(System.lineSeparator())
                .append("      3. 透你猴子").append(System.lineSeparator())
                .append(System.lineSeparator());
        poiBox.layouter().addTextBox(sheet, index, builder.toString());


        poiBox.writeToLocal("C:\\Users\\kern\\Desktop\\国家集训队运动员花名册模板.xls");
    }
}
