package cn.kerninventor.tools.spring.multithreadedtransaction;


/**
 * @Title AysnTask
 * @ProjectName kerninventresp
 * @PackageName cn.kerninventor.tools.spring.multithreadedtransaction
 * @Author Kern
 * @Date 2020/1/15 10:54
 * @Description TODO
 */
@FunctionalInterface
public interface AsyncTask {

    Object task(Object... args);

}
