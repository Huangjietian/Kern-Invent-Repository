package cn.kerninventor.tools.spring.beanvalidationcatcher.interfaces;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Kern
 * @date 2020/3/17 18:36
 * @description 需要校验的类，基于 JSR303标准 及 javax.validation 包下的接口规范，
 */
public interface BeanVerifiable {

    default Object errorMessages(Set<ConstraintViolation<Object>> constraintViolationSet) {
        throw new IllegalArgumentException(
                constraintViolationSet.stream().map(e -> e.getMessage()).collect(Collectors.joining()));
    }
}
