package cn.kerninventory.tools.common.structure;

import java.util.Collection;

/**
 * <h1>中文注释</h1>
 * <p>
 *     实现了该接口的类可以被{@link Tree#of(Collection, Sapling)}方法接收，并将返回一个泛型为当前类的Tree类实例。实现树状结构的封装
 * </p>
 * @author Kern
 * @version 1.0
 */
public interface Sapling<Key> extends Seed {

    Key subNode();

    Key rootNode();

}
