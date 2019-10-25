package cn.kern.inventor.tools.fileformat;

import cn.kern.inventor.tools.fileformat.enums.FileFormatEnum;
import cn.kern.inventor.tools.fileformat.enums.FileFormatErrorEnum;
import cn.kern.inventor.tools.fileformat.exception.FileFormatException;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Title: FilePolice
 * @ProjectName fileformat
 * @Version 1.0.SNAPSHOT
 * @Description: 校验文件格式
 * @Author Kern
 * @Date 2019/10/22 16:53
 */
public final class FileFormatCensor extends FormatChecker {

    protected FileFormatCensor() {
    }

    public static FormatChecker check(FileFormatEnum formatEnum, InputStream stream) {
        String header = notBlank(getFileHeader(stream));
        Boolean isPass = matchHeader(header, formatEnum);
        return new FormatChecker(isPass, getTargetFileFormatWhenUnpass(isPass, header), formatEnum);
    }

    public static void checkE(FileFormatEnum fileFormatEnum, InputStream stream) {
        if (!check(fileFormatEnum, stream).isPass())
            throw new FileFormatException(FileFormatErrorEnum.FORMAT_ERROR_MSG.getMsg());
    }

    public static String getFileHeader(InputStream stream) {
        try {
            byte[] bytes = new byte[4];
            stream.read(bytes, 0, bytes.length);
            return bytesToHexString(bytes);
        } catch (IOException e) {
            throw new FileFormatException(FileFormatErrorEnum.FILE_RESOLVE_ERROR_MSG.getMsg(), e);
        } finally {
            if (stream != null){
                try {
                    stream.close();
                } catch (IOException e) {
                    throw new FileFormatException(FileFormatErrorEnum.FILE_RESOLVE_ERROR_MSG.getMsg(), e);
                }
            }
        }
    }

    private static String bytesToHexString(byte[] src) {
        StringBuilder builder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        String hv;
        for (int i = 0; i < src.length; i++) {
            hv = Integer.toHexString(src[i] & 0xFF).toUpperCase();
            if (hv.length() < 2) {
                builder.append(0);
            }
            builder.append(hv);
        }
        return builder.toString();
    }

    private static String notBlank(String header){
        if (header == null || "".equals(header.replace("0", "").trim())){
            throw new FileFormatException(FileFormatErrorEnum.FILE_BLANK_MSG.getMsg());
        }
        return header;
    }

    private static boolean matchHeader(String header, FileFormatEnum formatEnum){
        if (header.contains(formatEnum.getHeader())){
            return true;
        }
        return false;
    }

    private static FileFormatEnum getTargetFileFormatWhenUnpass(Boolean passed, String header){
        if (!passed){
            return FileFormatEnum.getFormatByHeader(header);
        }
        return null;
    }

}
