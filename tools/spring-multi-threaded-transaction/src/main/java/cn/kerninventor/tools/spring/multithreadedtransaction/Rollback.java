package cn.kerninventor.tools.spring.multithreadedtransaction;

/**
 * @Title SyncRollback
 * @ProjectName eam-common-parent
 * @PackageName com.eam.sync
 * @Author Kern
 * @Date 2019/12/26 17:24
 * @Description TODO
 */
public class Rollback {

    private Boolean needRollback;

    public Rollback(Boolean needRollback) {
        this.needRollback = needRollback;
    }

    public Boolean getNeedRollback() {
        return needRollback;
    }

    public void setNeedRollback(Boolean needRollback) {
        this.needRollback = needRollback;
    }
}
