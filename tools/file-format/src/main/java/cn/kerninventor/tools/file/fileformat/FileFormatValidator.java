package cn.kerninventor.tools.file.fileformat;

import java.io.*;

/**
 * <h1>中文注释 </h1>
 * <p>
 *     FileFormatValidator只能支持验证当前文件或者流的格式是否与指定的格式相同。
 *     不能通过流来逆推出文件的格式。因为不同文件的头部字节可能是相同的。
 * </p>
 * <h1>Description</h1>
 * <p>
 *     The FileFormatValidator can only support verifying that the current file or stream format is the same as the specified format.
 *     You cannot reverse the file format by streaming.Because the header bytes of different files may be the same.
 * </p>
 *
 * @author Kern
 * @ate 2019/10/22 16:53
 */
public final class FileFormatValidator {

    public static boolean validate(File file, FileType... fileTypes) throws FileNotFoundException {
        FileInputStream stream = new FileInputStream(file);
        return validate(stream, file.getName(), fileTypes);
    }

    public static boolean validate(InputStream stream, String fileName, FileType... fileTypes) {
        return validate(stream, fileTypes) && FileType.isCorrectSuffix(fileName, fileTypes);
    }

    public static boolean validate(InputStream stream, FileType... fileTypes) {
        String header = getFileHeader(stream);
        notBlank(header);
        return matchHeader(header, fileTypes);
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

    private static boolean matchHeader(String header, FileType... fileTypes){
        for (FileType fileType : fileTypes){
            if (header.contains(fileType.getHeader())){
                return true;
            }
        }
        return false;
    }
}
