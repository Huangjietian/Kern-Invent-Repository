package cn.kerninventor.tools.spring.multithreadedtransaction;

import java.util.List;

/**
 * @Title AsynTask
 * @ProjectName kerninventresp
 * @PackageName cn.kerninventor.tools.spring.multithreadedtransaction.test
 * @Author Kern
 * @Date 2020/1/14 11:12
 * @Description TODO
 */
public interface AsynBatchTask<T> {

    Object execute(List<T> tList);
}
