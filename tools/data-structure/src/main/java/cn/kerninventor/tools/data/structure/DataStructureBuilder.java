package cn.kerninventor.tools.data.structure;

import cn.kerninventor.tools.data.structure.handler.DataTree;
import cn.kerninventor.tools.data.structure.handler.DataTreeBuilder;

import java.util.List;

/**
 * @author Kern
 * @date 2019/11/11 9:57
 */
public class DataStructureBuilder {

    public static <T extends Tree> DataTree toTree(List<T> datas) {
        return new DataTreeBuilder(datas);
    }
}
