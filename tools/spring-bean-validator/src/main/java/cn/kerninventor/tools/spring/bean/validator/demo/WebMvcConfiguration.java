package cn.kerninventor.tools.spring.bean.validator.demo;

import cn.kerninventor.tools.spring.bean.validator.BeanValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Kern
 * @date 2020/5/12 17:52
 * @description
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Bean
    public BeanValidator BeanValidator(){
        return new BeanValidator();
    }

}
