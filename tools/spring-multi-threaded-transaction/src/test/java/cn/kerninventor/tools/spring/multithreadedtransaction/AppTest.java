package cn.kerninventor.tools.spring.multithreadedtransaction;

import cn.kerninventor.tools.spring.multithreadedtransaction.test.SaveUserTask;
import cn.kerninventor.tools.spring.multithreadedtransaction.test.User;
import cn.kerninventor.tools.spring.multithreadedtransaction.test.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingDeque;

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
    private SaveUserTask saveUserTask;
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void test() {

        AysncTasksExecutor executor = new AysncTasksExecutor(applicationContext);

        //假设我们把一个User数据进行分组
        List<List<User>> userLists = new ArrayList<>();
        for (int i = 0 ; i < 10 ; i ++){
            List<User> users = new ArrayList<>();
            for (int j = 0 ; j < 100 ; j ++){
                users.add(new User());
            }
//            users.add(users.get(0));
            executor.addedittask(saveUserTask, users);
        }
        BlockingDeque<ExecuteResult> blockingDeque = executor.execute();
    }
}
