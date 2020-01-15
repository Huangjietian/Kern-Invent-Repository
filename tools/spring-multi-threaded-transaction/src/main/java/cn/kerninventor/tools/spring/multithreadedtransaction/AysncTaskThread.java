package cn.kerninventor.tools.spring.multithreadedtransaction;

import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.CountDownLatch;

/**
 * @Title AsyncTaskRunner
 * @ProjectName kerninventresp
 * @PackageName cn.kerninventor.tools.spring.multithreadedtransaction
 * @Author Kern
 * @Date 2020/1/15 14:21
 * @Description TODO
 */
public class AysncTaskThread extends Thread {

    private ApplicationContext applicationContext;
    private CountDownLatch mainCDL;
    private CountDownLatch branceCDL;
    private BlockingDeque<ExecuteResult> executeResults;
    private Rollback rollback;
    private AysncTask aysncTask;
    private Object[] args;

    AysncTaskThread(ApplicationContext applicationContext, CountDownLatch mainCDL, CountDownLatch branceCDL, BlockingDeque<ExecuteResult> executeResults, Rollback rollback, AysncTask aysncTask, Object[] args) {
        this.applicationContext = applicationContext;
        this.mainCDL = mainCDL;
        this.branceCDL = branceCDL;
        this.executeResults = executeResults;
        this.rollback = rollback;
        this.aysncTask = aysncTask;
        this.args = args;
    }

    private void invoke(){
        DataSourceTransactionManager transactionManager = applicationContext.getBean(DataSourceTransactionManager.class);
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);

        ExecuteResult executeResult = new ExecuteResult();
        try {
            executeResult.setResult(aysncTask.task(args));
        } catch (Exception ex) {
            System.out.println("发生异常啦" + ex.getMessage());
            executeResult.setError();
            executeResult.setExc(ex);
        }
        executeResults.add(executeResult);
        //计数， 等待其他子线程执行官完毕
        branceCDL.countDown();
        //子线程执行完毕，等待主线程回调
        try {
            mainCDL.await();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        if (rollback.isRollback()){
            System.out.println("事务回滚");
            transactionManager.rollback(transactionStatus);
        } else {
            System.out.println("事务提交");
            transactionManager.commit(transactionStatus);
        }
    }

    @Override
    public void run() {
        invoke();
    }
}
