package cn.kerninventor.tools.spring.beanvalidationcatcher;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Title: TestApp
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.spring.beanvalidationcatcher
 * @Author Kern
 * @Date 2020/2/5 10:22
 * @Description: TODO
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {App.class})
public class TestApp {

    @Autowired
    private TestController testController;

    @Test
    public void name() {
        TestBO testBO = new TestBO();
        testController.test(testBO);

    }
}
