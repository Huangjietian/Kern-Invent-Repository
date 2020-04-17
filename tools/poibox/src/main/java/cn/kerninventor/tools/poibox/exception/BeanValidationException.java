package cn.kerninventor.tools.poibox.exception;

import javax.validation.ConstraintViolation;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Kern
 * @date 2020/4/17 18:13
 */
public class BeanValidationException extends RuntimeException {

    private Map<Integer, Set<ConstraintViolation>> constraintMap = new HashMap<>();

    public BeanValidationException(Map<Integer, Set<ConstraintViolation>> constraintMap) {
        this.constraintMap = constraintMap;
    }

    public BeanValidationException(String message, Map<Integer, Set<ConstraintViolation>> constraintMap) {
        super(message);
        this.constraintMap = constraintMap;
    }

    public BeanValidationException(String message, Throwable cause, Map<Integer, Set<ConstraintViolation>> constraintMap) {
        super(message, cause);
        this.constraintMap = constraintMap;
    }

    public BeanValidationException(Throwable cause, Map<Integer, Set<ConstraintViolation>> constraintMap) {
        super(cause);
        this.constraintMap = constraintMap;
    }

    public BeanValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Map<Integer, Set<ConstraintViolation>> constraintMap) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.constraintMap = constraintMap;
    }
}
