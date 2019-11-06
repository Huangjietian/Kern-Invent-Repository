package cn.kerninventor.tools.fileformat.exception;

import java.util.IllegalFormatException;

/**
 * @Title: FileFormatException
 * @ProjectName fileformat
 * @Version 1.0.SNAPSHOT
 * @Description: TODO
 * @Author Kern
 * @Date 2019/10/22 17:01
 */
public class FileFormatException extends RuntimeException {

    public FileFormatException(String message) {
        super(message);
    }

    public FileFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
