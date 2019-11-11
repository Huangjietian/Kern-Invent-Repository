package cn.kerninventor.tools.data.diagramer.proxy;

import cn.kerninventor.tools.data.diagramer.DataDiagramAble;
import cn.kerninventor.tools.data.diagramer.ToTreeDiagramAble;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Title: DDTProxyUtils
 * @ProjectName tools
 * @Version 1.1.0.RELEASE
 * @Description: TODO
 * @Author Kern
 * @Date 2019/11/11 10:29
 */
public class DDTProxyFactory {

    public static final String LINE_SEPARATOR = System.lineSeparator();
    public static final String PATH_SEPARATOR = ";";
    public static final String NAME_PROXY_SUFFIX = "$Proxy";

    private static Map<Class, DataDiagramProxy> proxyClassContainer = new HashMap<>();
    private static DataDiagramProxy putProxy(Class key, DataDiagramProxy value) {
        proxyClassContainer.put(key, value);
        return value;
    }

    public static DataDiagramProxy getProxy(DataDiagramAble target) throws NoSuchMethodException, IOException, ClassNotFoundException {
        DataDiagramProxy proxy = proxyClassContainer.get(target.getClass());
        if (proxy != null){
            return proxy;
        }
        if (target instanceof ToTreeDiagramAble){
            return putProxy(target.getClass(), DataTreeProxy.proxyGenerate((Class<ToTreeDiagramAble>) target.getClass()));
        }
        return null;
    }
}
