package cn.kerninventor.tools.spring.multithreadedtransaction;

import java.util.List;

/**
 * @Title TaskExecuteResult
 * @ProjectName kerninventresp
 * @PackageName cn.kerninventor.tools.spring.multithreadedtransaction
 * @Author Kern
 * @Date 2020/1/6 14:53
 * @Description TODO
 */
public class AsynBatchTaskExecuteResult {

    private Boolean isError;
    private Exception exc;
    private List<Exception> excs;
    private List invokeReturns;
    private Object ntstatus;

    public AsynBatchTaskExecuteResult() {
        this.isError = false;
    }

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

    public List<Exception> getExcs() {
        return excs;
    }

    public void setExcs(List<Exception> excs) {
        this.excs = excs;
    }

    public List getInvokeReturns() {
        return invokeReturns;
    }

    public void setInvokeReturns(List invokeReturns) {
        this.invokeReturns = invokeReturns;
    }


}
