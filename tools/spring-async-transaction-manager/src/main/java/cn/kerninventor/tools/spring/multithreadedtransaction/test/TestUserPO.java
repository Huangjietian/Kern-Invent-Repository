package cn.kerninventor.tools.spring.multithreadedtransaction.test;

/**
 * @Title User
 * @ProjectName kerninventresp
 * @PackageName cn.kerninventor.tools.spring.multithreadedtransaction
 * @Author Kern
 * @Date 2020/1/10 15:29
 * @Description TODO
 */
public class TestUserPO {

    private Long id;

    public TestUserPO() {
        id = System.nanoTime();
    }

    public Long getId() {
        return id;
    }
}
