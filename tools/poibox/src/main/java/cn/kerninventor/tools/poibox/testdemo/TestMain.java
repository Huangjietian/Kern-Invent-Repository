package cn.kerninventor.tools.poibox.testdemo;

import cn.kerninventor.tools.poibox.POIBox;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;

/**
 * @Title: MainTest
 * @ProjectName tools
 * @Version 1.1.0.RELEASE
 * @Description: TODO
 * @Author Kern
 * @Date 2019/10/29 20:04
 */
public class TestMain {

    public static void main(String[] args) throws IOException, InvalidFormatException {

        POIBox myBox = POIBox.open();
        myBox.dataProcessor().templateTo("人员信息导入模板1", Test.class);
        myBox.dataProcessor().templateTo("人员信息导入模板2", Test.class);

        myBox.wirteToLocal("C:\\Users\\82576\\Desktop\\人员信息导入模板.xlxs");

    }
}


