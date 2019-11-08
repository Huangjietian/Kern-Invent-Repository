package cn.kerninventor.tools.datapacker;

/**
 * @Title: Planter
 * @ProjectName swms
 * @Version 1.0.SNAPSHOT
 * @Author Kern
 * @Date 2019/11/8 10:36
 */
public interface DataTreePacker<T extends InheritedDataTree> {

    ToTree<T> setNode(Class<T> tClass, String upperNodeName, String lowerNodeName, T topOne) throws NoSuchMethodException;

}
