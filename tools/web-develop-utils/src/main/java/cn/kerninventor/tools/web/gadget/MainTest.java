package cn.kerninventor.tools.web.gadget;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Title: MainTest
 * @ProjectName tools
 * @Version 1.1.0.RELEASE
 * @Description: TODO
 * @Author Kern
 * @Date 2019/11/15 14:24
 */
public class MainTest {


    public static void main(String[] args) {
        test("1中国");
    }

    public static void test(String val) {
        Boolean b = Pattern.matches("^[a-zA-Z\u4e00-\u9fa5][a-zA-Z0-9\u4e00-\u9fa5]*+$", val);
        System.out.println(b);

        Pattern p = Pattern.compile("^[a-zA-Z\u4e00-\u9fa5]+$");
        Matcher m = p.matcher(val);
        Boolean bb = m.find(0);
        System.out.println(bb);
    }
}
