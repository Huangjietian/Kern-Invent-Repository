package cn.kerninventor.tools.spring.multithreadedtransaction;


import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @Title MuiltThreadTransactionDeamon
 * @ProjectName kerninventresp
 * @PackageName cn.kerninventor.tools.spring.multithreadedtransaction
 * @Author Kern
 * @Date 2020/1/6 14:48
 * @Description TODO
 */
public class AysncTasksExecutor {

    private DataSourceTransactionManager transactionManager;

    public AysncTasksExecutor(ApplicationContext applicationContext) {
        this.transactionManager = Objects.requireNonNull(
                Objects.requireNonNull(applicationContext, "Invalid spring application context.").getBean(DataSourceTransactionManager.class),
                "Can't found DataSourceTransactionManager from ApplicationContext.");
    }

    //添加任务 executor.addedittask(task1, args).addedittask(task2, args)...;
    private List<AsyncTaskCoverer> asyncTaskCoverers = new ArrayList<>();
    public AysncTasksExecutor addedittask(AysncTask aysncTask, Object... args) {
        asyncTaskCoverers.add(new AsyncTaskCoverer(aysncTask,args));
        return this;
    }

    public BlockingDeque<ExecuteResult> execute() {
        if (asyncTaskCoverers.size() == 0){
            new LinkedBlockingDeque<>(0);
        }
        CountDownLatch mainCDL = new CountDownLatch(1);
        CountDownLatch branceCDL = new CountDownLatch(asyncTaskCoverers.size());
        BlockingDeque<ExecuteResult> executeResults = new LinkedBlockingDeque<>(asyncTaskCoverers.size());
        Rollback rollback = new Rollback(false);
        new ArrayList<AsyncTaskCoverer>(asyncTaskCoverers).forEach( e -> {
                new AysncTaskThread(
                        transactionManager,
                        mainCDL,
                        branceCDL,
                        executeResults,
                        rollback,
                        e.getAysncTask(),
                        e.getArgs()
                ).start();
           }
        );
        try {
            branceCDL.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executeResults.forEach(e -> {
            if (e.isError()){
                rollback.setRollback(true);
            }
        });
        mainCDL.countDown();
        return executeResults;
    }

    private class AsyncTaskCoverer {

        private AysncTask aysncTask;
        private Object[] args;

        public AsyncTaskCoverer(AysncTask aysncTask, Object[] args) {
            this.aysncTask = aysncTask;
            this.args = args;
        }

        public AysncTask getAysncTask() {
            return aysncTask;
        }

        public void setAysncTask(AysncTask aysncTask) {
            this.aysncTask = aysncTask;
        }

        public Object[] getArgs() {
            return args;
        }

        public void setArgs(Object[] args) {
            this.args = args;
        }
    }



}
