package cn.kerninventory.tools.common.structure.tree;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

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
public interface TreeBranchBuilder<S, T, K> extends TreeTrunkBuilder<S, T, K> {

    /**
     * 指定包含子节点, 如果未指定, 最终build将返回一个TreeNode类型的集合, 如果指定则返回当前指定类型的集合
     * 注意, 如果进行了mapping操作, 则需要重新指定, 且子集合不能为空
     * @param subList 子集合
     * @return 当前构造者
     */
    TreeBuilder<S, T, K> withSubList(Function<T, List<T>> subList);

    /**
     * 获取子集获取方式
     * @return func
     */
    Function<T, List<T>> getSubListFunc();

    /**
     * 转换为一个新的树构造者,并指明了元素的映射方式
     * @param <NT> 泛型
     * @param ntClass 新的结果类型
     * @param mapping 映射方式
     * @return 新的树构造者
     */
    <NT> TreeBranchBuilder<S, NT, K> mapping(Class<NT> ntClass, BiFunction<S, TreeBranchBuilder<S, NT, K>, NT> mapping);

}
