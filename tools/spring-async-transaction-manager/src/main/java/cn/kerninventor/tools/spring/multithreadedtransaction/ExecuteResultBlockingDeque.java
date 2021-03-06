package cn.kerninventor.tools.spring.multithreadedtransaction;

import java.util.Set;
import java.util.concurrent.BlockingDeque;
import java.util.stream.Collectors;

/**
 * @Title ExecuteResultBlockingDeque
 * @ProjectName kerninventresp
 * @PackageName cn.kerninventor.tools.spring.multithreadedtransaction
 * @Author Kern
 * @Date 2020/1/19 10:07
 * @Description TODO
 */
public class ExecuteResultBlockingDeque {

    private BlockingDeque<ExecuteResult> executeResults;

    public ExecuteResultBlockingDeque(BlockingDeque<ExecuteResult> executeResults) {
        this.executeResults = executeResults;
    }

    public ExecuteResult getExecuteResult(int taskId) {
        ExecuteResult executeResult = null;
        for (ExecuteResult e : executeResults){
            if (e.taskId == taskId)
                executeResult = e;
        }
        return executeResult;
    }

    public boolean hasError(){
        return executeResults.stream().anyMatch(e -> e.isError());
    }

    public ExecuteResultBlockingDeque throwingWhenError() throws RuntimeException {
        if (hasError()){
            Set<String> execMsgSet = executeResults.stream().filter(e -> e.getExc() != null).map(e -> e.getExc().getMessage() + System.lineSeparator()).collect(Collectors.toSet());
            throw new RuntimeException(execMsgSet.toString());
        }
        return this;
    }
}
