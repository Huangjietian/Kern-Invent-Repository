package cn.kerninventor.tools.spring.multithreadedtransaction.test;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Title UserService
 * @ProjectName kerninventresp
 * @PackageName cn.kerninventor.tools.spring.multithreadedtransaction
 * @Author Kern
 * @Date 2020/1/10 15:31
 * @Description TODO
 */
@Mapper
public interface TestUserMapper {

    void saveBatch(@Param("list") List<TestUserPO> testUserPO);
}
