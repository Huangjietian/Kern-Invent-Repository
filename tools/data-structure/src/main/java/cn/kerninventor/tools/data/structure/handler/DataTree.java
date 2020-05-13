package cn.kerninventor.tools.data.structure.handler;

import cn.kerninventor.tools.data.structure.Tree;

import java.util.List;

/**
 * @author Kern
 * @date 2019/11/11 9:59
 */
public interface DataTree {

    <T extends Tree> List<T> getResult(Tree top);
}
