package cn.kerninventor.tools.data.structure;

/**
 * @author Kern
 * @date 2019/11/11 11:15
 */
public interface TreeStructureAble<K> extends DataStructure {

    K getPrimaryKey();

    K getParentKey();

}
