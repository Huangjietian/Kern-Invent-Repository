package cn.kerninventor.tools.spring.beanvalidationcatcher.paramtervalidate;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Kern
 * @date 2020/4/8 8:58
 * @description TODO
 */
@Aspect
@Component
public class ParamValidateInterceptor {

    private ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    @Around("execution(* cn.kerninventor.tools.spring.beanvalidationcatcher..*(..)) && @args(cn.kerninventor.tools.spring.beanvalidationcatcher.paramtervalidate.BeanValidate)")
    public Object paramValidate(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();
        List preVerifier = filtrate(args);
        validate(preVerifier);
        return proceedingJoinPoint.proceed();
    }

    private List<Object> filtrate(Object... objects) {
        List needValidateParams = new ArrayList(16);
        for (Object obj : objects) {
            if (obj.getClass().getDeclaredAnnotation(BeanValidate.class) != null) {
                needValidateParams.add(obj);
            }
        }
        return needValidateParams;
    }

    private void validate(List preVerifier) {
        if (preVerifier == null || preVerifier.size() == 0) {
            return;
        }
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Object>> set = validator.validate(preVerifier.get(0));
        Set<String> results = set.stream().map(e -> e.getMessage()).collect(Collectors.toSet());
        if (set != null && set.size() > 0) {
            throw new IllegalArgumentException(results.toString());
        }
    }

}
