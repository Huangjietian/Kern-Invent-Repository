package cn.kerninventor.tools.data.structure.proxy;

import cn.kerninventor.tools.data.structure.DataStructure;
import cn.kerninventor.tools.data.structure.TreeStructureAble;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Kern
 * @date 2019/11/11 10:29
 */
public class DDTProxyFactory {

    public static final String LINE_SEPARATOR = System.lineSeparator();
    public static final String PATH_SEPARATOR = ";";
    public static final String NAME_PROXY_SUFFIX = "$Proxy";

    private static Map<Class, DataStructureProxy> proxyClassContainer = new HashMap<>();
    private static DataStructureProxy putProxy(Class key, DataStructureProxy value) {
        proxyClassContainer.put(key, value);
        return value;
    }

    /**
     * 正确的调用应该是识别target的类型，是树状图或者其他，自动识别并调用对应的代理生成服务。
     * @param target
     * @return
     * @throws NoSuchMethodException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static DataStructureProxy getProxy(DataStructure target) throws NoSuchMethodException, IOException, ClassNotFoundException {
        DataStructureProxy proxy = proxyClassContainer.get(target.getClass());
        if (proxy != null){
            return proxy;
        }
        if (target instanceof TreeStructureAble){
            return putProxy(target.getClass(), DataTreeProxy.proxyGenerate((Class<TreeStructureAble>) target.getClass()));
        }
        return null;
    }
}
