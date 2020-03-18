package cn.kerninventor.tools.spring.beanvalidationcatcher.methodparamsverify;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Kern
 * @date 2020/3/18 10:13
 * @description TODO
 */
@Aspect
@Component
public class ParamsValidator {

    private static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    @Around("@annotation(ParamsVerify)")
    public Object valid(ProceedingJoinPoint joinPoint) throws Throwable {
        Validator validator = validatorFactory.getValidator();
        Map<Object, Set<ConstraintViolation<Object>>> results = new LinkedHashMap<>();
        for (Object arg : joinPoint.getArgs()) {
            results.put(arg, validator.validate(arg));
        }
        ParamsVerify paramsVerify = ((MethodSignature)joinPoint.getSignature()).getMethod().getAnnotation(ParamsVerify.class);
        ParamsVerifyHandler paramsVerifyHandler = paramsVerify.handler().getDeclaredConstructor().newInstance();
        paramsVerifyHandler.handler(results);
        return joinPoint.proceed();
    }
}
