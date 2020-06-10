package cn.kerninventor.tools.spring.bean.validator;

import javax.validation.ConstraintViolation;
import java.util.Set;

/**
 * <h1>中文描述</h1>
 * <p>
 *     抛出异常回调， 将表单校验的结果作为异常信息抛出IllegalArgumentException， 这是默认的回调实现。
 * </p>
 * @author Kern
 * @version 1.0
 */
public class ThrowingCallback implements ValidateCallback {

    @Override
    public <T> void invoke(Set<ConstraintViolation<T>> constraintViolations) {
        if (constraintViolations == null || constraintViolations.isEmpty()) {
            return;
        }
        StringBuilder builder = new StringBuilder();
        String line = System.lineSeparator();
        constraintViolations.forEach(e -> {
            builder.append(e.getMessage()).append(line);
        });
        throw new IllegalArgumentException(builder.toString());
    }
}
