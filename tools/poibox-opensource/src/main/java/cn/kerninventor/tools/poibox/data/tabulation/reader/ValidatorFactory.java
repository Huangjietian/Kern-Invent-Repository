package cn.kerninventor.tools.poibox.data.tabulation.reader;

import javax.validation.Validation;

/**
 * @author Kern
 * @date 2020/5/25 10:50
 * @description
 */
public class ValidatorFactory {

    public static BeanValidator hibernateValidator(){
        javax.validation.ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        return new DefaultBeanValidator(validatorFactory.getValidator());
    }

}
