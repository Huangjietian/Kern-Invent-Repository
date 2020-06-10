package cn.kerninventor.tools.spring.bean.validator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <h1>中文描述</h1>
 * <p>
 *     配置类，引入{@code BeanValidator}类到spring bean容器中。
 * </p>
 * @author Kern
 * @version 1.0
 */
@Configuration
public class BeanValidatorImporter {

    @Bean
    public BeanValidator beanValidator(){
        return new BeanValidator();
    }
}
