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

    private Boolean isRollback;

    public Rollback(Boolean isRollback) {
        this.isRollback = isRollback;
    }

    public Boolean isRollback() {
        return isRollback;
    }

    public void setRollback(Boolean rollback) {
        isRollback = rollback;
    }
}
