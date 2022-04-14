package cn.kerninventory.tools.common.structure.tree;


import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 *     树节点
 * </p>
 *
 * @author Kern
 */
public class TreeNode extends LinkedHashMap<String, Object> implements Serializable {

    private final static String KEY_NAME = "key";
    private final static String PKEY_NAME = "pkey";
    private final static String BRANCHES_NAME = "branches";


    private Object key;
    private Object pkey;
    private final List<TreeNode> branches = new LinkedList<>();

    static <E> TreeNode of(E element, TreeBranchBuilder<E, TreeNode, ?> builder) {
        TreeNode treeNode = new TreeNode();
//        treeNode.setKey(builder.getKeyFunc().apply(element));
//        treeNode.setPkey(builder.getPkeyFunc().apply(element));
        treeNode.put(KEY_NAME, builder.getKeyFunc().apply(element));
        treeNode.put(PKEY_NAME, builder.getPkeyFunc().apply(element));
        treeNode.put(BRANCHES_NAME, treeNode.branches);
        Field[] declaredFields = element.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            try {
                Object o = declaredField.get(element);
                treeNode.put(declaredField.getName(), o);
            } catch (IllegalAccessException e) {
                throw new IllegalArgumentException("Getting element value failure", e);
            }
        }
        return treeNode;
    }

    public Object getKey() {
        return get(KEY_NAME);
    }

    public Object getPkey() {
        return get(PKEY_NAME);
    }

    public List<TreeNode> getBranches() {
        return (List<TreeNode>) get(BRANCHES_NAME);
    }

}
