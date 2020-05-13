package cn.kerninventor.tools.data.structure.handler;

import cn.kerninventor.tools.data.structure.Tree;
import cn.kerninventor.tools.data.structure.proxy.ProxyInstanceGeneratorFactory;
import cn.kerninventor.tools.data.structure.proxy.TreeProxyInstanceGenerator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * @author Kern
 * @date 2019/11/11 11:00
 */
public class DataTreeBuilder<T extends Tree> implements DataTree {

    private List<Tree> tProxys;
    private List<Tree> result;
    private TreeProxyInstanceGenerator proxyInstanceGenerator;

    public DataTreeBuilder(List<T> targets) {
        if (targets == null || targets.size() == 0){
            throw new NullPointerException("datas can't be empty!");
        }
        this.proxyInstanceGenerator = ProxyInstanceGeneratorFactory.getTreeProxy(targets.get(0).getClass());
        this.tProxys = this.proxyInstanceGenerator.newInstance(targets);
    }

    private Tree beTop(Tree top) {
        if ((top = proxyInstanceGenerator.newInstance(top)) != null){
            return nonEmpty(top);
        }
        return null;
    }

    private Tree nonEmpty(Tree tree){
        Objects.requireNonNull(tree, "Tree object can't be null!");
        Objects.requireNonNull(tree.subNode(), "Tree object sub node can't be null!");
        if (tree.subNode() instanceof String && "".equals(((String) tree.subNode()).trim())){
            throw new IllegalArgumentException("Tree object sub node can't be empty!");
        }
        return tree;
    }

    private void putTopside(){
        Iterator<Tree> iterator;
        for (iterator = tProxys.iterator() ; iterator.hasNext() ;) {
            Tree t = iterator.next();
            if (nonEmpty(t).masterNode() == null){
                result.add(t);
                iterator.remove();
            }
        }
    }

    @Override
    public List<? extends Tree> getResult(Tree top) {
        result = new ArrayList<>();
        if ((top = beTop(top)) != null) {
            result.add(top);
        } else {
            putTopside();
        }
        return decorateTree(result, tProxys);
    }

    private  List<Tree> decorateTree(List<Tree> result, List<Tree> target) {
        for (Tree t : result){
            List subList = new ArrayList();
            for (Iterator<Tree> iterator = target.iterator(); iterator.hasNext() ;) {
                Tree t1 = iterator.next();
                if (t.subNode().equals(t1.masterNode())){
                    subList.add(t1);
                    iterator.remove();
                }
            }
            if (subList.size() > 0){
                t.setBranches(subList);
                decorateTree(t.branches(), target);
            }
        }
        return result;
    }

}
