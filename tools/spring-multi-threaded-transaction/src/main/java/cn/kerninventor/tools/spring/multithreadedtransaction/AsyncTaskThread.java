package cn.kerninventor.tools.spring.multithreadedtransaction;

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
public class AsyncTaskThread extends Thread {

    private DataSourceTransactionManager transactionManager;
    private CountDownLatch mainCDL;
    private CountDownLatch branceCDL;
    private BlockingDeque<ExecuteResult> executeResults;
    private Rollback rollback;
    private AsyncTask asyncTask;
    private Object[] args;

    AsyncTaskThread(DataSourceTransactionManager transactionManager, CountDownLatch mainCDL, CountDownLatch branceCDL, BlockingDeque<ExecuteResult> executeResults, Rollback rollback, AsyncTask asyncTask, Object[] args) {
        this.transactionManager = transactionManager;
        this.mainCDL = mainCDL;
        this.branceCDL = branceCDL;
        this.executeResults = executeResults;
        this.rollback = rollback;
        this.asyncTask = asyncTask;
        this.args = args;
    }

    private void invoke(){
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        transactionDefinition.setTimeout(30);
        transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);

        ExecuteResult executeResult = new ExecuteResult();
        try {
            executeResult.setResult(asyncTask.task(args));
        } catch (Exception ex) {
            System.out.println("发生异常啦" + ex.getMessage());
            executeResult.setError();
            executeResult.setExc(ex);
        }
        executeResults.add(executeResult);
        branceCDL.countDown();
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
