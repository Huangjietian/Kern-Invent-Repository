package cn.kerninventor.tools.spring.bean.validator;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * <h1>中文描述</h1>
 * <p>
 *     校验切面实现
 * </p>
 * @author Kern
 * @version 1.0
 */
@Aspect
public class BeanValidator {

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Around("@annotation(BeanValidate)")
    public Object catching(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        BeanValidate beanValidate = method.getDeclaredAnnotation(BeanValidate.class);
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            validate(arg, beanValidate);
        }
        return joinPoint.proceed();
    }

    private <T> void validate(T bean, BeanValidate beanValidate) {
        if (beanValidate == null) {
            return;
        }
        ValidateCallback callback;
        try {
            callback = beanValidate.callback().getConstructor().newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException("ValidateCallback lacks a constructor with no parameter!");
        }
        validate(bean, callback);
    }

    public static <T> void validate(T bean, ValidateCallback callback) {
        if (bean == null || callback == null) {
            return;
        }
        Set<ConstraintViolation<T>> constraintViolations = validating(bean);
        callback.invoke(constraintViolations);
    }

    public static <T> Set<ConstraintViolation<T>> validating(T bean) {
        return validator.validate(bean);
    }
}
