package cn.kerninventor.tools.data.structure.handler;

import cn.kerninventor.tools.data.structure.Tree;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author Kern
 * @date 2019/11/11 9:59
 */
public interface DataTree {

    List<Tree> getResult(Tree top) throws InvocationTargetException, IllegalAccessException, InstantiationException;
}
