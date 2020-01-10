package cn.kerninventor.tools.spring.multithreadedtransaction.test;

/**
 * @Title User
 * @ProjectName kerninventresp
 * @PackageName cn.kerninventor.tools.spring.multithreadedtransaction
 * @Author Kern
 * @Date 2020/1/10 15:29
 * @Description TODO
 */
public class User {

    private Long id;

    public User() {
        id = System.nanoTime();
    }

    public Long getId() {
        return id;
    }
}
