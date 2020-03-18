package cn.kerninventor.tools.spring.beanvalidationcatcher.methodparamsverify;

import javax.validation.ConstraintViolation;
import java.util.Map;
import java.util.Set;

/**
 * @author Kern
 * @date 2020/3/18 10:21
 * @description TODO
 */
@FunctionalInterface
public interface ParamsVerifyHandler {

    void handler(Map<Object, Set<ConstraintViolation<Object>>> validResults);
}
