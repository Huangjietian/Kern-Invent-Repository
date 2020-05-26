package cn.kerninventor.tools.poibox.exception;

/**
 * @author Kern
 * @date 2019/12/17 11:26
 */
public class IllegalSourceClassOfTabulationException extends RuntimeException{

    public IllegalSourceClassOfTabulationException() {
        super();
    }

    public IllegalSourceClassOfTabulationException(String message) {
        super(message);
    }

    public IllegalSourceClassOfTabulationException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalSourceClassOfTabulationException(Throwable cause) {
        super(cause);
    }

}
