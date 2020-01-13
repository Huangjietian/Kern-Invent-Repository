package cn.kerninventor.tools.spring.multithreadedtransaction;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.CountDownLatch;

/**
 * @Title AsynTaskExecuteRunner
 * @ProjectName kerninventresp
 * @PackageName cn.kerninventor.tools.spring.multithreadedtransaction
 * @Author Kern
 * @Date 2020/1/13 18:00
 * @Description TODO
 */
public class AsynTaskExecuteRunner <E extends AsynTaskExecutor<D>, D>implements Runnable {

    private CountDownLatch mainCDL;
    private CountDownLatch branceCDL;
    private BlockingDeque<TaskExecuteResult> taskExecuteResults;
    private Rollback rollback;
    private E executor;
    private D data;

    public AsynTaskExecuteRunner(CountDownLatch mainCDL, CountDownLatch branceCDL, BlockingDeque<TaskExecuteResult> taskExecuteResults, Rollback rollback, E executor, D data) {
        this.mainCDL = mainCDL;
        this.branceCDL = branceCDL;
        this.taskExecuteResults = taskExecuteResults;
        this.rollback = rollback;
        this.executor = executor;
        this.data = data;
    }

    @Override
    public void run() {
        TaskExecuteResult executeResult = new TaskExecuteResult();
        try {
            executeResult.setInvokeReturn(executor.customTask(data));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            executeResult.setError(true);
            executeResult.setExc(ex);
        }
        taskExecuteResults.add(executeResult);
        //计数， 等待其他子线程执行官完毕
        branceCDL.countDown();
        //子线程执行完毕，等待主线程回调
        try {
            mainCDL.await();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        if (rollback.getNeedRollback()){
            System.out.println("事务回滚");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        } else {
            System.out.println("事务提交");
        }
    }
}
