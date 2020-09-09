package cn.kerninventory.tools.spring.webmvc.configuration;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *     全局异常捕获处理器
 * </p>
 *
 * @author Kern
 */
@RestControllerAdvice
public class GlobalExceptionHandlerTemplate {

    /**
     * 请求参数缺失异常处理。
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Object handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        return null;
    }


    /**
     * 基于JSR-303{@see https://download.oracle.com/otn-pub/jcp/bean_validation-1.0-fr-oth-JSpec/bean_validation-1_0-final-spec.pdf?AuthParam=1599643893_709596f53e0f031d5ddcdf9a17605b71}。
     * 表单校验异常处理。
     * 配合 {@link javax.validation.Valid} 和 {@link org.springframework.stereotype.Controller} 注解使用。
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleMaxUploadSizeExceededException(MethodArgumentNotValidException e) {
        return null;
    }


    /**
     * 处理文件上传最大限制异常，多用于文件上传接口。
     * 配合springboot配置项  spring.servlet.multipart.max-file-size   spring.servlet.multipart.max-request-size。
     * 校验上传文件的大小{@link MultipartFile#getSize()} 或是请求Http报文的大小{@link HttpServletRequest#getContentLength()}。
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public Object handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        return null;
    }

    /**
     * 默认的异常处理。
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(Throwable.class)
    public Object handleDefaultException(Throwable e) {
        return null;
    }
}
