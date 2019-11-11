package cn.kerninventor.tools.data.diagramer.proxy.util;

import java.io.*;

/**
 * @Title: JavaFileCreator
 * @ProjectName tools
 * @Version 1.1.0.RELEASE
 * @Description: TODO
 * @Author Kern
 * @Date 2019/11/11 17:28
 */
public class ProxyJavaFileCreator {

    public static File create(Class targetClass, String className, String context) throws IOException {
        String targetSource = targetClass.getProtectionDomain().getCodeSource().getLocation().getFile()
                + targetClass.getPackage().getName().replace(".", "/")
                + "/" + className + ".java";
        File file = new File(targetSource);
        file.createNewFile();
        FileWriter writer = new FileWriter(file);
        writer.write(context);
        writer.flush();
        writer.close();
        return file;
    }
}
