package cn.kerninventor.tools.poibox.data.exception;

/**
 * @Title: IllSourceClassTabulationException
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.data.datatable
 * @Author Kern
 * @Date 2019/12/17 11:26
 * @Description: TODO
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
