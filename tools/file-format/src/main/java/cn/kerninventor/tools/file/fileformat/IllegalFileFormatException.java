package cn.kerninventor.tools.file.fileformat;

/**
 * <h1>中文注释</h1>
 * <p>
 *     错误文件格式异常，编译时异常
 * </p>
 * @author Kern
 * @version 1.0
 */
public class IllegalFileFormatException extends Exception {

    public IllegalFileFormatException(String message) {
        super(message);
    }

    public IllegalFileFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
