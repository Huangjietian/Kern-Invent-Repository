package cn.kerninventory.tools.common.structure;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <h1>中文注释</h1>
 * <p>
 *     树状结构封装类
 * </p>
 * @author Kern
 * @version 1.0
 */
public class Tree<T extends Sapling> {

    private T trunk;

    private List<Tree<T>> branches;

    private Tree(T trunk) {
        this.trunk = trunk;
    }

    /**
     * 获取当前结构的主体
     * @return
     */
    public T getTrunk() {
        return trunk;
    }

    /**
     * 重设当前结构的主体
     * @param trunk
     * @return
     */
    public Tree<T> setTrunk(T trunk) {
        this.trunk = trunk;
        return this;
    }

    /**
     * 获取当前结构的分支
     * @return
     */
    public List<Tree<T>> getBranches() {
        return branches;
    }

    /**
     * 重设当前结构的分支
     * @param branches
     * @return
     */
    public Tree<T> setBranches(List<Tree<T>> branches) {
        this.branches = branches;
        return this;
    }

    /**
     * <p>
     *     传入实现了{@link Sapling}接口的对象集合，可以获得一个Tree实例，表示了该集合的树状结构。<br/>
     *     可以通过rootStrategy参数指定根部策略
     * </p>
     * @param collection
     * @param rootStrategy
     * @param <T>
     * @return
     */
    public static <T extends Sapling> Tree<T> of(Collection<T> collection, RootStrategy rootStrategy) {
        T root = findRoot(collection, rootStrategy);
        return of(collection, root);
    }

    /**
     * <p>
     *     传入实现了{@link Sapling}接口的对象集合，可以获得一个Tree实例，表示了该集合的树状结构。<br/>
     *     可以通过root参数指定根部
     * </p>
     * @param collection
     * @param root
     * @param <T>
     * @return
     */
    public static <T extends Sapling> Tree<T> of(Collection<T> collection, Sapling root) {
        if (root == null) {
            root = findRoot(collection, e -> e.rootNode() == null);
        }
        if (root == null) {
            throw new IllegalArgumentException("No root was found!");
        }
        Tree<T> tree = new Tree(root);
        tree.decorate(collection);
        return tree;
    }

    private void decorate(Collection<T> collection) {
        Iterator<T> iterator = collection.iterator();
        while (iterator.hasNext()) {
            T t = iterator.next();
            if (trunk.subNode().equals(t.rootNode())) {
                Tree tree = new Tree(t);
                tree.decorate(collection);
                addBranches(tree);
                //FIXME 下面的代码将导致  ConcurrentModificationException
//                iterator.remove();
            }
        }
    }

    private void addBranches(Tree<T> tree) {
        if (branches == null) {
            branches = new ArrayList<>();
        }
        branches.add(tree);
    }

    private static <T extends Sapling> T findRoot(Collection<T> collection, RootStrategy rootStrategy) {
        Set<T> roots = collection.stream().filter(e -> rootStrategy.rule(e)).collect(Collectors.toSet());
        if (roots.size() != 1) {
            throw new IllegalArgumentException("Multiple root found!");
        }
        return roots.iterator().next();
    }

    /**
     * <h1>中文注释</h1>
     * <p>
     *     根部策略函数接口
     * </p>
     */
    @FunctionalInterface
    public interface RootStrategy {

        boolean rule(Sapling root);

    }
}
