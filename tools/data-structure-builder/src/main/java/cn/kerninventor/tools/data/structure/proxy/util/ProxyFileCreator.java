package cn.kerninventor.tools.data.structure.proxy.util;

import java.io.*;

/**
 * @author Kern
 * @date 2019/11/11 17:28
 */
public class ProxyFileCreator {

    public static File create(Class targetClass, String className, String context) throws IOException {
        String targetSource = targetClass.getProtectionDomain().getCodeSource().getLocation().getFile()
                + targetClass.getPackage().getName().replace(".", "/")
                + "/" + className + ".java";
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
            //TODO 异常处理
            throw e;
        } finally {
            if (writer != null) {
                writer.close();
            }

        }
    }
}
