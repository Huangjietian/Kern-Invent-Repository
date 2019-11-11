package cn.kerninventor.tools.data.diagramer;

import cn.kerninventor.tools.data.diagramer.handler.DataTreeDiagramHandler;
import cn.kerninventor.tools.data.diagramer.handler.TreeDiagramTransformer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @Title: DataStructureUtils
 * @ProjectName swms
 * @Version 1.1.0.RELEASE
 * @Description: TODO
 * @Author Kern
 * @Date 2019/11/11 9:57
 */
public class DataDiagramer {

    public static <T extends ToTreeDiagramAble> TreeDiagramTransformer tree(List<T> datas) throws NoSuchMethodException, IOException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return new DataTreeDiagramHandler(datas);
    }
}
