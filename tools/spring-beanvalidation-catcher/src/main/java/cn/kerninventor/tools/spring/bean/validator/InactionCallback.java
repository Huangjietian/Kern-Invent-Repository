package cn.kerninventor.tools.spring.bean.validator;

import javax.validation.ConstraintViolation;
import java.util.Set;

/**
 * @author Kern
 * @date 2020/5/11 19:49
 * @description
 */
public class InactionCallback implements ValidateCallback {
    @Override
    public <T> void invoke(Set<ConstraintViolation<T>> constraintViolations) {
        /**
         * NOTHING TO DO!
         */
    }
}
