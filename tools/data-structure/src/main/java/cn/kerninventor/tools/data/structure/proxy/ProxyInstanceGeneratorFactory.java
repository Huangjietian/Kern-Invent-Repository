package cn.kerninventor.tools.data.structure.proxy;

import cn.kerninventor.tools.data.structure.Tree;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Kern
 * @date 2019/11/11 10:29
 */
public class ProxyInstanceGeneratorFactory {

    static final String TAG_TREE = "&TREE_";

    private static Map<String, ProxyInstanceGenerator> proxyClassContainer = new ConcurrentHashMap<>();

    public static long getTagNumbers(final String tag) {
        return proxyClassContainer.keySet().stream().filter(e -> e.contains(tag)).count();
    }

    private static <T extends ProxyInstanceGenerator> T putProxy(String tag, Class key, T value) {
        proxyClassContainer.put(tag + key.getName(), value);
        return value;
    }

    public static TreeProxyInstanceGenerator getTreeProxy(Class<? extends Tree> clazz)  {
        ProxyInstanceGenerator proxy = proxyClassContainer.get(TAG_TREE+ clazz.getName());
        if (proxy == null){
            TreeProxyInstanceGenerator dataTreeProxy = null;
            try {
                dataTreeProxy = TreeProxyInstanceGenerator.proxyGenerate(clazz);
                return dataTreeProxy;
            } catch (Exception e) {
                throw new IllegalArgumentException("The proxy object could not be created!"+ e.getMessage(), e);
            } finally {
                putProxy(TAG_TREE, clazz, dataTreeProxy);
            }
        }
        return (TreeProxyInstanceGenerator) proxy;
    }
}
