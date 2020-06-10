package cn.kerninventor.tools.spring.bean.validator;

import java.lang.annotation.*;

/**
 * <h1>中文注释</h1>
 * <p>
 *     将该注解注解在方法上以表示该方法的参数需要进行表单校验。
 *     表单校验使用hibernate-validator.jar 提供的实现
 *     关于 @See <a href="https://blog.csdn.net/weixin_43740223/article/details/100889250">hibernate-validator</a>
 * </p>
 * @author Kern
 * @version 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BeanValidate {

    /**
     * <p>
     *      指定一个回调函数{@code ValidateCallback}的实现类的class, 该回调函数将处理表单校验的结果
     *      默认情况下{@code ThrowingCallback} 将把校验结果的错误信息作为异常消息内容抛出一个IllegalArgumentException
     * </p>
     * @return {@code java.lang.Class<? extends ValidateCallback>}
     */
    Class<? extends ValidateCallback> callback() default ThrowingCallback.class;

}
