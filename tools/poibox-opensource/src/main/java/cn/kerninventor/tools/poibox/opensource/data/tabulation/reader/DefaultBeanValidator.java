package cn.kerninventor.tools.poibox.opensource.data.tabulation.reader;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @author Kern
 * @date 2020/5/25 11:01
 * @description
 */
public class DefaultBeanValidator<T> implements BeanValidator<T, DefaultValidateResult>{

    private Validator validator;

    private DefaultValidateResult defaultValidateResult;

    public DefaultBeanValidator(Validator validator) {
        this.validator = validator;
    }

    @Override
    public void validate(T t) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);
        defaultValidateResult = new DefaultValidateResult(constraintViolations);
    }

    @Override
    public DefaultValidateResult getResult() {
        return defaultValidateResult;
    }
}
