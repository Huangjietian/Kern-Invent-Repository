package cn.kerninventor.tools.data.diagramer.proxy;

import cn.kerninventor.tools.data.diagramer.ToTreeDiagramAble;
import cn.kerninventor.tools.data.diagramer.proxy.util.DDTComplier;
import cn.kerninventor.tools.data.diagramer.proxy.util.ProxyJavaFileCreator;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Title: DataTreeProxy
 * @ProjectName tools
 * @Version 1.1.0.RELEASE
 * @Description: TODO
 * @Author Kern
 * @Date 2019/11/11 15:30
 */
public class DataTreeProxy implements DataDiagramProxy<ToTreeDiagramAble> {

    public final static String GET_SIGN = "getter";
    public final static String SET_SIGN = "setter";

    private Class<ToTreeDiagramAble> proxyClass;
    private Method getMethod;
    private Method setMethod;

    private DataTreeProxy(Class<ToTreeDiagramAble> proxyClass) throws NoSuchMethodException {
        this.proxyClass = proxyClass;
        this.getMethod = proxyClass.getMethod("getter");
        this.setMethod = proxyClass.getMethod("setter", java.util.Collection.class);
    }

    public static DataTreeProxy proxyGenerate(Class<ToTreeDiagramAble> targetClass) throws ClassNotFoundException, NoSuchMethodException, IOException {
        String className = targetClass.getSimpleName() + NAME_PROXY_SUFFIX;
        String packageName = targetClass.getPackage().getName();

        String contextPackage = "package " + packageName + PATH_SEPARATOR + LINE_SEPARATOR;
        String contextImport = "import " + targetClass.getName() + PATH_SEPARATOR + LINE_SEPARATOR;
        String contextClass = "public class " + className
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
            file = ProxyJavaFileCreator.create(targetClass, className, buffer.toString());
            DDTComplier.compile(file);
        } catch (IOException e) {
            throw e;
        } finally {
            file.deleteOnExit();
        }
        String classFullName = targetClass.getName() + NAME_PROXY_SUFFIX;
        return new DataTreeProxy((Class<ToTreeDiagramAble>) Class.forName(classFullName));
    }

    @Override
    public ToTreeDiagramAble newInstance(ToTreeDiagramAble targetObj) {
        return JSON.parseObject(JSON.toJSONString(targetObj), proxyClass);
    }

    @Override
    public List<ToTreeDiagramAble> newInstance(List<ToTreeDiagramAble> targetObjs) {
        return JSON.parseArray(JSON.toJSONString(targetObjs), proxyClass);
    }

    @Override
    public Object invoke(Object sign, ToTreeDiagramAble toTreeDiagramAble, Object... parameters) throws InvocationTargetException, IllegalAccessException {
        if (GET_SIGN.equals(sign)){
            return getMethod.invoke(toTreeDiagramAble);
        } else if (SET_SIGN.equals(sign)){
            return setMethod.invoke(toTreeDiagramAble, parameters);
        } else {
            throw new IllegalAccessException("error coding in " + DataTreeProxy.class + " : invoke sign error!");
        }
    }


}
