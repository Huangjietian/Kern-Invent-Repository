package cn.kerninventor.tools.poibox.opensource.data.tabulation.reader;

import javax.validation.ConstraintViolation;
import java.util.Set;

/**
 * @author Kern
 * @date 2020/5/25 11:01
 * @description
 */
public class DefaultValidateResult<T> {

    private Set<ConstraintViolation<T>> constraintViolations;

    public DefaultValidateResult(Set<ConstraintViolation<T>> constraintViolations) {
        this.constraintViolations = constraintViolations;
    }

    public void throwing(){
        StringBuilder builder = new StringBuilder();
        constraintViolations.forEach(e -> {
            builder.append(e.getMessage());
        });
        throw new IllegalArgumentException(builder.toString());
    }

    public void throwingSeparator(final String separator){
        StringBuilder builder = new StringBuilder();
        constraintViolations.forEach(e -> {
            builder.append(e.getMessage()).append(separator);
        });
        throw new IllegalArgumentException(builder.toString());
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
