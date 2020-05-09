package cn.kerninventor.tools.poibox.opensource.exception;

/**
 * @author Kern
 * @date 2020/5/9 12:30
 * @description
 */
public class UnSupportedDataTypeException extends RuntimeException {

    public UnSupportedDataTypeException() {
        super();
    }

    public UnSupportedDataTypeException(String message) {
        super(message);
    }

    public UnSupportedDataTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnSupportedDataTypeException(Throwable cause) {
        super(cause);
    }

    public UnSupportedDataTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
