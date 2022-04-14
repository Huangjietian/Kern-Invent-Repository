package cn.kerninventory.tools.common.structure.tree;

import java.util.function.Function;

/**
 * <p>
 *      预先构建 ->  key - pkey关系
 * </p>
 *
 * @author Kern
 */
public interface TreeTrunkBuilder<S, T, K> {

    /**
     * 指定key 和 上级key
     * @param key key
     * @param pkey pkey
     * @return 当前构造者
     */
    TreeBranchBuilder<S, T, K> key(Function<S, K> key, Function<S, K> pkey);

    /**
     * 获取key获取方式
     * @return func
     */
    Function<S, K> getKeyFunc();

    /**
     * 获取pkey获取方式
     * @return func
     */
    Function<S, K> getPkeyFunc();
}
