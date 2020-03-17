package cn.kerninventor.tools.spring.beanvalidationcatcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Title: App
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.spring.beanvalidationcatcher
 * @Author Kern
 * @Date 2020/2/5 10:22
 * @Description: TODO
 */
@SpringBootApplication
@EnableAspectJAutoProxy
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
