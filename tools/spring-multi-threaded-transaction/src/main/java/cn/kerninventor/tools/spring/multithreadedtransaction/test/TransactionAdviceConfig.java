package cn.kerninventor.tools.spring.multithreadedtransaction.test;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 全局事务配置
 * @Author yangaj
 * @Date 2019/4/12
 * @Version 1.0.0
 **/
@Aspect
@Configuration
public class TransactionAdviceConfig {

    private static final String AOP_POINTCUT_EXPRESSION = "execution (* cn.kerninventor.tools.spring.multithreadedtransaction..*(..))";

    private static final int TX_METHOD_TIMEOUT = -1;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public TransactionInterceptor txAdvice() {

        RuleBasedTransactionAttribute txAttr_REQUIRED = new RuleBasedTransactionAttribute();
        RollbackRuleAttribute error = new RollbackRuleAttribute(Exception.class);
        List<RollbackRuleAttribute> rollbackRuleAttributes = new ArrayList<>();
        rollbackRuleAttributes.add(error);
        txAttr_REQUIRED.setRollbackRules(rollbackRuleAttributes);
        txAttr_REQUIRED.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        txAttr_REQUIRED.setTimeout(TX_METHOD_TIMEOUT);

        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        //增删改开启事务，查询不开启事务，非数据库操作不开启事务
        //定义通用度较高的方法，其余用注解即可
        source.addTransactionalMethod("save*", txAttr_REQUIRED);
        source.addTransactionalMethod("insert*", txAttr_REQUIRED);
        source.addTransactionalMethod("update*", txAttr_REQUIRED);
        source.addTransactionalMethod("delete*", txAttr_REQUIRED);

        return new TransactionInterceptor(transactionManager, source);
    }

    @Bean
    public Advisor txAdviceAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }

}
