package cn.kerninventor.tools.spring.bean.validator;

import java.lang.annotation.*;

/**
 * @author Kern
 * @date 2020/5/11 19:12
 * @description
 */
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BeanValidate {

    Class<? extends ValidateCallback> callback() default ThrowingCallback.class;

}
