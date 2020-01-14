package cn.kerninventor.tools.spring.multithreadedtransaction.test;

import cn.kerninventor.tools.spring.multithreadedtransaction.AsynBatchTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Title BatchInserUserTask
 * @ProjectName kerninventresp
 * @PackageName cn.kerninventor.tools.spring.multithreadedtransaction
 * @Author Kern
 * @Date 2020/1/10 15:28
 * @Description TODO
 */
@Component
public class BatchInserUserBatchTask extends AsynBatchTaskExecutor<List<User>> {

    @Resource
    private UserMapper userMapper;

    @Override
    public Object customTask(List<User> users) throws Exception {
        userMapper.saveBatch(users);
        return users.size();
    }
}
