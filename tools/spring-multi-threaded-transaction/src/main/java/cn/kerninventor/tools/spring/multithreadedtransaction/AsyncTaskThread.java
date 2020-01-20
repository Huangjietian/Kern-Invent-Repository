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
    private AsyncTaskCoverer taskCoverer;


    public AsyncTaskThread(DataSourceTransactionManager transactionManager, CountDownLatch mainCDL, CountDownLatch branceCDL, BlockingDeque<ExecuteResult> executeResults, Rollback rollback, AsyncTaskCoverer taskCoverer) {
        this.transactionManager = transactionManager;
        this.mainCDL = mainCDL;
        this.branceCDL = branceCDL;
        this.executeResults = executeResults;
        this.rollback = rollback;
        this.taskCoverer = taskCoverer;
    }

    private void invoke(){
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        transactionDefinition.setTimeout(30);
        transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);

        ExecuteResult executeResult = new ExecuteResult(taskCoverer.getTaskId());
        try {
            executeResult.setResult(taskCoverer.getAsyncTask().task(taskCoverer.getArgs()));
        } catch (Exception ex) {
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
            transactionManager.rollback(transactionStatus);
        } else {
            transactionManager.commit(transactionStatus);
        }
    }

    @Override
    public void run() {
        invoke();
    }
}
