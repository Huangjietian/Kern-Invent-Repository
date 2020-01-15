package cn.kerninventor.tools.spring.multithreadedtransaction.test;

import cn.kerninventor.tools.spring.multithreadedtransaction.AysncTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Title SaveUserTask
 * @ProjectName kerninventresp
 * @PackageName cn.kerninventor.tools.spring.multithreadedtransaction.test
 * @Author Kern
 * @Date 2020/1/15 18:07
 * @Description TODO
 */
@Service
public class SaveUserTask implements AysncTask {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Object task(Object... args) {
        userMapper.saveBatch((List<User>) args[0]);
        return null;
    }
}
