package cn.kerninventory.tools.common.structure.tree;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

/**
 * <p>
 *     树装饰者
 *     S  source类型
 *     T  result类型
 *     K  key类型
 * </p>
 *
 * @author Kern
 */
public interface TreeBuilder<S, T, K> extends TreeBranchBuilder<S, T, K> {

    /**
     * 构建树
     * @param elements 元素集合
     * @param isRoot 判断元素是否为根元素
     * @return 树结构
     */
    List<T> build(Collection<S> elements, Predicate<S> isRoot);

    /**
     * 克隆构建树
     * @param elements 元素集合
     * @param isRoot 判断元素是否为根元素
     * @return 树结构
     */
    List<T> cloneBuild(Collection<S> elements, Predicate<S> isRoot);

    /**
     * Factory Method
     * @param sourceType sourceClass
     * @param keyType keyClass
     * @param <S> source type
     * @param <K> key type
     * @return trunk builder
     */
    static <S, K> TreeNodeTrunkBuilder<S, K> builder(Class<S> sourceType, Class<K> keyType) {
        return new TreeNodeTrunkBuilder<>(sourceType, TreeNode.class, keyType, TreeNode::of);
    }

}
