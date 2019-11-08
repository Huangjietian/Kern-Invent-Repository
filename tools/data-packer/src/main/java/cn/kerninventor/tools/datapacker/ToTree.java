package cn.kerninventor.tools.datapacker;

import java.util.List;

/**
 * @Title: ToTree
 * @ProjectName swms
 * @Version 1.0.SNAPSHOT
 * @Author Kern
 * @Date 2019/11/8 11:26
 */
public interface ToTree <T extends InheritedDataTree> {

    List<T> toTree(List<T> sends) throws NoSuchMethodException;
}
