package cn.kerninventor.tools.spring.beanvalidationcatcher.methodparamsverify.demo;

import cn.kerninventor.tools.spring.beanvalidationcatcher.methodparamsverify.ParamsVerifyHandler;

import javax.validation.ConstraintViolation;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author Kern
 * @date 2020/3/18 10:56
 * @description TODO
 */
public class TestHandler implements ParamsVerifyHandler {
    @Override
    public void handler(Map<Object, Set<ConstraintViolation<Object>>> validResults) {
        Set<ConstraintViolation<Object>> set = new HashSet<>();
        validResults.values().forEach(e -> set.addAll(e));
        if (!set.isEmpty()) {
            throw new IllegalArgumentException(set.stream().map(e -> e.getMessage()).collect(Collectors.joining()));
        }
    }
}
