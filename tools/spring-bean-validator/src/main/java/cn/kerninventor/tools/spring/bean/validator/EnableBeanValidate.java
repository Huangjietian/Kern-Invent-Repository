package cn.kerninventor.tools.spring.bean.validator;

import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author Kern
 * @date 2020/5/19 10:17
 * @description
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableAspectJAutoProxy
@Import(value = BeanValidatorImporter.class)
public @interface EnableBeanValidate {
}
