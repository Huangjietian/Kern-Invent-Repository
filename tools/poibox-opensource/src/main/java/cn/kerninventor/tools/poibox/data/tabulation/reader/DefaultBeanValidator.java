package cn.kerninventor.tools.poibox.data.tabulation.reader;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @author Kern
 * @date 2020/5/25 11:01
 * @description
 */
public class DefaultBeanValidator<T> implements BeanValidator<T, DefaultBeanValidateResult>{

    private Validator validator;

    private DefaultBeanValidateResult defaultBeanValidateResult;

    public DefaultBeanValidator(Validator validator) {
        this.validator = validator;
    }

    @Override
    public void validate(T t) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);
        defaultBeanValidateResult = new DefaultBeanValidateResult(constraintViolations);
    }

    @Override
    public DefaultBeanValidateResult getResult() {
        return defaultBeanValidateResult;
    }
}
