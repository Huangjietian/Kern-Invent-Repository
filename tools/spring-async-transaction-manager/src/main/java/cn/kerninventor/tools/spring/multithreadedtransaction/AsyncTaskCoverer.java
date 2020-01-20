package cn.kerninventor.tools.spring.multithreadedtransaction;

/**
 * @Title AsyncTaskCoverer
 * @ProjectName kerninventresp
 * @PackageName cn.kerninventor.tools.spring.multithreadedtransaction
 * @Author Kern
 * @Date 2020/1/20 11:56
 * @Description TODO
 */
public class AsyncTaskCoverer {

    private int taskId;
    private AsyncTask asyncTask;
    private Object[] args;

    public AsyncTaskCoverer(int taskId, AsyncTask asyncTask, Object[] args) {
        this.taskId = taskId;
        this.asyncTask = asyncTask;
        this.args = args;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
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
