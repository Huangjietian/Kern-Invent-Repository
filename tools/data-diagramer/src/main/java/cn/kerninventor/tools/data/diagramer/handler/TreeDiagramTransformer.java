package cn.kerninventor.tools.data.diagramer.handler;

import cn.kerninventor.tools.data.diagramer.ToTreeDiagramAble;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @Title: DataStructureToTreeTransverter
 * @ProjectName swms
 * @Version 1.1.0.RELEASE
 * @Description: TODO
 * @Author Kern
 * @Date 2019/11/11 9:59
 */
public interface TreeDiagramTransformer {

    List<ToTreeDiagramAble> getResult(ToTreeDiagramAble top) throws InvocationTargetException, IllegalAccessException, InstantiationException;
}
