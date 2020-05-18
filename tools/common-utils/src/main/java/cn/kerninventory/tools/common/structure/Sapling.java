package cn.kerninventory.tools.common.structure;

/**
 * @author Kern
 * @date 2020/5/18 8:46
 * @description
 */
public interface Sapling<Key> extends Seed {

    Key subNode();

    Key rootNode();

}
