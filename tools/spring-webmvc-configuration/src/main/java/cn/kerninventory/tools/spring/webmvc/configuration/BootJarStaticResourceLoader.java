package cn.kerninventory.tools.spring.webmvc.configuration;

import org.springframework.boot.system.ApplicationHome;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Kern
 * @date 2020/5/13 11:09
 * @description
 */
public class BootJarStaticResourceLoader {

    private ClassPathResource resource;

    public BootJarStaticResourceLoader(String classpath) {
        if (!classpath.startsWith("/static") && classpath.startsWith("static")) {
            classpath = "/static" + classpath;
        }
        resource = new ClassPathResource(classpath);
    }

    private String generateLocalFilePath(String path) {
        if (path == null || "".equals(path.trim())) {
            path = getApplicationRoot().toString();
        }
        return path + resource.getFilename();
    }

    public File toLocalFile(String path) throws IOException {
        InputStream in = null;
        FileOutputStream out = null;
        try {
            in = resource.getInputStream();
            byte[] bytes = new byte[in.available()];
            in.read(bytes);
            out = new FileOutputStream(generateLocalFilePath(path));
            out.write(bytes);
            out.flush();
        } catch (IOException e) {
            throw e;
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
        return new File(path);
    }

    public static File getApplicationRoot(){
        return new ApplicationHome(BootJarStaticResourceLoader.class).getSource().getParentFile();
    }

    public ClassPathResource getResource() {
        return resource;
    }

}
