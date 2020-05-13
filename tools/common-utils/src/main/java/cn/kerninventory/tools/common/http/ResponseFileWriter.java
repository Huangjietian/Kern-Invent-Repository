package cn.kerninventory.tools.common.http;

import cn.kerninventory.tools.common.BeanUtil;
import cn.kerninventory.tools.common.StringUtil;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;

/**
 * @author Kern
 * @date 2020/5/13 14:35
 * @description
 */
public class ResponseFileWriter {

    private HttpServletResponse response;
    private volatile boolean isClose;
    private static Object lock = new Object();

    public ResponseFileWriter(HttpServletResponse response) {
        this.response = response;
    }

    public void write(MultipartFile multipartFile, Map<String, String> responseHeader) throws IOException {
        if (isClose) return;
        write(multipartFile.getBytes(), multipartFile.getOriginalFilename(), responseHeader);
    }

    public void write(File file, Map<String, String> responseHeader) throws IOException {
        if (isClose) return;
        FileInputStream in = new FileInputStream(file);
        write(in, file.getPath(), responseHeader);
    }

    public void write(InputStream in, String fileFullName, Map<String, String> responseHeader) throws IOException {
        if (isClose) return;
        byte[] bytes = new byte[in.available()];
        in.read(bytes);
        write(bytes, fileFullName, responseHeader);
    }

    public void write(byte[] bytes, String fileFullName, Map<String, String> responseHeader) throws IOException {
        if (isClose) return;
        synchronized (lock) {
            response.reset();
            ContentType contentType = ContentType.getContentTypesByFileName(fileFullName).iterator().next();
            if (contentType == null) {
                throw new IllegalArgumentException("Unable to match contentType with fileFullName :" + fileFullName);
            }
            response.setContentType(contentType.getExpression());
            addDefaultHeader(response, fileFullName, "UTF-8","*");
            addHeader(response, responseHeader);
            OutputStream out = null;
            try {
                out = response.getOutputStream();
                out.write(bytes);
                out.flush();
            } catch (IOException e) {
                throw e;
            } finally {
                if (out != null) {
                    out.close();
                }
                isClose = true;
            }
        }
    }

    public void addDefaultHeader(HttpServletResponse response, String fileFullName, String charset, String accessControlAllowOrigin) throws UnsupportedEncodingException {
        fileFullName = encode(fileFullName, charset, "ISO-8859-1");
        fileFullName = StringUtil.subFrontByLastIndexOf(fileFullName, System.lineSeparator());
        response.addHeader("Content-Disposition", "attachment;filename=" + fileFullName);
        response.addHeader("charset", charset);
        response.addHeader("Access-Control-Allow-Origin", accessControlAllowOrigin);
    }

    public void addHeader(HttpServletResponse response, Map<String, String> responseHeader) {
        if (BeanUtil.isEmpty(responseHeader)) {
            return;
        }
        for (String key : responseHeader.keySet()) {
            response.addHeader(key, responseHeader.get(key));
        }
    }

    public String encode(String str, String decodeCharset, String encodeCharset) throws UnsupportedEncodingException {
        return new String(str.getBytes(decodeCharset), encodeCharset);
    }

}
