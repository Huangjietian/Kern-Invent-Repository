package cn.kerninventor.tools.spring.multithreadedtransaction;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.util.List;
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

    public boolean hasError(){
        return executeResults.stream().anyMatch(e -> e.isError());
    }

    public void throwingWhenError() throws RuntimeException {
        if (hasError()){
            Set<String> execMsgSet = executeResults.stream().filter(e -> e.getExc() != null).map(e -> e.getExc().getMessage() + System.lineSeparator()).collect(Collectors.toSet());
            throw new RuntimeException(execMsgSet.toString());
        }
    }
}
