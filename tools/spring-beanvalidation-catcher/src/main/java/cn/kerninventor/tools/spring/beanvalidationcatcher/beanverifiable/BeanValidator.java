package cn.kerninventor.tools.spring.beanvalidationcatcher.beanverifiable;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * @author Kern
 * @date 2020/3/17 18:54
 */
public class BeanValidator {

    private static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    public void valid(Object[] args){
        Validator validator = validatorFactory.getValidator();
        for (Object arg : args) {
            if (BeanVerifiable.class.isAssignableFrom(arg.getClass())) {
                Set<ConstraintViolation<Object>> constraintViolationSet = validator.validate(arg);
                if (constraintViolationSet != null && !constraintViolationSet.isEmpty())
                    ((BeanVerifiable)arg).errorMessages(constraintViolationSet);
            }
        }

    }
}