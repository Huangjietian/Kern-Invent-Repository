package cn.kerninventor.tools.data.structure;

import cn.kerninventor.tools.data.structure.handler.DataTreeStructureHandler;
import cn.kerninventor.tools.data.structure.handler.DataTreeStructureCarrier;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author Kern
 * @date 2019/11/11 9:57
 */
public class DataStructureBuilder {

    public static <T extends TreeStructureAble> DataTreeStructureCarrier tree(List<T> datas) throws NoSuchMethodException, IOException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return new DataTreeStructureHandler(datas);
    }
}
