package cn.kerninventor.tools.fileformat;

import cn.kerninventor.tools.fileformat.enums.FileFormatEnum;
import cn.kerninventor.tools.fileformat.enums.FileFormatErrorEnum;
import cn.kerninventor.tools.fileformat.exception.FileFormatException;

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
public final class FileFormatCensor extends CheckResult {

    protected FileFormatCensor() {
    }

    public static CheckResult check(InputStream stream, FileFormatEnum... fileFormatEnums) {
        String header = notBlank(getFileHeader(stream));
        Boolean isPass = matchHeader(header, fileFormatEnums);
        return new CheckResult(isPass, getTargetFileFormatWhenUnpass(isPass, header), fileFormatEnums);
    }

    public static void checkE(InputStream stream, FileFormatEnum... fileFormatEnums) {
        checkE(stream, FileFormatErrorEnum.FORMAT_ERROR_MSG.getMsg(), fileFormatEnums);
    }

    public static void checkE(InputStream stream, String errorMsg, FileFormatEnum... fileFormatEnums) {
        if (check(stream, fileFormatEnums).unPass()){
            throw new FileFormatException(errorMsg);
        }
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

    private static boolean matchHeader(String header, FileFormatEnum... fileFormatEnums){
        for (FileFormatEnum fileFormatEnum : fileFormatEnums){
            if (header.contains(fileFormatEnum.getHeader())){
                return true;
            }
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
