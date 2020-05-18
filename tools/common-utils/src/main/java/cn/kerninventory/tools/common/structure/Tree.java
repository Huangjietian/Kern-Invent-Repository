package cn.kerninventory.tools.common.structure;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Kern
 * @date 2020/5/18 9:01
 * @description
 */
public class Tree<T extends Sapling> {

    private T trunk;

    private List<Tree<T>> branches;

    public Tree(T trunk) {
        this.trunk = trunk;
    }

    public T getTrunk() {
        return trunk;
    }

    public Tree<T> setTrunk(T trunk) {
        this.trunk = trunk;
        return this;
    }

    public List<Tree<T>> getBranches() {
        return branches;
    }

    public Tree<T> setBranches(List<Tree<T>> branches) {
        this.branches = branches;
        return this;
    }

    private void addBranche(Tree<T> tree) {
        if (branches == null) {
            branches = new ArrayList<>();
        }
        branches.add(tree);
    }

    public static <T extends Sapling> Tree<T> of(Collection<T> collection, RootStrategy rootStrategy) {
        T root = findRoot(collection, rootStrategy);
        return of(collection, root);
    }

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

    public void decorate(Collection<T> collection) {
        Iterator<T> iterator = collection.iterator();
        while (iterator.hasNext()) {
            T t = iterator.next();
            if (trunk.subNode().equals(t.rootNode())) {
                Tree tree = new Tree(t);
                tree.decorate(collection);
                addBranche(tree);
                //FIXME 下面的代码将导致  ConcurrentModificationException
//                iterator.remove();
            }
        }
    }

    private static <T extends Sapling> T findRoot(Collection<T> collection, RootStrategy rootStrategy) {
        Set<T> roots = collection.stream().filter(e -> rootStrategy.rule(e)).collect(Collectors.toSet());
        if (roots.size() != 1) {
            throw new IllegalArgumentException("Multiple root found!");
        }
        return roots.iterator().next();
    }

    @FunctionalInterface
    public interface RootStrategy {

        boolean rule(Sapling root);

    }

}
