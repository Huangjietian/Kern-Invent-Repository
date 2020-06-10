package cn.kerninventor.tools.spring.bean.validator;

import javax.validation.ConstraintViolation;
import java.util.Set;

/**
 * <h1>中文描述</h1>
 * <p>
 *     表单校验回调函数接口。
 *     默认提供了 {@code InactionCallback} 及 {@code ThrowingCallback}
 *     如果需要自定义的回调函数，实现该接口即可。
 * </p>
 * @author Kern
 * @version 1.0
 */
@FunctionalInterface
public interface ValidateCallback {

    <T> void invoke(Set<ConstraintViolation<T>> constraintViolations);
}
