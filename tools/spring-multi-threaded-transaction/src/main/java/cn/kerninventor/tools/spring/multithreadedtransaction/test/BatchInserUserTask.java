package cn.kerninventor.tools.spring.multithreadedtransaction.test;

import cn.kerninventor.tools.spring.multithreadedtransaction.AsynTaskExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
public class BatchInserUserTask extends AsynTaskExecutor<List<User>> {

    @Resource
    private UserMapper userMapper;

    @Override
    public Object customTask(List<User> users) throws Exception {
        userMapper.saveBatch(users);
        return users.size();
    }
}
