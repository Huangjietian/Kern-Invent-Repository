package cn.kerninventor.tools.datapacker.impl;

import cn.kerninventor.tools.datapacker.InheritedDataTree;
import cn.kerninventor.tools.datapacker.DataTreePacker;
import cn.kerninventor.tools.datapacker.ToTree;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @Title: DataTreePlanterServer
 * @ProjectName swms
 * @Version 1.0.SNAPSHOT
 * @Author Kern
 * @Date 2019/11/8 11:12
 */
public class DataTreePackerServer<T extends InheritedDataTree> implements DataTreePacker<T> {

    private Class<T> tClass;

    @Override
    public ToTree<T> setNode(Class<T> tClass, String upperNodeName, String lowerNodeName, T topOne) throws NoSuchMethodException {
        this.tClass = Objects.requireNonNull(tClass, "Incorrect data input : The parameter tClass: Class not null!");
        Method upperMethod = getMethod(upperNodeName);
        validateTopOne(topOne, upperMethod);
        return new ToTreeServer<>(topOne, tClass, upperMethod, getMethod(lowerNodeName));
    }

    private Method getMethod(String fieldName) throws NoSuchMethodException {
        String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        return tClass.getDeclaredMethod(methodName);
    }

    private void validateTopOne(T topOne, Method method){
        if (Objects.nonNull(topOne)){
            Object object = null;
            try {
                object = method.invoke(topOne);
            } catch (Exception e) {
                throw new RuntimeException("Internal server error!");
            }
            Objects.requireNonNull(object, "Incorrect data input : topOne." + method.getName() + " returning is null!");
        }
    }
}
