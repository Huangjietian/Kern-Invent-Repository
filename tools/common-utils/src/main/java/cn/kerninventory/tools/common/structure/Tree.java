package cn.kerninventory.tools.common.structure;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <h1>中文注释</h1>
 * <p>
 *     树状结构封装类
 * </p>
 * @author Kern
 * @version 1.0
 */
public class Tree<T extends Sapling, R> {

    private T trunk;

    private R result;

    private List<Tree<T, R>> branches;

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
    public Tree<T, R> setTrunk(T trunk) {
        this.trunk = trunk;
        return this;
    }

    /**
     * 获取当前结构的分支
     * @return
     */
    public List<Tree<T, R>> getBranches() {
        return branches;
    }

    /**
     * 重设当前结构的分支
     * @param branches
     * @return
     */
    public Tree<T, R> setBranches(List<Tree<T, R>> branches) {
        this.branches = branches;
        return this;
    }

    public R getResult() {
        return result;
    }

    public Tree<T, R> setResult(R result) {
        this.result = result;
        return this;
    }

    /**
     * <p>
     *     对树状结构当前层及其下一层次进行遍历操作，使树形结构自当前层起得到一系列的结果。需要指定一个Function函数
     * </p>
     * @param function
     */
    public void forEachOperate(Function<T, R> function) {
        operate(function);
        List<Tree<T, R>> branches = getBranches();
        if (branches != null) {
            branches.forEach(e -> e.forEachOperate(function));
        }
    }

    /**
     * <p>
     *     对树形结构的当前层进行操作，得到一个Result结果。需要指定一个Function函数
     * </p>
     * @param function
     */
    public void operate(Function<T ,R> function) {
        setResult(function.apply(getTrunk()));
    }

    /**
     * <p>
     *     传入实现了{@link Sapling}接口的对象集合，可以获得一个Tree实例，表示了该集合的树状结构。<br/>
     *     可以通过rootStrategy参数指定根部策略
     * </p>
     * @param collection
     * @param rootStrategy
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T extends Sapling, R> Tree<T, Object> of(Collection<T> collection, RootStrategy rootStrategy) {
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
     * @param <R>
     * @return
     */
    public static <T extends Sapling, R> Tree<T, Object> of(Collection<T> collection, Sapling root) {
        if (root == null) {
            root = findRoot(collection, e -> e.rootNode() == null);
        }
        if (root == null) {
            throw new IllegalArgumentException("No root was found!");
        }
        Tree<T, Object> tree = new Tree(root);
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
            }
        }
    }

    private void addBranches(Tree<T, R> tree) {
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
