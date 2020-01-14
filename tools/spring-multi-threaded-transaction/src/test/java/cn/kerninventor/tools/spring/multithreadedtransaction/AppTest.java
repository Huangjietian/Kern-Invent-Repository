package cn.kerninventor.tools.spring.multithreadedtransaction;

import cn.kerninventor.tools.spring.multithreadedtransaction.test.BatchInserUserBatchTask;
import cn.kerninventor.tools.spring.multithreadedtransaction.test.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

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
    private BatchInserUserBatchTask batchInserUserTask;
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void test() {
        //假设我们把一个User数据进行分组
        List<List<User>> userLists = new ArrayList<>();
        for (int i = 0 ; i < 10 ; i ++){
            List<User> users = new ArrayList<>();
            for (int j = 0 ; j < 100 ; j ++){
                users.add(new User());
            }
            userLists.add(users);
        }
        userLists.get(0).add(userLists.get(0).get(0));
        batchInserUserTask.asynExecute(userLists);
    }
}
