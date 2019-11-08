package cn.kerninventor.tools.dataplanter.impl;

import cn.kerninventor.tools.dataplanter.InheritedDataTree;
import cn.kerninventor.tools.dataplanter.ToTree;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @Title: ToTreeServer
 * @ProjectName swms
 * @Version 1.0.SNAPSHOT
 * @Author Kern
 * @Date 2019/11/8 11:29
 */
public class ToTreeServer <T extends InheritedDataTree> implements ToTree<T> {

    private T top;
    private Class<T> tClass;
    private Method upperNodeMethod;
    private Method lowerNodeMethod;
    private List<T> tree;

    private ToTreeServer() {
    }

    protected ToTreeServer(T top, Class<T> tClass, Method upperNodeMethod, Method lowerNodeMethod) {
        this.top = top;
        this.tClass = tClass;
        this.upperNodeMethod = upperNodeMethod;
        this.lowerNodeMethod = lowerNodeMethod;
        tree = new ArrayList<>();
        if (Objects.nonNull(top)){
            tree.add(top);
        }
    }

    @Override
    public List<T> toTree(List<T> sends) throws NoSuchMethodException {
        /**
         * When parameter's type is java.util.Arrays.ArrayList,
         * this method will throw UnsupportedOperationException.
         * So, author add this code to make sure the list supports add, remove, and set operations
         */
        sends = new ArrayList<>(sends);

        Iterator<T> iterator = sends.iterator();
        if (tree.size() > 0){
            recursion(tree, sends);
        } else {
            while (iterator.hasNext()) {
                T t = iterator.next();
                Object lowerNode = invoke(t, lowerNodeMethod);
                if (Objects.isNull(lowerNode)){
                    tree.add(t);
                    iterator.remove();
                }
            }
            recursion(tree, sends);
        }
        return tree;
    }

    private void recursion (Collection<T> trunk, Collection<T> branch) throws NoSuchMethodException {
        for (T t : trunk){
            Iterator<T> iterator = branch.iterator();
            while (iterator.hasNext()){
                T subT = iterator.next();
                if (invoke(t, upperNodeMethod).equals(invoke(subT, lowerNodeMethod))){
                    t.add(subT);
                    iterator.remove();
                }
            }
            if (t.getBranches().size() > 0){
                recursion(t.getBranches(), branch);
            }
        }
    }

    private Object invoke(Object obj, Method method) throws NoSuchMethodException {
        try {
            return method.invoke(obj);
        } catch (Exception e) {
            throw new NoSuchMethodException("Incorrect data input : get Value method invoke exception");
        }
    }
}
