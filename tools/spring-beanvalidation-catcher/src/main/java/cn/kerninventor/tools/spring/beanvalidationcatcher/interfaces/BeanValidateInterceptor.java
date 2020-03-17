package cn.kerninventor.tools.spring.beanvalidationcatcher.interfaces;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author Kern
 * @date 2020/3/17 19:33
 * @description TODO
 */
@Aspect
@Component
public class BeanValidateInterceptor {

    private BeanValidator validator = new BeanValidator();

    @Around("execution(* *..*Controller.*(..)) && args(BeanVerifiable)")
    public Object validIntercepte(ProceedingJoinPoint joinPoint) throws Throwable {
        validator.valid(joinPoint.getArgs());
        return joinPoint.proceed();
    }
}
