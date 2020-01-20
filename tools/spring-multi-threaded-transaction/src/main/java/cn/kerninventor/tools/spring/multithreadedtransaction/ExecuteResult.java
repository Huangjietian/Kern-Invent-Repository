package cn.kerninventor.tools.spring.multithreadedtransaction;


/**
 * @Title TaskExecuteResult
 * @ProjectName kerninventresp
 * @PackageName cn.kerninventor.tools.spring.multithreadedtransaction
 * @Author Kern
 * @Date 2020/1/6 14:53
 * @Description TODO
 */
public class ExecuteResult {

    public ExecuteResult(int taskId) {
        this.isError = false;
        this.taskId = taskId;
    }

    int taskId;
    private Boolean isError;
    private Exception exc;
    private Object result;

    public Boolean isError() {
        return isError;
    }

    public void setError() {
        isError = true;
    }

    public Exception getExc() {
        return exc;
    }

    public void setExc(Exception exc) {
        this.exc = exc;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
