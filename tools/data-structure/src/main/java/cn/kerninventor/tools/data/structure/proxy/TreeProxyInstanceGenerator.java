package cn.kerninventor.tools.data.structure.proxy;

import cn.kerninventor.tools.data.structure.Tree;
import cn.kerninventor.tools.data.structure.proxy.util.ProxyFileComplier;
import cn.kerninventor.tools.data.structure.proxy.util.ProxyFileCreator;
import com.alibaba.fastjson.JSON;

import java.io.File;
import java.util.List;

/**
 * @author Kern
 * @date 2019/11/11 15:30
 */
public class TreeProxyInstanceGenerator<T extends Tree> implements ProxyInstanceGenerator {

    private Class<T> proxyClass;

    private TreeProxyInstanceGenerator(Class<T> proxyClass) {
        this.proxyClass = proxyClass;
    }

    private static String getClassContent(Class targetClass) {
        String proxyClassName = targetClass.getSimpleName() + NAME_PROXY_SUFFIX;
        String packageName = targetClass.getPackage().getName();
        return new StringBuilder()
                .append("package ").append(packageName).append(LINE_END)
                .append("public final class ").append(proxyClassName).append(" extends ").append(targetClass.getName()).append(" {").append(LINE_SEPARATOR)
                .append("private java.util.List subList").append(LINE_END)
                .append("@Override").append(LINE_SEPARATOR)
                .append("public java.util.List branches() { return this.subList;}").append(LINE_SEPARATOR)
                .append("@Override").append(LINE_SEPARATOR)
                .append("public void setBranches(java.util.List list) {this.subList = list;}").append(LINE_SEPARATOR)
                .append("}").append(LINE_SEPARATOR)
                .toString()
                ;
    }

    public static TreeProxyInstanceGenerator proxyGenerate(Class<? extends Tree> targetClass) throws Exception {
        String classContent = getClassContent(targetClass);
        String proxyClassSimpleName = targetClass.getSimpleName() + NAME_PROXY_SUFFIX;
        File file = ProxyFileCreator.create(proxyClassSimpleName, targetClass, classContent);
        ProxyFileComplier.compile(file);
        String proxyClassFullName = targetClass.getName() + NAME_PROXY_SUFFIX;
        Class clazz = Class.forName(proxyClassFullName);
        return new TreeProxyInstanceGenerator(clazz);
    }

    public <S extends Tree> T newInstance(S target) {
        String objJson = JSON.toJSONString(target);
        return JSON.parseObject(objJson, proxyClass);
    }

    public List<T> newInstance(List<? extends Tree> targets) {
        String objJson = JSON.toJSONString(targets);
        return JSON.parseArray(objJson, proxyClass);
    }

}
