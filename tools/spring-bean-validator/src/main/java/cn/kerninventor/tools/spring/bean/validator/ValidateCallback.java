package cn.kerninventor.tools.spring.bean.validator;

import javax.validation.ConstraintViolation;
import java.util.Set;

/**
 * @author Kern
 * @date 2020/5/11 19:28
 * @description
 */
@FunctionalInterface
public interface ValidateCallback {

    <T> void invoke(Set<ConstraintViolation<T>> constraintViolations);
}
