package cn.kerninventor.tools.poibox.demo;

import cn.kerninventor.tools.poibox.Poibox;
import cn.kerninventor.tools.poibox.PoiboxFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kern
 * @date 2020/5/7 12:50
 * @description
 */
public class TestHumen {

    public static void main(String[] args) throws IOException {
        Humen humen1 = new Humen("中国", "小明","1");
        Humen humen2 = new Humen("中国", "小红","2");
        Humen humen3 = new Humen("中国", "小白","1");

        List<Humen> humenList = new ArrayList<>();
        humenList.add(humen1);
        humenList.add(humen2);
        humenList.add(humen3);


        Poibox poibox = PoiboxFactory.open();
        poibox.dataTabulator().writer(Humen.class).writeTo("模板").withColumnDataTranslator("gender", GenderEnum.MEN).writeTo("数据", humenList);
        poibox.writeToLocal("C:\\Users\\82576\\Desktop\\Humen.xls");
    }
}
