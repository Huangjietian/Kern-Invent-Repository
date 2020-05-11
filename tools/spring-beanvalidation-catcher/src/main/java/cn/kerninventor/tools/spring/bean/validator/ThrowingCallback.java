package cn.kerninventor.tools.spring.bean.validator;

import javax.validation.ConstraintViolation;
import java.util.Set;

/**
 * @author Kern
 * @date 2020/5/11 19:43
 * @description
 */
public class ThrowingCallback implements ValidateCallback {

    @Override
    public <T> void invoke(Set<ConstraintViolation<T>> constraintViolations) {
        StringBuilder builder = new StringBuilder();
        String line = System.lineSeparator();

        constraintViolations.forEach(e -> {
            builder.append(e.getMessage()).append(line);
        });

        throw new IllegalArgumentException(builder.toString());
    }
}
