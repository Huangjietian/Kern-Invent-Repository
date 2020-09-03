package cn.kerninventory.tools.excel.fluexcel;

import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * <p>一句话描述</p>
 *
 * @author Kern
 */
public abstract class Appender {

    private Appender() {
    }

    public abstract void output(Workbook workbook);

    public static Appender of(HttpServletResponse httpServletResponse) {
        return new HttpResponseAppender(httpServletResponse);
    }

    public static Appender of(String localFilePath) {
        //TODO 校验文件名是否具有正常的后缀
        File file = new File(localFilePath);
        return new LocalFileAppender(file);
    }

    static class HttpResponseAppender extends Appender {
        HttpServletResponse response;

        HttpResponseAppender(HttpServletResponse response) {
            this.response = response;
        }

        @Override
        public void output(Workbook workbook) {
            //TODO 写入到响应中
        }
    }

    static class LocalFileAppender extends Appender {
        File localFile;

        LocalFileAppender(File localFile) {
            this.localFile = localFile;
        }

        @Override
        public void output(Workbook workbook) {
            //TODO 写入到本地文件中
        }
    }
}
