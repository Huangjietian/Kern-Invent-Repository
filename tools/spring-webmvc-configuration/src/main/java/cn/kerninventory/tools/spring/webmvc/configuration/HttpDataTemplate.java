package cn.kerninventory.tools.spring.webmvc.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Map;

/**
 * <p>
 *     Web服务中 Http响应中内嵌的数据报文 {@link javax.servlet.http.HttpServletResponse}
 * </p>
 *
 * @author Kern
 */
public class HttpDataTemplate<HttpDataBody> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Http响应状态
     */
    @JsonProperty(value = "status")
    private int status;

    /**
     * 响应消息
     */
    @JsonProperty(value = "message")
    private String message;

    /**
     * 响应报文数据主体
     */
    @JsonProperty(value = "data")
    private HttpDataBody dataBody;

    /**
     * 可选值集合
     */
    @JsonProperty(value = "optional")
    private Map<String, Object> optionalValues;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public HttpDataBody getDataBody() {
        return dataBody;
    }

    public Map<String, Object> getOptionalValues() {
        return optionalValues;
    }

    private HttpDataTemplate() {
    }

    public static HttpDataTemplate success() {
        return of(HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), null, null);
    }

    public static HttpDataTemplate success(String message) {
        return of(HttpStatus.OK, message, null, null);
    }

    public static HttpDataTemplate success(Map<String, Object> optionalValues) {
        return of(HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), null, optionalValues);
    }

    public static <T> HttpDataTemplate<T> success(T dataBody) {
        return of(HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), dataBody, null);
    }

    public static HttpDataTemplate success(String message, Map<String, Object> optionalValues) {
        return of(HttpStatus.OK, message, null, optionalValues);
    }

    public static <T> HttpDataTemplate<T> success(String message, T dataBody) {
        return of(HttpStatus.OK, message, dataBody, null);
    }

    public static <T> HttpDataTemplate<T> success(T dataBody, Map<String, Object> optionalValues) {
        return of(HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), dataBody, optionalValues);
    }

    public static <T> HttpDataTemplate<T> success(String message, T dataBody, Map<String, Object> optionalValues) {
        return of(HttpStatus.OK, message, dataBody, optionalValues);
    }

    public static HttpDataTemplate error() {
        return of(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null, null);
    }

    public static HttpDataTemplate error(String message) {
        return of(HttpStatus.INTERNAL_SERVER_ERROR, message, null, null);
    }

    public static HttpDataTemplate error(Map<String, Object> optionalValues) {
        return of(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null, optionalValues);
    }

    public static <T> HttpDataTemplate<T> error(T dataBody) {
        return of(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), dataBody, null);
    }

    public static HttpDataTemplate error(String message, Map<String, Object> optionalValues) {
        return of(HttpStatus.INTERNAL_SERVER_ERROR, message, null, optionalValues);
    }

    public static <T> HttpDataTemplate<T> error(String message, T dataBody) {
        return of(HttpStatus.INTERNAL_SERVER_ERROR, message, dataBody, null);
    }

    public static <T> HttpDataTemplate<T> error(T dataBody, Map<String, Object> optionalValues) {
        return of(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), dataBody, optionalValues);
    }

    public static <T> HttpDataTemplate<T> error(String message, T dataBody, Map<String, Object> optionalValues) {
        return of(HttpStatus.INTERNAL_SERVER_ERROR, message, dataBody, optionalValues);
    }

    public static <T> HttpDataTemplate<T> of(HttpStatus status, String message, T dataBody, Map<String, Object> optionalValues) {
        HttpDataTemplate<T> httpData = new HttpDataTemplate<>();
        httpData.status = status.value();
        httpData.message = message;
        httpData.dataBody = dataBody;
        httpData.optionalValues = optionalValues;
        return httpData;
    }

}
