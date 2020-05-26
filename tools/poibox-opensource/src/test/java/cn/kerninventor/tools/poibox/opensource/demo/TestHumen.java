package cn.kerninventor.tools.poibox.opensource.demo;

import cn.kerninventor.tools.poibox.opensource.Poibox;
import cn.kerninventor.tools.poibox.opensource.PoiboxFactory;

import java.io.IOException;

/**
 * @author Kern
 * @date 2020/5/7 12:50
 * @description
 */
public class TestHumen {

    public static void main(String[] args) throws IOException {
        Poibox poibox = PoiboxFactory.open();
        poibox.dataTabulator().writer(Humen.class).writeTo("humen");
        poibox.writeToLocal("C:\\Users\\82576\\Desktop\\Humen.xls");
    }
}
