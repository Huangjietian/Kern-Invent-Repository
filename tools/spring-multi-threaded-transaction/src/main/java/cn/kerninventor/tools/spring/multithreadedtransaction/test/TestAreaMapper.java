package cn.kerninventor.tools.spring.multithreadedtransaction.test;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Title TestAreaMapper
 * @ProjectName kerninventresp
 * @PackageName cn.kerninventor.tools.spring.multithreadedtransaction.test
 * @Author Kern
 * @Date 2020/1/20 15:16
 * @Description TODO
 */
@Mapper
public interface TestAreaMapper {

    List<TestProvincePO> findTestProvinces();

    List<TestCountryPO> findTestCountries();
}
