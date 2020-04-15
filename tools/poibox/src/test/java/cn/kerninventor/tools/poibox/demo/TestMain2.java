package cn.kerninventor.tools.poibox.demo;

import cn.kerninventor.tools.poibox.POIBox;
import cn.kerninventor.tools.poibox.POIBoxFactory;

import java.io.IOException;

/**
 * @author Kern
 * @date 2020/4/15 16:05
 */
public class TestMain2 {

    public static void main(String[] args) throws IOException {
        POIBox poiBox = POIBoxFactory.open();
        poiBox.dataTabulator().writer(AthleteRosterEO.class).writeTo("你好");
        poiBox.writeToLocal("C:\\Users\\kern\\Desktop\\国家集训队运动员花名册模板.xls");
    }
}
