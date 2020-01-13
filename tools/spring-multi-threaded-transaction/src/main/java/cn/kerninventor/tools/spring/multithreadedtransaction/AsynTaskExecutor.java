package cn.kerninventor.tools.spring.multithreadedtransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.stream.Collectors;

/**
 * @Title MuiltThreadTransactionDeamon
 * @ProjectName kerninventresp
 * @PackageName cn.kerninventor.tools.spring.multithreadedtransaction
 * @Author Kern
 * @Date 2020/1/6 14:48
 * @Description TODO
 */
@Component
public abstract class AsynTaskExecutor <T> {

    public abstract Object customTask(T t) throws Exception;

    @Transactional(propagation = Propagation.REQUIRES_NEW, timeout = 60)
    public TaskExecuteResult asynExecute(List<T> args) {
        if (args == null || args.size() == 0){
            return null;
        }
        TransactionAspectSupport.currentTransactionStatus();
        CountDownLatch mainCDL = new CountDownLatch(1);
        CountDownLatch branceCDL = new CountDownLatch(args.size());
        BlockingDeque<TaskExecuteResult> taskExecuteResults = new LinkedBlockingDeque<>(args.size());
        Rollback rollback = new Rollback(false);
        for (T t : args){
//            asynTaskExecutorBranches.execute(mainCDL,branceCDL,taskExecuteResults,rollback,this,t);
            new Thread(new AsynTaskExecuteRunner(mainCDL,branceCDL,taskExecuteResults,rollback,this,t)).start();
        }
        //等待分线程执行
        try {
            branceCDL.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        taskExecuteResults.forEach( e -> {
            if (e.getError()){
                rollback.setNeedRollback(true);
            }
        });
        //主线程执行完毕，回调分线程
        mainCDL.countDown();
        TaskExecuteResult executeResult = new TaskExecuteResult();
        if (rollback.getNeedRollback()){
            executeResult.setError(true);
            executeResult.setExcs(taskExecuteResults.stream().filter( e -> e.getExc() != null).map(e -> e.getExc()).collect(Collectors.toList()));
        } else {
            executeResult.setInvokeReturns(taskExecuteResults.stream().filter( e -> e.getInvokeReturn() != null).map(e -> e.getInvokeReturn()).collect(Collectors.toList()));
        }
        return executeResult;
    }

//    @Transactional(timeout = 30)
//    public void execute(CountDownLatch mainCDL,
//                           CountDownLatch branceCDL,
//                           BlockingDeque<TaskExecuteResult> taskExecuteResults,
//                           Rollback rollback,
//                           T t){
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                TaskExecuteResult executeResult = new TaskExecuteResult();
//                try {
//                    executeResult.setInvokeReturn(customTask(t));
//                } catch (Exception ex) {
//                    System.out.println(ex.getMessage());
//                    executeResult.setError(true);
//                    executeResult.setExc(ex);
//                }
//                taskExecuteResults.add(executeResult);
//                //计数， 等待其他子线程执行官完毕
//                branceCDL.countDown();
//                //子线程执行完毕，等待主线程回调
//                try {
//                    mainCDL.await();
//                } catch (InterruptedException ex) {
//                    ex.printStackTrace();
//                }
//                if (rollback.getNeedRollback()){
//                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//                    System.out.println("事务回滚");
//                } else {
//                    System.out.println("事务提交");
//                }
//            }
//        }).start();
//    }




}
