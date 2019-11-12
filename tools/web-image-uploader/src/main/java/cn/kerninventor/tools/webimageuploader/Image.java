package cn.kerninventor.tools.webimageuploader;

import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Title: Image
 * @ProjectName swms
 * @Version 1.1.0.RELEASE
 * @Description: TODO
 * @Author Kern
 * @Date 2019/10/15 11:11
 */
public class Image {

    private ImageFormatEnum format;

    private String originalName;
    private String originalPath;
    private int originalSize;

    private String thumbnailName;
    private String thumbnailPath;
    private int thumbailSize;

    private String thumbBase64;

    public Image(ImageFormatEnum format) {
        this.format = format;
    }

    public ImageFormatEnum getFormat() {
        return format;
    }

    public int getOriginalSize() {
        return originalSize;
    }

    public void setOriginalSize(int originalSize) {
        this.originalSize = originalSize;
    }

    public String getOriginalPath() {
        return originalPath;
    }

    public void setOriginalPath(String originalPath) {
        this.originalPath = originalPath;
    }

    public int getThumbailSize() {
        return thumbailSize;
    }

    public void setThumbailSize(int thumbailSize) {
        this.thumbailSize = thumbailSize;
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getThumbnailName() {
        return thumbnailName;
    }

    public void setThumbnailName(String thumbnailName) {
        this.thumbnailName = thumbnailName;
    }

    public static final String PREFIX_BASE64_1 = "data:image/";
    public static final String PREFIX_BASE64_3 = ";base64,";
    public static String prefixOfBASE64(String format){
        return PREFIX_BASE64_1 + format + PREFIX_BASE64_3;
    }

    public String toBASE64(InputStream is) throws IOException {
        try {
            byte[] bytes = new byte[is.available()];
            is.read(bytes);
            return (prefixOfBASE64(format.getFormat()) + new BASE64Encoder().encode(bytes)).replace(System.lineSeparator(), "");
        } catch (IOException e) {
            throw new IOException("图片转BASE64失败: " + e.getMessage(), e);
        }
    }

    public String getThumbBase64() {
        return thumbBase64;
    }

    public void setThumbBase64(String thumbBase64) {
        this.thumbBase64 = thumbBase64;
    }
}
