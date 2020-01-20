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
public class AsyncTasksExecutor {

    private DataSourceTransactionManager transactionManager;

    public AsyncTasksExecutor(ApplicationContext applicationContext) {
        this.transactionManager = Objects.requireNonNull(
                Objects.requireNonNull(applicationContext, "Invalid spring application context.").getBean(DataSourceTransactionManager.class),
                "Can't found DataSourceTransactionManager from ApplicationContext.");
    }

    public AsyncTasksExecutor(DataSourceTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    //添加任务 executor.addedittask(task1, args).addedittask(task2, args)...;
    private List<AsyncTaskCoverer> asyncTaskCoverers = new ArrayList<>();
    public AsyncTasksExecutor addedittask(AsyncTask asyncTask, Object... args) {
        asyncTaskCoverers.add(new AsyncTaskCoverer(asyncTask,args));
        return this;
    }

    public AsyncTasksExecutor cleanTasks(){
        asyncTaskCoverers.clear();
        return this;
    }

    public ExecuteResultBlockingDeque execute() {
        if (asyncTaskCoverers.size() == 0){
            return new ExecuteResultBlockingDeque(new LinkedBlockingDeque<ExecuteResult>());
        }
        CountDownLatch mainCDL = new CountDownLatch(1);
        CountDownLatch branceCDL = new CountDownLatch(asyncTaskCoverers.size());
        BlockingDeque<ExecuteResult> executeResults = new LinkedBlockingDeque<>(asyncTaskCoverers.size());
        Rollback rollback = new Rollback(false);
        new ArrayList<AsyncTaskCoverer>(asyncTaskCoverers).forEach( e -> {
                new AsyncTaskThread(
                        transactionManager,
                        mainCDL,
                        branceCDL,
                        executeResults,
                        rollback,
                        e.getAsyncTask(),
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
        return new ExecuteResultBlockingDeque(executeResults);
    }

    private class AsyncTaskCoverer {

        private AsyncTask asyncTask;
        private Object[] args;

        public AsyncTaskCoverer(AsyncTask asyncTask, Object[] args) {
            this.asyncTask = asyncTask;
            this.args = args;
        }

        public AsyncTask getAsyncTask() {
            return asyncTask;
        }

        public void setAsyncTask(AsyncTask asyncTask) {
            this.asyncTask = asyncTask;
        }

        public Object[] getArgs() {
            return args;
        }

        public void setArgs(Object[] args) {
            this.args = args;
        }
    }



}
