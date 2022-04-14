package cn.kerninventory.tools.common.structure.tree;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * <p>
 *
 * </p>
 *
 * @author Kern
 */
public class TreeNodeTrunkBuilder<S, K> implements TreeTrunkBuilder<S, TreeNode, K> {

    private final DefaultTreeBuilder<S, TreeNode, K> defaultTreeBuilder;

    TreeNodeTrunkBuilder(Class<S> eType, Class<TreeNode> tType, Class<K> kType, BiFunction<S, TreeBranchBuilder<S, TreeNode, K>, TreeNode> mapping) {
        this.defaultTreeBuilder = new DefaultTreeBuilder<>(eType, tType, kType, mapping);
        this.defaultTreeBuilder.subListFunc = TreeNode::getBranches;
    }

    @Override
    public TreeBuilder<S, TreeNode, K> key(Function<S, K> key, Function<S, K> pkey) {
        defaultTreeBuilder.key(key, pkey);
        return defaultTreeBuilder;
    }

    @Override
    public Function<S, K> getKeyFunc() {
        return defaultTreeBuilder.getKeyFunc();
    }

    @Override
    public Function<S, K> getPkeyFunc() {
        return defaultTreeBuilder.getPkeyFunc();
    }
}
