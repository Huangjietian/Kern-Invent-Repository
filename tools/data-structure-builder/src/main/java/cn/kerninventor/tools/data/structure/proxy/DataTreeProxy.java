package cn.kerninventor.tools.data.structure.proxy;

import cn.kerninventor.tools.data.structure.TreeStructureAble;
import cn.kerninventor.tools.data.structure.proxy.util.ProxyComplier;
import cn.kerninventor.tools.data.structure.proxy.util.ProxyFileCreator;
import com.alibaba.fastjson.JSON;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author Kern
 * @date 2019/11/11 15:30
 */
public class DataTreeProxy implements DataStructureProxy<TreeStructureAble> {

    public final static String GET_SIGN = "getter";
    public final static String SET_SIGN = "setter";

    private Class<TreeStructureAble> proxyClass;
    private Method getMethod;
    private Method setMethod;

    private DataTreeProxy(Class<TreeStructureAble> proxyClass) throws NoSuchMethodException {
        this.proxyClass = proxyClass;
        this.getMethod = proxyClass.getMethod("getter");
        this.setMethod = proxyClass.getMethod("setter", java.util.Collection.class);
    }

    public static DataTreeProxy proxyGenerate(Class<TreeStructureAble> targetClass) throws ClassNotFoundException, NoSuchMethodException, IOException {
        String className = targetClass.getSimpleName() + NAME_PROXY_SUFFIX;
        String packageName = targetClass.getPackage().getName();
        String contextPackage = "package " + packageName + PATH_SEPARATOR + LINE_SEPARATOR;
        String contextImport = "import " + targetClass.getName() + PATH_SEPARATOR + LINE_SEPARATOR;
        String contextClass = "public final class " + className
                + " extends " + targetClass.getName() + " {" + LINE_SEPARATOR;
        String contextField = "private java.util.Collection subList;" + LINE_SEPARATOR;
        String contextMethod = "public void setter(java.util.Collection subList) { this.subList = subList; } " + LINE_SEPARATOR
                + "public java.util.Collection getter() { return this.subList; }" + LINE_SEPARATOR;
        String cotextEnd = "}" + LINE_SEPARATOR;
        StringBuffer buffer = new StringBuffer();
        buffer.append(contextPackage).append(contextImport).append(contextClass)
                .append(contextField).append(contextMethod).append(cotextEnd);

        File file = null;
        try {
            file = ProxyFileCreator.create(targetClass, className, buffer.toString());
            ProxyComplier.compile(file);
        } catch (IOException e) {
            throw e;
        } finally {
            file.deleteOnExit();
        }
        String classFullName = targetClass.getName() + NAME_PROXY_SUFFIX;
        return new DataTreeProxy((Class<TreeStructureAble>) Class.forName(classFullName));
    }



    @Override
    public TreeStructureAble newInstance(TreeStructureAble targetObj) {
        return JSON.parseObject(JSON.toJSONString(targetObj), proxyClass);
    }

    @Override
    public List<TreeStructureAble> newInstance(List<TreeStructureAble> targetObjs) {
        return JSON.parseArray(JSON.toJSONString(targetObjs), proxyClass);
    }

    @Override
    public Object invoke(Object sign, TreeStructureAble treeStructureAble, Object... parameters) throws InvocationTargetException, IllegalAccessException {
        if (GET_SIGN.equals(sign)){
            return getMethod.invoke(treeStructureAble);
        } else if (SET_SIGN.equals(sign)){
            return setMethod.invoke(treeStructureAble, parameters);
        } else {
            throw new IllegalAccessException("error coding in " + DataTreeProxy.class + " : invoke sign error!");
        }
    }


}
