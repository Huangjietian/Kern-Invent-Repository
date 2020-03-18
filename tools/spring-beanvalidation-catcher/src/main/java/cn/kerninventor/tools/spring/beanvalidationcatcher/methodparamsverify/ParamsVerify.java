package cn.kerninventor.tools.spring.beanvalidationcatcher.methodparamsverify;

import java.lang.annotation.*;

/**
 * @author Kern
 * @date 2020/3/18 10:10
 * @description 标记需要校验的方法,将拦截方法的参数进行校验。
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ParamsVerify {

    Class<? extends ParamsVerifyHandler> handler() default DefaultParamsVerifyHandler.class;
}
