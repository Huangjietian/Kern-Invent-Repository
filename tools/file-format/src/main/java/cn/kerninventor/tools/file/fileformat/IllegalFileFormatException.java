package cn.kerninventor.tools.file.fileformat;

/**
 * @uthor Kern
 * @date 2019/10/22 17:01
 */
public class IllegalFileFormatException extends Exception {

    public IllegalFileFormatException(String message) {
        super(message);
    }

    public IllegalFileFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
