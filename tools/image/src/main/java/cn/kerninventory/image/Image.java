package cn.kerninventory.image;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *     image
 * </p>
 *
 * @author Kern
 */
public interface Image {

    /**
     * 获取图片的base64编码
     * @return
     */
    String getBase64String();

    /**
     * 获取图片的字节数组
     * @return
     */
    byte[] getBytes();

    /**
     * 下载到本地
     */
    void toLocalFile(String localFileAbsolutePath);

    /**
     * 写入到http响应中
     */
    void writeToHttpServletResponse(HttpServletResponse response);

    /**
     * 生成略缩图
     * @return
     */
    ThumbnailImage thumbnail(int wide, int high);

    /**
     * 预览
     * @param thumbnail 是否以略缩图展示
     */
    void preview(boolean thumbnail);

}
