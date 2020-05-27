package cn.kerninventor.tools.file.fileformat;

/**
 * @uthor Kern
 * @date 2019/10/22 17:01
 */
public class FileFormatException extends RuntimeException {

    public FileFormatException(String message) {
        super(message);
    }

    public FileFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
