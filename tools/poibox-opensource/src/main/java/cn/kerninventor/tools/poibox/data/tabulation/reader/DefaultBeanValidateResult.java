package cn.kerninventor.tools.poibox.data.tabulation.reader;

import javax.validation.ConstraintViolation;
import java.util.Set;

/**
 * @author Kern
 * @date 2020/5/25 11:01
 * @description
 */
public class DefaultBeanValidateResult<T> {

    private Set<ConstraintViolation<T>> constraintViolations;

    public DefaultBeanValidateResult(Set<ConstraintViolation<T>> constraintViolations) {
        this.constraintViolations = constraintViolations;
    }

    public void throwing(String separator){
        if (separator == null) {
            separator = "";
        }
        StringBuilder builder = new StringBuilder();
        String finalSeparator = separator;
        constraintViolations.forEach(e -> {
            builder.append(e.getMessage()).append(finalSeparator);
        });
        throw new IllegalArgumentException(builder.toString());
    }

    public void throwing(String separator, Throwable throwable) throws Throwable {
        if (separator == null) {
            separator = "";
        }
        StringBuilder builder = new StringBuilder();
        String finalSeparator = separator;
        constraintViolations.forEach(e -> {
            builder.append(e.getMessage()).append(finalSeparator);
        });
        throwable.addSuppressed(new IllegalArgumentException(builder.toString()));
        throw throwable;
    }

    public void throwingLine(){
        StringBuilder builder = new StringBuilder();
        constraintViolations.forEach(e -> {
            builder.append(e.getMessage()).append(" ").append(System.lineSeparator());
        });
        throw new IllegalArgumentException(builder.toString());
    }

    public Set<ConstraintViolation<T>> getConstraintViolations() {
        return constraintViolations;
    }
}
