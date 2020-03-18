package cn.kerninventor.tools.spring.beanvalidationcatcher.methodparamsverify;

import javax.validation.ConstraintViolation;
import java.util.Map;
import java.util.Set;

/**
 * @author Kern
 * @date 2020/3/18 10:32
 * @description TODO
 */
public class DefaultParamsVerifyHandler implements ParamsVerifyHandler {
    @Override
    public void handler(Map<Object, Set<ConstraintViolation<Object>>> validResults) {
        //TODO NOTHING!
    }
}
