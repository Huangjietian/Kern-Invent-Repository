package cn.kerninventor.tools.spring.bean.validator;

import javax.validation.ConstraintViolation;
import java.util.Set;

/**
 * <h1>中文描述</h1>
 * <p>
 *     不活跃的回调函数，invoke方法不做任何操作，这是开发阶段放开表单校验的快速方式，不建议发布到正式环境。
 * </p>
 * @author Kern
 * @version 1.0
 */
public class InactionCallback implements ValidateCallback {
    @Override
    public <T> void invoke(Set<ConstraintViolation<T>> constraintViolations) {
        /**
         * NOTHING TO DO!
         */
    }
}
