package cn.kerninventor.tools.data.structure.handler;

import cn.kerninventor.tools.data.structure.TreeStructureAble;
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
public class DataTreeStructureHandler<T extends TreeStructureAble> implements DataTreeStructureCarrier {

    private List<TreeStructureAble> targetDatas;
    private List<TreeStructureAble> result;
    private DataStructureProxy proxy;

    public DataTreeStructureHandler(List<T> targetDatas) throws NoSuchMethodException, IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        if (targetDatas == null || targetDatas.size() == 0){
            throw new NullPointerException("datas can't be empty!");
        }
        proxy = DDTProxyFactory.getProxy(targetDatas.get(0));
        this.targetDatas = proxy.newInstance(targetDatas);
    }

    private TreeStructureAble beTop(TreeStructureAble top) throws InstantiationException, IllegalAccessException {
        if ((top = (TreeStructureAble) proxy.newInstance(top)) != null){
            return nonEmpty(top);
        }
        return null;
    }

    private TreeStructureAble nonEmpty(TreeStructureAble treeStructureAble){
        Objects.requireNonNull(treeStructureAble, "ToTreeDiagramable object can't be null!");
        Objects.requireNonNull(treeStructureAble.getPrimaryKey(), "ToTreeDiagramable object primary key can't be null!");
        if (treeStructureAble.getPrimaryKey() instanceof String && "".equals(((String) treeStructureAble.getPrimaryKey()).trim())){
            throw new IllegalArgumentException("ToTreeDiagramable object primary key can't be empty!");
        }
        return treeStructureAble;
    }

    private void putTopside(){
        Iterator<TreeStructureAble> iterator = targetDatas.iterator();
        while (iterator.hasNext()){
            TreeStructureAble t = iterator.next();
            if (nonEmpty(t).getParentKey() == null){
                result.add(t);
                iterator.remove();
            }
        }
    }

    @Override
    public List<TreeStructureAble> getResult(TreeStructureAble top) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        result = new ArrayList<>();
        if ((top = beTop(top)) != null) {
            result.add(top);
        } else {
            putTopside();
        }
        return decorateTree(result, targetDatas);
    }

    private  List<TreeStructureAble> decorateTree(List<TreeStructureAble> result, List<TreeStructureAble> target) throws InvocationTargetException, IllegalAccessException {
        for (TreeStructureAble t : result){
            Iterator<TreeStructureAble> iterator = target.iterator();
            List subList = new ArrayList();
            while (iterator.hasNext()){
                TreeStructureAble t1 = iterator.next();
                if (t.getPrimaryKey().equals(t1.getParentKey())){
                    subList.add(t1);
                    iterator.remove();
                }
            }
            if (subList.size() > 0){
                proxy.invoke(DataTreeProxy.SET_SIGN, t, subList);
                decorateTree((List<TreeStructureAble>) proxy.invoke(DataTreeProxy.GET_SIGN, t, null), target);
            }
        }
        return result;
    }

}
