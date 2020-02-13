package cn.kerninventor.tools.spring.multithreadedtransaction;

import cn.kerninventor.tools.spring.multithreadedtransaction.test.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Title AppTest
 * @ProjectName kerninventresp
 * @PackageName cn.kerninventor.tools.spring.multithreadedtransaction
 * @Author Kern
 * @Date 2020/1/10 15:36
 * @Description TODO
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {App.class})
public class AppTest {

    @Autowired
    private ApplicationContext applicationContext;
    @Resource
    private TestUserMapper testUserMapper;
    @Test
    public void testBatchInsert() {
        AsyncTasksExecutor executor = new AsyncTasksExecutor(applicationContext, true);

        //假设我们把一个User数据进行分组
        for (int i = 0 ; i < 10 ; i ++){
            List<TestUserPO> users = new ArrayList<>();
            for (int j = 0 ; j < 100 ; j ++){
                users.add(new TestUserPO());
            }
            //添加错误数据
//            users.add(users.get(0));
            //lambda表达式
            executor.addeditTask(args -> { testUserMapper.saveBatch((List<TestUserPO>) args[0]);return null; }, users);
        }
        ExecuteResultBlockingDeque blockingDeque = executor.execute().throwingWhenError();
    }

    @Resource
    private TestAreaMapper areaMapper;
    @Test
    public void testDiffFind() {
        AsyncTasksExecutor executor = new AsyncTasksExecutor(applicationContext, false);
        executor.addeditTask(1, args ->  areaMapper.findTestCountries(), null)
                .addeditTask(2, args ->  areaMapper.findTestProvinces(), null);
        ExecuteResultBlockingDeque blockingDeque = executor.execute().throwingWhenError();
        List<TestCountryPO> countries = (List<TestCountryPO>) blockingDeque.getExecuteResult(1).getResult();
        System.out.println("countries information : " + countries.toString());
        List<TestProvincePO> provinces = (List<TestProvincePO>) blockingDeque.getExecuteResult(2).getResult();
        System.out.println("provinces information : " + provinces.toString());
    }
}
