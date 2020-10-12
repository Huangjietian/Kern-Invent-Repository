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
 * @version 1.0
 */
public final class FileFormatValidator {

    /**
     * <p>
     *     通过传入的文件，文件格式枚举数组，判断该文件是否符合该文件格式枚举数组中的其中一个格式，符合的话返回true
     * </p>
     * @param file
     * @param fileFormatTypes
     * @return boolean 校验结果
     * @throws FileNotFoundException
     * @throws IllegalFileFormatException
     */
    public static boolean validate(File file, FileFormatType... fileFormatTypes) throws FileNotFoundException, IllegalFileFormatException {
        FileInputStream stream = new FileInputStream(file);
        return validate(stream, file.getName(), fileFormatTypes);
    }

    /**
     * <p>
     *     通过传入的文件输入流，文件名，文件格式枚举数组，判断该文件是否符合该文件格式枚举数组中的其中一个格式，符合的话返回true
     * </p>
     * @param stream
     * @param fileName
     * @param fileFormatTypes
     * @return
     * @throws IllegalFileFormatException
     */
    public static boolean validate(InputStream stream, String fileName, FileFormatType... fileFormatTypes) throws IllegalFileFormatException {
        return validate(stream, false, fileFormatTypes) && FileFormatType.isCorrectSuffix(fileName, fileFormatTypes);
    }

    /**
     * <p>
     *     通过传入的文件输入流，文件名，文件格式枚举数组，判断该文件是否符合该文件格式枚举数组中的其中一个格式，符合的话返回true
     * </p>
     * @param stream
     * @param keepStream 是否保留输入流, 如果true, InputStream的实际类型将转换为 {@link ByteArrayInputStream}
     * @param fileName
     * @param fileFormatTypes
     * @return
     * @throws IllegalFileFormatException
     */
    public static boolean validate(InputStream stream, boolean keepStream, String fileName, FileFormatType... fileFormatTypes) throws IllegalFileFormatException {
        return validate(stream, keepStream, fileFormatTypes) && FileFormatType.isCorrectSuffix(fileName, fileFormatTypes);
    }

    /**
     * <p>
     *     通过传入的文件输入流，文件格式枚举数组，判断该文件是否符合该文件格式枚举数组中的其中一个格式，符合的话返回true<br/>
     *     如果已知文件名的情况下，建议调用含文件名参数的validate方法，本方法仅校验了文件流头部字节，缺少了文件后缀校验。
     * </p>
     * @param stream
     * @param fileFormatTypes
     * @return
     * @throws IllegalFileFormatException
     */
    public static boolean validate(InputStream stream, FileFormatType... fileFormatTypes) throws IllegalFileFormatException {
        return validate(stream, false, fileFormatTypes);
    }

    /**
     * <p>
     *     通过传入的文件输入流，文件格式枚举数组，判断该文件是否符合该文件格式枚举数组中的其中一个格式，符合的话返回true<br/>
     *     如果已知文件名的情况下，建议调用含文件名参数的validate方法，本方法仅校验了文件流头部字节，缺少了文件后缀校验。
     * </p>
     * @param stream
     * @param keepStream  是否保留输入流, 如果true, InputStream的实际类型将转换为 {@link ByteArrayInputStream}
     * @param fileFormatTypes
     * @return
     * @throws IllegalFileFormatException
     */
    public static boolean validate(InputStream stream, boolean keepStream, FileFormatType... fileFormatTypes) throws IllegalFileFormatException {
        InputStream stream1;
        if (keepStream) {
            stream1 = backupInputStream(stream);
        } else {
            stream1 = stream;
        }
        String header = getFileHeader(stream1);
        notBlank(header);
        return matchHeader(header, fileFormatTypes);
    }

    private static InputStream backupInputStream(InputStream source) throws IllegalFileFormatException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = source.read(buffer)) > -1) {
                baos.write(buffer, 0, len);
            }
            baos.flush();
        } catch (IOException e) {
            throw new IllegalFileFormatException("File resolution error! " + e.getMessage(), e);
        }
        source = new ByteArrayInputStream(baos.toByteArray());
        return new ByteArrayInputStream(baos.toByteArray());
    }

    private static String getFileHeader(InputStream stream) throws IllegalFileFormatException {
        try {
            byte[] bytes = new byte[4];
            stream.read(bytes, 0, bytes.length);
            return bytesToHexString(bytes);
        } catch (IOException e) {
            throw new IllegalFileFormatException("File resolution error! " + e.getMessage(), e);
        } finally {
            if (stream != null){
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
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
        for (byte b : src) {
            hv = Integer.toHexString(b & 0xFF).toUpperCase();
            if (hv.length() < 2) {
                builder.append(0);
            }
            builder.append(hv);
        }
        return builder.toString();
    }

    private static void notBlank(String header) throws IllegalFileFormatException {
        if (header == null || "".equals(header.replace("0", "").trim())){
            throw new IllegalFileFormatException("Empty file error!");
        }
    }

    private static boolean matchHeader(String header, FileFormatType... fileFormatTypes){
        for (FileFormatType fileFormatType : fileFormatTypes){
            if (header.contains(fileFormatType.getHeader())){
                return true;
            }
        }
        return false;
    }
}
