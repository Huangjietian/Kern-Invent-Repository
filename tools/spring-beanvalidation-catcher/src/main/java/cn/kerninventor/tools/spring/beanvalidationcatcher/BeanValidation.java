package cn.kerninventor.tools.spring.beanvalidationcatcher;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Title: BeanValidation
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.spring.beanvalidationcatcher
 * @Author Kern
 * @Date 2020/2/4 20:59
 * @Description: TODO
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BeanValidation {
}
