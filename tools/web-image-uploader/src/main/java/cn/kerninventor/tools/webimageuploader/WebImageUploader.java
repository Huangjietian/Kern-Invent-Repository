package cn.kerninventor.tools.webimageuploader;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.core.io.InputStreamSource;

import java.io.*;

/**
 * @Title: WebImageUploaderImpl
 * @ProjectName swms
 * @Version 1.1.0.RELEASE
 * @Description: TODO
 * @Author Kern
 * @Date 2019/10/11 16:35
 */
public class WebImageUploader {

    private ImageFormatEnum defaultFormat = ImageFormatEnum.JPEG;
    public final static String THUMBNAIL_PREFIX = "thumb_";

    private final static int THUMBNAIL_DEF_HIGHT = 200;
    private final static int THUMBNAIL_DEF_WIDTH = 200;
    private int thumnailHight;
    private int thumbnailWidth;

    private Integer maxFileSize;

    //原始图片存放路径
    private String originalImageDir;
    //略缩图存放路径
    private String thumbnailImageDir;

    public WebImageUploader(String originalImageDir, String thumbnailImageDir) throws IOException {
        this.originalImageDir = originalImageDir;
        this.thumbnailImageDir = thumbnailImageDir;
        init();
    }

    public WebImageUploader(String originalImageDir, String thumbnailImageDir, ImageFormatEnum format) throws IOException {
        this.defaultFormat = format;
        this.originalImageDir = originalImageDir;
        this.thumbnailImageDir = thumbnailImageDir;
        init();
    }

    private void init() throws IOException {
        mkdir(this.originalImageDir, this.thumbnailImageDir);
        initThumbnailSize();
    }

    public void setMaxFileSize(Integer MB) {
        this.maxFileSize = MB;
    }
    private void initThumbnailSize(){
        setThumbnailSize(THUMBNAIL_DEF_HIGHT,THUMBNAIL_DEF_WIDTH);
    }
    public void setThumbnailSize(int hight, int width){
        this.thumnailHight = hight;
        this.thumbnailWidth = width;
    }

    public void mkdir(String... paths) throws IOException {
        for (String path : paths){
            File dir = new File(path);
            if (!dir.exists()){
                dir.mkdirs();
            }else {
                if (!dir.isDirectory()){
                    throw new IOException("无效的文件目录【" + path + "】");
                }
            }
        }
    }

    public void setImageFormat(ImageFormatEnum format) {
        defaultFormat = format;
    }
    public ImageFormatEnum getDefaultFormat(){
        return defaultFormat;
    }

    public Image upload(InputStreamSource source, String imageName) throws IOException {
        String imagePath = originalImageDir + File.separator + imageName;
        Image image = new Image(defaultFormat);
        //读取
        byte[] buffer = readSource(source);
        //写入
        File file = coverWirteToFile(imagePath, buffer);
        image.setOriginalName(file.getName());
        image.setOriginalPath(imagePath);//原图路径
        image.setOriginalSize(buffer.length);//原图大小

        //生成略缩图
        generateThumbnail(file, image);
        return image;
    }

    private void generateThumbnail(File file, Image image) throws IOException{
        FileInputStream is = null;
        String thumbnailPath = thumbnailImageDir + File.separator + THUMBNAIL_PREFIX + file.getName();
        try {
            Thumbnails.of(file)
                    .size(thumnailHight,thumbnailWidth)
                    .toFile(thumbnailPath);
            is = new FileInputStream(thumbnailPath);
            image.setThumbnailName(THUMBNAIL_PREFIX + file.getName());
            image.setThumbnailPath(thumbnailPath);//略缩图路径
            image.setThumbailSize(is.available());//略缩图大小
            image.setThumbBase64(image.toBASE64(is));//略缩图BASE64
        } catch (IOException e) {
            throw new IOException("生成略缩图失败", e);
        } finally {
            if (is != null)
                is.close();
        }
    }

    public byte[] readSource(InputStreamSource source) throws IOException {
        InputStream is = null;
        try {
            is = source.getInputStream();
            int available = getAvaiable(is);
            //读取字节
            byte[] buffer = new byte[available];
            is.read(buffer);
            return buffer;
        } catch (Exception e) {
            throw new IOException("读取源文件失败", e);
        } finally {
            if (is != null)
                is.close();
        }
    }

    public File coverWirteToFile(String path, byte[] buffer) throws IOException {
        if (maxFileSize != null && ByteSize.compareTo(maxFileSize, buffer.length) < 0){
            throw new IOException("图片大小超过最大限制:" + maxFileSize + "MB");
        }
        FileOutputStream os = null;
        try {
            File file = new File(path);
            if (file.exists()){
                file.delete();
            }
            file.createNewFile();
            //写入字节
            os = new FileOutputStream(file);
            os.write(buffer);
            os.flush();
            return file;
        } catch (IOException e) {
            throw new IOException("写入文件到本地失败", e);
        } finally {
            if (os != null)
                os.close();
        }
    }

    /**
     * 获取源字节数，当网络传输较差时，将尝试五次。
     * @param is
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    private int getAvaiable(InputStream is) throws IOException, InterruptedException {
        int available = is.available();
        int tryTime = 5;
        while (available == 0){
            //短暂暂停当前线程，为网络传输提供传输时间。
            Thread.sleep(10);
            available = is.available();
            -- tryTime;
            if (tryTime == 0){
                throw new IOException("未读取到文件,请检查网络状况");
            }
        }
        return available;
    }

    public static void removeFile(String... paths) {
        for (String path : paths){
            new File(path).delete();
        }
    }

}
