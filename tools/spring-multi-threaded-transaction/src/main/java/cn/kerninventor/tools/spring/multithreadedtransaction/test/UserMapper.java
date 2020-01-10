package cn.kerninventor.tools.spring.multithreadedtransaction.test;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

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
public interface UserMapper {

    void saveBatch(@Param("list") List<User> user);
}
