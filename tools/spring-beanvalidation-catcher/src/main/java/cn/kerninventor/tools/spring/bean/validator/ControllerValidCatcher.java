package cn.kerninventor.tools.spring.bean.validator;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author Kern
 * @date 2020/5/11 19:18
 * @description
 */
@Aspect
@Component
public class ControllerValidCatcher {

    @Around("within(*..*Controller) && @args(VerifiedBean)")
    public Object catching(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        Arrays.stream(args)
                .forEach(
                        e -> {
                           Validator.validate(e);
                        }
                );
        return joinPoint.proceed();
    }
}
