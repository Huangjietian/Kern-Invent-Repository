package cn.kerninventor.tools.data.structure.handler;

import cn.kerninventor.tools.data.structure.Tree;
import cn.kerninventor.tools.data.structure.proxy.DDTProxyFactory;
import cn.kerninventor.tools.data.structure.proxy.DataStructureProxy;
import cn.kerninventor.tools.data.structure.proxy.DataTreeProxy;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * @author Kern
 * @date 2019/11/11 11:00
 */
public class DataTreeBuilder<T extends Tree> implements DataTree {

    private List<Tree> targetDatas;
    private List<Tree> result;
    private DataStructureProxy proxy;

    public DataTreeBuilder(List<T> targetDatas) throws NoSuchMethodException, IOException, ClassNotFoundException {
        if (targetDatas == null || targetDatas.size() == 0){
            throw new NullPointerException("datas can't be empty!");
        }
        proxy = DDTProxyFactory.getTreeProxy(targetDatas.get(0));
        this.targetDatas = proxy.newInstance(targetDatas);
    }

    private Tree beTop(Tree top) throws InstantiationException, IllegalAccessException {
        if ((top = (Tree) proxy.newInstance(top)) != null){
            return nonEmpty(top);
        }
        return null;
    }

    private Tree nonEmpty(Tree tree){
        Objects.requireNonNull(tree, "ToTreeDiagramable object can't be null!");
        Objects.requireNonNull(tree.getChildKey(), "ToTreeDiagramable object child key can't be null!");
        if (tree.getChildKey() instanceof String && "".equals(((String) tree.getChildKey()).trim())){
            throw new IllegalArgumentException("ToTreeDiagramable object child key can't be empty!");
        }
        return tree;
    }

    private void putTopside(){
        Iterator<Tree> iterator;
        for (iterator = targetDatas.iterator() ; iterator.hasNext() ;) {
            Tree t = iterator.next();
            if (nonEmpty(t).getParentKey() == null){
                result.add(t);
                iterator.remove();
            }
        }
    }

    @Override
    public List<Tree> getResult(Tree top) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        result = new ArrayList<>();
        if ((top = beTop(top)) != null) {
            result.add(top);
        } else {
            putTopside();
        }
        return decorateTree(result, targetDatas);
    }

    private  List<Tree> decorateTree(List<Tree> result, List<Tree> target) throws InvocationTargetException, IllegalAccessException {
        for (Tree t : result){
            List subList = new ArrayList();
            for (Iterator<Tree> iterator = target.iterator(); iterator.hasNext() ;) {
                Tree t1 = iterator.next();
                if (t.getChildKey().equals(t1.getParentKey())){
                    subList.add(t1);
                    iterator.remove();
                }
            }
            if (subList.size() > 0){
                proxy.invoke(DataTreeProxy.SET_SIGN, t, subList);
                decorateTree((List<Tree>) proxy.invoke(DataTreeProxy.GET_SIGN, t, null), target);
            }
        }
        return result;
    }

}
