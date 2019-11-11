package cn.kerninventor.tools.data.diagramer;

/**
 * @Title: ToTreeDiagramAble
 * @ProjectName tools
 * @Version 1.1.0.RELEASE
 * @Description: TODO
 * @Author Kern
 * @Date 2019/11/11 11:15
 */
public interface ToTreeDiagramAble <T extends ToTreeDiagramAble, K> extends DataDiagramAble {

    K getPrimaryKey();

    K getParentKey();

}
