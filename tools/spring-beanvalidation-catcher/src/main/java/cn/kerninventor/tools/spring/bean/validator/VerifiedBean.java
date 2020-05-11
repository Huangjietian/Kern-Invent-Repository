package cn.kerninventor.tools.spring.bean.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Kern
 * @date 2020/5/11 19:12
 * @description
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface VerifiedBean {

    Class<? extends ValidateCallback> callback() default InactionCallback.class;

}
