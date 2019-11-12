package cn.kerninventor.tools.webimageuploader;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Title: ImageFormatEnum
 * @ProjectName swms
 * @Version 1.1.0.RELEASE
 * @Description: TODO
 * @Author Kern
 * @Date 2019/10/11 16:41
 */
public enum ImageFormatEnum {

    JPG("jpg","FFD8FFE",".jpg"),
    PNG("png","89504E47",".png"),
    JPEG("jpeg","FFD8FFE",".jpeg"),
    ;

    ImageFormatEnum(String format, String header, String suffix) {
        this.format = format;
        this.header = header;
        this.suffix = suffix;
    }

    private String format;
    private String header;
    private String suffix;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public static ImageFormatEnum getEnumByPath(String path){
        for (ImageFormatEnum imageFormatEnum : ImageFormatEnum.values()){
            if (path.contains(imageFormatEnum.suffix)){
                return imageFormatEnum;
            }
        }
        return null;
    }

    public static boolean isSupportImageByHeader(String header){
        List<String> list = Lists.newArrayList(ImageFormatEnum.values()).stream().map(e -> e.header).collect(Collectors.toList());
        for (String str : list){
            if (header.contains(str)){
                return true;
            }
        }
        return false;
    }

    public static boolean isSupportImageByFormat(String format){
        return Lists.newArrayList(ImageFormatEnum.values()).stream().map(e -> e.format).collect(Collectors.toList()).contains(format);
    }

    public static boolean isSupportImageBySuffix(String suffix){
        return Lists.newArrayList(ImageFormatEnum.values()).stream().map(e -> e.suffix).collect(Collectors.toList()).contains(suffix);
    }

}
