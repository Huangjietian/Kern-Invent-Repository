package cn.kerninventor.tools.spring.bean.validator;

import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <h1>中文注释</h1>
 * <p>
 *     该注解仅适用于springboot项目， 用于开启表单参数校验。
 *     需要配合 {@code @SpringBootApplication} 使用，
 *     同时你的项目需要引入springboot AOP 模块 @see <a href="https://spring.io/projects/spring-framework#learn">springboot AOP</a>
 * </p>
 *
 * @author Kern
 * @version 1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableAspectJAutoProxy
@Import(value = BeanValidatorImporter.class)
public @interface EnableBeanValidate {
}
