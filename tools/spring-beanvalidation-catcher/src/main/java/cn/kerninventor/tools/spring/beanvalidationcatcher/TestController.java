package cn.kerninventor.tools.spring.beanvalidationcatcher;

import org.springframework.stereotype.Controller;

/**
 * @Title: TestController
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.spring.beanvalidationcatcher
 * @Author Kern
 * @Date 2020/2/5 10:07
 * @Description: TODO
 */
@Controller
public class TestController {

    public void test(TestBO testBO){
        System.out.println("测试方法");
    }
}
