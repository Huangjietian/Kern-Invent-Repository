package cn.kerninventory.tools.common.structure.tree;

import java.io.*;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * <p>
 *
 * </p>
 *
 * @author Kern
 */
public class DefaultTreeBuilder<E, T, K> implements TreeBuilder<E, T, K> {

    final Class<E> eType;
    final Class<T> tType;
    final Class<K> kType;
    final BiFunction<E, TreeBranchBuilder<E, T, K>, T> mapping;
    Function<E, K> keyFunc;
    Function<E, K> pkeyFunc;
    Function<T, List<T>> subListFunc;

    DefaultTreeBuilder(Class<E> eType, Class<T> tType, Class<K> kType, BiFunction<E, TreeBranchBuilder<E, T, K>, T> mapping) {
        this.eType = eType;
        this.tType = tType;
        this.kType = kType;
        this.mapping = mapping;
    }

    @Override
    public TreeBranchBuilder<E, T, K> key(Function<E, K> key, Function<E, K> pkey) {
        this.keyFunc = key;
        this.pkeyFunc = pkey;
        return this;
    }

    @Override
    public TreeBuilder<E, T, K> withSubList(Function<T, List<T>> subList) {
        if (tType != TreeNode.class) {
            subListFunc = subList;
        }
        return this;
    }

    @Override
    public <NT> TreeBranchBuilder<E, NT, K> mapping(Class<NT> ntClass, BiFunction<E, TreeBranchBuilder<E, NT, K>, NT> mapping) {
        DefaultTreeBuilder<E, NT, K> newBuilder = new DefaultTreeBuilder<>(eType, ntClass, kType, mapping);
        newBuilder.keyFunc = this.keyFunc;
        newBuilder.pkeyFunc = this.pkeyFunc;
        return newBuilder;
    }

    @Override
    public Function<E, K> getKeyFunc() {
        return keyFunc;
    }

    @Override
    public Function<E, K> getPkeyFunc() {
        return pkeyFunc;
    }

    @Override
    public Function<T, List<T>> getSubListFunc() {
        return subListFunc;
    }

    @Override
    public List<T> build(Collection<E> elements, Predicate<E> isRoot) {
        if (keyFunc == null || pkeyFunc == null) {
            throw new IllegalArgumentException("Can't get key or parent key, Tree build failure!!!");
        }
        if (subListFunc == null) {
            throw new IllegalArgumentException("Failed to get sub-collection, Tree build failure!!!");
        }
        Collection<E> roots = new LinkedList<>();
        Iterator<E> iterator = elements.iterator();
        while (iterator.hasNext()) {
            E next = iterator.next();
            if (isRoot.test(next)) {
                roots.add(next);
                iterator.remove();
            }
        }
        return recursion(new LinkedList<>(), roots, elements);
    }

    @Override
    public List<T> cloneBuild(Collection<E> elements, Predicate<E> isRoot) {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        Collection<E> dest;
        try {
            ObjectOutputStream out = new ObjectOutputStream(byteOut);
            out.writeObject(elements);
            ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
            ObjectInputStream in = new ObjectInputStream(byteIn);
            dest = (Collection<E>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new IllegalArgumentException("Elements clone failure, Tree build failure!!!");
        }
        return build(dest, isRoot);
    }

    private List<T> recursion(List<T> results, Collection<E> roots, Collection<E> elements) {
        for (E root : roots) {
            T result = mapping.apply(root, this);
            results.add(result);
            List<T> subList = getSubListFunc().apply(result);
            if (subList == null) {
                throw new IllegalArgumentException("SubList not be null");
            }
            List<E> eSubList = new LinkedList<>();
            Iterator<E> iterator = elements.iterator();
            K rootKey = Objects.requireNonNull(getKeyFunc().apply(root), "Node key is null");
            while (iterator.hasNext()) {
                E next = iterator.next();
                K nextPkey = getPkeyFunc().apply(next);
                if (rootKey.equals(nextPkey)) {
                    eSubList.add(next);
                    iterator.remove();
                }
            }
            if (!eSubList.isEmpty()) {
                recursion(subList, eSubList, elements);
            }
        }
        return results;
    }

}
