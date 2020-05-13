package cn.kerninventor.tools.data.structure.proxy.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;

/**
 * @author Kern
 * @date 2019/11/11 17:28
 */
public class ProxyFileCreator {

    public static File create(String proxyClassName, Class targetClass, String context) throws IOException {
        ProtectionDomain protectionDomain = targetClass.getProtectionDomain();
        CodeSource codeSource = protectionDomain.getCodeSource();
        URL url = codeSource.getLocation();
        String targetSource = url.getFile() + targetClass.getPackage().getName().replace(".", "/") + "/" + proxyClassName + ".java";
        File file = null;
        FileWriter writer = null;
        try {
            file = new File(targetSource);
            file.createNewFile();
            writer = new FileWriter(file);
            writer.write(context);
            writer.flush();
            return file;
        } catch (IOException e) {
            throw e;
        } finally {
            if (writer != null) {
                writer.close();
            }

        }
    }
}
