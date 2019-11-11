package cn.kerninventor.tools.data.diagramer.proxy;

import cn.kerninventor.tools.data.diagramer.DataDiagramAble;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;

/**
 * @Title: DataProxy
 * @ProjectName tools
 * @Version 1.1.0.RELEASE
 * @Description: TODO
 * @Author Kern
 * @Date 2019/11/11 17:40
 */
public interface DataDiagramProxy <T extends DataDiagramAble> {

    public static final String LINE_SEPARATOR = System.lineSeparator();
    public static final String PATH_SEPARATOR = ";";
    public static final String NAME_PROXY_SUFFIX = "$Proxy";

    T newInstance(T targetObj) throws IllegalAccessException, InstantiationException;

    List<T> newInstance(List<T> targetObjs);

    Object invoke(Object sign,T t, Object... parameters) throws InvocationTargetException, IllegalAccessException;
}
