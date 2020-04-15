package cn.kerninventor.tools.poibox.demo;

import cn.kerninventor.tools.poibox.POIBox;
import cn.kerninventor.tools.poibox.POIBoxFactory;
import cn.kerninventor.tools.poibox.data.templated.writer.Writer;
import cn.kerninventor.tools.poibox.layout.AnchorIndex;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.IOException;

/**
 * @author Kern
 * @date 2020/4/15 16:05
 */
public class TestMain2 {

    public static void main(String[] args) throws IOException {
        POIBox poiBox = POIBoxFactory.open();
        Writer writer = poiBox.dataTabulator().writer(AthleteRosterEO.class);
        writer.writeTo("你好");

        Sheet sheet = poiBox.getSheet("你好");
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
