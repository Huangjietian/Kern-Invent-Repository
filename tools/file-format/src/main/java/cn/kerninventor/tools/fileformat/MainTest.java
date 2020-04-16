package cn.kerninventor.tools.fileformat;

import cn.kerninventor.tools.fileformat.enums.FileFormatEnum;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @Title: MainTest
 * @ProjectName tools
 * @Version 1.1.0.RELEASE
 * @Description: TODO
 * @Author Kern
 * @Date 2019/11/4 8:50
 */
public class MainTest {

    private static final String filePath = "C:\\Users\\kern\\Desktop\\中央成绩任务跟踪20200213.xlsx";

    public static void main(String[] args) throws FileNotFoundException {

        FileInputStream source = new FileInputStream(filePath);

        /**
         * 注意，输入流将在一次校验后被关闭，需要重新获取新的输入流，以下示例同时运行将导致异常
         */

        //你可以用一个FormatChecker的实例去装载FileFormatCensor.check的返回值，他表示一次文件校验的结果
        CheckResult checker = FileFormatCensor.check(source, FileFormatEnum.PNG);

        //你也可以直接调用FileFormatCensor.checkE方法，如果校验未通过，它将返回一个默认的异常。
        FileFormatCensor.checkE(source, FileFormatEnum.PNG);

        //你也可以调用含有errorMsg参数的checkE方法，他支持你在文件校验失败后抛出一个你指定消息的异常
        //同时，任何校验方法的FileFormatEnum枚举都是多个的
        FileFormatCensor.checkE(source, "文件校验失败，请上传图片", FileFormatEnum.PNG, FileFormatEnum.JPG, FileFormatEnum.JPEG);
    }
}
