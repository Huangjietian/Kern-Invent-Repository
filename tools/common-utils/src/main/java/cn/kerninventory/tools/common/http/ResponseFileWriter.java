package cn.kerninventory.tools.common.http;

import cn.kerninventory.tools.common.BeanUtil;
import cn.kerninventory.tools.common.StringUtil;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * <h1>中文注释</h1>
 * <p>
 *     响应文件写入器
 * </p>
 * @author Kern
 * @version 1.0
 */
public class ResponseFileWriter {

    private HttpServletResponse response;
    private volatile boolean isClose;
    private static final Object lock = new Object();

    /**
     * <p>
     *     构造器, 需要传入一个{@link HttpServletResponse}对象构建
     * </p>
     * @param response
     */
    public ResponseFileWriter(HttpServletResponse response) {
        this.response = response;
    }

    /**
     * <p>
     *     {@link MultipartFile}对象写入响应<br/>
     *     {@link Map}中的key作为头部元素名称，value作为头部元素值写入到响应
     * </p>
     * @param multipartFile
     * @param responseHeader
     * @throws IOException
     */
    public void write(MultipartFile multipartFile, Map<String, String> responseHeader) throws IOException {
        if (isClose) return;
        write(multipartFile.getBytes(), multipartFile.getOriginalFilename(), responseHeader);
    }

    /**
     * <p>
     *     {@link File}对象写入响应<br/>
     *     {@link Map}中的key作为头部元素名称，value作为头部元素值写入到响应
     * </p>
     * @param file
     * @param responseHeader
     * @throws IOException
     */
    public void write(File file, Map<String, String> responseHeader) throws IOException {
        if (isClose) return;
        FileInputStream in = new FileInputStream(file);
        write(in, file.getPath(), responseHeader);
    }

    /**
     * <p>
     *     {@link InputStream}对象写入响应<br/>
     *     指定头部 filename=参数fileFullName <br/>
     *     {@link Map}中的key作为头部元素名称，value作为头部元素值写入到响应
     * </p>
     * @param in
     * @param fileFullName
     * @param responseHeader
     * @throws IOException
     */
    public void write(InputStream in, String fileFullName, Map<String, String> responseHeader) throws IOException {
        if (isClose) return;
        byte[] bytes = new byte[in.available()];
        in.read(bytes, 0, bytes.length);
        write(bytes, fileFullName, responseHeader);
    }

    /**
     * <p>
     *     将字节数组写入响应<br/>
     *     指定头部 filename=参数fileFullName <br/>
     *     {@link Map}中的key作为头部元素名称，value作为头部元素值写入到响应
     * </p>
     * @param bytes
     * @param fileFullName
     * @param responseHeader
     * @throws IOException
     */
    public void write(byte[] bytes, String fileFullName, Map<String, String> responseHeader) throws IOException {
        if (isClose) return;
        synchronized (lock) {
            response.reset();
            ResponseFileStreamContentType responseFileStreamContentType = ResponseFileStreamContentType.getContentTypesByFileName(fileFullName).iterator().next();
            if (responseFileStreamContentType == null) {
                throw new IllegalArgumentException("Unable to match contentType with fileFullName :" + fileFullName);
            }
            response.setContentType(responseFileStreamContentType.getExpression());
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
                isClose = true;
                if (out != null) {
                    out.close();
                }
            }
        }
    }

    private void addDefaultHeader(HttpServletResponse response, String fileFullName, String charset, String accessControlAllowOrigin) {
        fileFullName = IsoEncoding(fileFullName, charset);
        fileFullName = StringUtil.subFrontByLastIndexOf(fileFullName, System.lineSeparator());
        response.addHeader("Content-Disposition", "attachment;filename=" + fileFullName);
        response.addHeader("charset", charset);
        response.addHeader("Access-Control-Allow-Origin", accessControlAllowOrigin);
    }

    private void addHeader(HttpServletResponse response, Map<String, String> responseHeader) {
        if (BeanUtil.isEmpty(responseHeader)) {
            return;
        }
        for (String key : responseHeader.keySet()) {
            response.addHeader(key, responseHeader.get(key));
        }
    }

    private String IsoEncoding(String str, String decodeCharset) {
        Charset charset = Charset.forName(decodeCharset);
        byte[] decodeBytes = str.getBytes(charset);
        return new String(decodeBytes, Charset.forName("ISO-8859-1"));
    }


}
