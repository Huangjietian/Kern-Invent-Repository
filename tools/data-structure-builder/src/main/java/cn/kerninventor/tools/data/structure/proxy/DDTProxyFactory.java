package cn.kerninventor.tools.data.structure.proxy;

import cn.kerninventor.tools.data.structure.Tree;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Kern
 * @date 2019/11/11 10:29
 */
public class DDTProxyFactory {

    private static final String TAG_TREE = "&TREE_";

    public static final String LINE_SEPARATOR = System.lineSeparator();
    public static final String PATH_SEPARATOR = ";";
    public static final String NAME_PROXY_SUFFIX = "$Proxy";

    private static Map<String, DataStructureProxy> proxyClassContainer = new HashMap<>();


    private static DataStructureProxy putProxy(String tag, Class key, DataStructureProxy value) {
        proxyClassContainer.put(tag + key.getName(), value);
        return value;
    }


    /**
     * @param target
     * @return
     * @throws NoSuchMethodException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static DataStructureProxy getTreeProxy(Tree target) throws NoSuchMethodException, IOException, ClassNotFoundException {
        DataStructureProxy proxy = proxyClassContainer.get(target.getClass());
        if (proxy != null){
            return proxy;
        }
        if (target instanceof Tree){
            return putProxy(TAG_TREE, target.getClass(), DataTreeProxy.proxyGenerate((Class<Tree>) target.getClass()));
        }
        return null;
    }
}
