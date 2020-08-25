package cn.kerninventory.tools.excel.anno;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * <p>一句话描述</p>
 *
 * @author Kern
 */
public abstract class SteamTarget {

    public static SteamTarget of(HttpServletResponse httpServletResponse) {
        return new HttpResponseSteamTarget(httpServletResponse);
    }

    public abstract void output();

    public static SteamTarget of(String localFilePath) {
        //TODO 校验文件名是否具有正常的后缀
        File file = new File(localFilePath);
        return new LocalFileSteamTarget(file);
    }


    static class HttpResponseSteamTarget extends SteamTarget {
        HttpServletResponse response;

        HttpResponseSteamTarget(HttpServletResponse response) {
            this.response = response;
        }

        @Override
        public void output() {
            //TODO 写入到响应中
        }
    }

    static class LocalFileSteamTarget extends SteamTarget {
        File localFile;

        LocalFileSteamTarget(File localFile) {
            this.localFile = localFile;
        }

        @Override
        public void output() {
            //TODO 写入到本地文件中
        }
    }
}
