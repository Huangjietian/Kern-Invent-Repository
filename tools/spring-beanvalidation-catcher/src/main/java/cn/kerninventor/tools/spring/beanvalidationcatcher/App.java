package cn.kerninventor.tools.spring.beanvalidationcatcher;

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
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableAspectJAutoProxy
public class App {
}
