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
    private boolean automaticScheduling;
    private int automaticTaskId;

    public AsyncTasksExecutor(ApplicationContext applicationContext) {
        this.transactionManager = Objects.requireNonNull(
                Objects.requireNonNull(applicationContext, "Invalid spring application context.").getBean(DataSourceTransactionManager.class),
                "Can't found DataSourceTransactionManager from ApplicationContext.");
    }

    public AsyncTasksExecutor(ApplicationContext applicationContext, boolean automaticScheduling) {
        this.transactionManager = Objects.requireNonNull(
                Objects.requireNonNull(applicationContext, "Invalid spring application context.").getBean(DataSourceTransactionManager.class),
                "Can't found DataSourceTransactionManager from ApplicationContext.");
        this.automaticScheduling = automaticScheduling;
    }

    public AsyncTasksExecutor(DataSourceTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public AsyncTasksExecutor(DataSourceTransactionManager transactionManager, boolean automaticScheduling) {
        this.transactionManager = transactionManager;
        this.automaticScheduling = automaticScheduling;
    }

    //添加任务 executor.addedittask(1, task1, args).addedittask(2, task2, args)...;
    private List<AsyncTaskCoverer> asyncTaskCoverers = new ArrayList<>();
    public AsyncTasksExecutor addeditTask(AsyncTask asyncTask, Object... args) {
        if (!automaticScheduling) {
            throw new IllegalArgumentException("TaskId is required when tasks are not automatically sorted!");
        }
        asyncTaskCoverers.add(new AsyncTaskCoverer(automaticTaskId++, asyncTask, args));
        return this;
    }

    public AsyncTasksExecutor addeditTask(Integer taskId, AsyncTask asyncTask, Object... args) {
        if (automaticScheduling){
            asyncTaskCoverers.add(new AsyncTaskCoverer(automaticTaskId++, asyncTask, args));
            return this;
        }
        if (taskId == null) {
            throw new IllegalArgumentException("TaskId is required when tasks are not automatically sorted!");
        }
        if (asyncTaskCoverers.parallelStream().anyMatch(e -> taskId == e.getTaskId())){
            throw new IllegalArgumentException("TaskId cannot be repeated when tasks are not automatically sorted");
        }
        asyncTaskCoverers.add(new AsyncTaskCoverer(taskId, asyncTask,args));
        return this;
    }

    public AsyncTasksExecutor cleanTasks(){
        asyncTaskCoverers.clear();
        automaticTaskId = 0;
        return this;
    }

    public ExecuteResultBlockingDeque execute() {
        if (asyncTaskCoverers.size() == 0){
            return new ExecuteResultBlockingDeque(new LinkedBlockingDeque<ExecuteResult>());
        }
        CountDownLatch mainCDL = new CountDownLatch(1);
        CountDownLatch branceCDL = new CountDownLatch(asyncTaskCoverers.size());
        BlockingDeque<ExecuteResult> executeResults = new LinkedBlockingDeque<>(asyncTaskCoverers.size());
        Rollback rollback = new Rollback();
        new ArrayList<AsyncTaskCoverer>(asyncTaskCoverers).forEach( e -> {
                new AsyncTaskThread(
                        transactionManager,
                        mainCDL,
                        branceCDL,
                        executeResults,
                        rollback,
                        e
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


}
