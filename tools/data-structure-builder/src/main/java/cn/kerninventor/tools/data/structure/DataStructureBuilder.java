package cn.kerninventor.tools.data.structure;

import cn.kerninventor.tools.data.structure.handler.DataTreeBuilder;
import cn.kerninventor.tools.data.structure.handler.DataTree;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author Kern
 * @date 2019/11/11 9:57
 */
public class DataStructureBuilder {

    public static <T extends Tree> DataTree toTree(List<T> datas) throws NoSuchMethodException, IOException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return new DataTreeBuilder(datas);
    }
}
