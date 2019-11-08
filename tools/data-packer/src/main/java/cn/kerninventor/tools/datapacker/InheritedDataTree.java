package cn.kerninventor.tools.datapacker;

import com.alibaba.fastjson.annotation.JSONField;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * @Title: DataTree
 * @ProjectName swms
 * @Version 1.0.SNAPSHOT
 * @Author Kern
 * @Date 2019/11/8 10:47
 */
public abstract class InheritedDataTree<T extends InheritedDataTree> {

    private String branchesName;

    public abstract String getBranchesName();

    @JSONField
    private Collection<T> branches ;

    public InheritedDataTree() {
        branches = new ArrayList<>();
        String bName = getBranchesName();
        branchesName = bName == null || "".equals(bName.trim()) ? "branches" : bName;
        try {
            setBranchesName();
        } catch (Exception e) {
            throw new RuntimeException("Internal server error!" , e);
        }
    }

    public Collection<T> getBranches() {
        return branches;
    }

    public void add(T t){
        branches.add(t);
    }

    private void setBranchesName() throws NoSuchFieldException, IllegalAccessException {
        Field branchesField = this.getClass().getSuperclass().getDeclaredField("branches");
        JSONField jsonField = branchesField.getAnnotation(JSONField.class);
        InvocationHandler invocationHandler = Proxy.getInvocationHandler(jsonField);
        Field value = invocationHandler.getClass().getDeclaredField("memberValues");
        value.setAccessible(true);
        Map<String, Object> memberValues = (Map<String, Object>)value.get(invocationHandler);
        memberValues.put("name", branchesName);
        value.set(invocationHandler, memberValues);
    }
}
