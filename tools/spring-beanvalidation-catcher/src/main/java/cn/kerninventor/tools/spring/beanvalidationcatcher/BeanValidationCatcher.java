package cn.kerninventor.tools.spring.beanvalidationcatcher;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @Title: BeanValidationCatcher
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.spring.beanvalidationcatcher
 * @Author Kern
 * @Date 2020/2/4 20:47
 * @Description: TODO
 */
@Aspect
@Component
public class BeanValidationCatcher {

    @Around("@args(BeanValidation)")
    public void catcher(ProceedingJoinPoint proceedingJoinPoint){
        Object[] args = proceedingJoinPoint.getArgs();
        for (Object arg : args) {
            Class clazz = arg.getClass();
            if (clazz.getAnnotation(BeanValidation.class) != null) {
                //TODO 校验
                System.out.println("校验");
            }
        }

    }
}
