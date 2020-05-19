package cn.kerninventor.tools.spring.bean.validator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Kern
 * @date 2020/5/19 10:18
 * @description
 */
@Configuration
public class BeanValidatorImporter {

    @Bean
    public BeanValidator beanValidator(){
        return new BeanValidator();
    }
}
