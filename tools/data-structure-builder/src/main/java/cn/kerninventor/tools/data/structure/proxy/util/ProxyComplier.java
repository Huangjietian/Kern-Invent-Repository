package cn.kerninventor.tools.data.structure.proxy.util;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author Kern
 * @date 2019/11/11 14:52
 */
public class ProxyComplier {

    public static void compile(File proxyFile) throws IOException {
        StandardJavaFileManager standardJavaFileManager = null;
        try {
            JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
            standardJavaFileManager = javaCompiler.getStandardFileManager(null, null, Charset.forName("UTF-8"));
            Iterable javaFile = standardJavaFileManager.getJavaFileObjects(proxyFile);
            JavaCompiler.CompilationTask task = javaCompiler.getTask(null, standardJavaFileManager, null, null, null, javaFile);
            task.call();
            standardJavaFileManager.flush();
        } catch (Exception e) {
            //TODO 异常处理
            throw e;
        } finally {
            if (standardJavaFileManager != null) {
                standardJavaFileManager.close();
            }
        }

    }
}
