package cn.kerninventor.tools.spring.bean.validator;


import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * @author Kern
 * @date 2020/5/11 19:25
 * @description
 */
public class Validator {

    private static javax.validation.ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private static javax.validation.Validator validator = factory.getValidator();

    public static <T> void validate(@NotNull T bean) {
        VerifiedBean verifiedBean = bean.getClass().getDeclaredAnnotation(VerifiedBean.class);
        if (verifiedBean == null) {
            return;
        }
        validate(bean, verifiedBean);
    }

    public static <T> void validate(@NotNull T bean, VerifiedBean verifiedBean) {
        if (verifiedBean == null) {
            return;
        }
        ValidateCallback callback;
        try {
            callback = verifiedBean.callback().getConstructor().newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException("ValidateCallback lacks a constructor with no parameter!");
        }
        validate(bean, callback);
    }

    public static <T> void validate(@NotNull T bean, ValidateCallback callback) {
        if (bean == null) {
            return;
        }
        Set<ConstraintViolation<T>> constraintViolations = validating(bean);
        callback.invoke(constraintViolations);
    }

    public static <T> Set<ConstraintViolation<T>> validating(@NotNull T bean) {
        return validator.validate(bean);
    }
}
