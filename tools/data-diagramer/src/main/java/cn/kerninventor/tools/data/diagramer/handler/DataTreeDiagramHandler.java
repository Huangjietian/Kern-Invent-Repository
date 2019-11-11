package cn.kerninventor.tools.data.diagramer.handler;

import cn.kerninventor.tools.data.diagramer.ToTreeDiagramAble;
import cn.kerninventor.tools.data.diagramer.proxy.DDTProxyFactory;
import cn.kerninventor.tools.data.diagramer.proxy.DataDiagramProxy;
import cn.kerninventor.tools.data.diagramer.proxy.DataTreeProxy;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * @Title: DataToTreeDiagramHandler
 * @ProjectName tools
 * @Version 1.1.0.RELEASE
 * @Description: TODO
 * @Author Kern
 * @Date 2019/11/11 11:00
 */
public class DataTreeDiagramHandler<T extends ToTreeDiagramAble> implements TreeDiagramTransformer {

    private List<ToTreeDiagramAble> targetDatas;
    private List<ToTreeDiagramAble> result;
    private DataDiagramProxy proxy;

    public DataTreeDiagramHandler(List<T> targetDatas) throws NoSuchMethodException, IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        if (targetDatas == null || targetDatas.size() == 0){
            throw new NullPointerException("datas can't be empty!");
        }
        proxy = DDTProxyFactory.getProxy(targetDatas.get(0));
        this.targetDatas = proxy.newInstance(targetDatas);
    }

    private ToTreeDiagramAble beTop(ToTreeDiagramAble top) throws InstantiationException, IllegalAccessException {
        if ((top = (ToTreeDiagramAble) proxy.newInstance(top)) != null){
            return nonEmpty(top);
        }
        return null;
    }

    private ToTreeDiagramAble nonEmpty(ToTreeDiagramAble toTreeDiagramAble){
        Objects.requireNonNull(toTreeDiagramAble, "ToTreeDiagramable object can't be null!");
        Objects.requireNonNull(toTreeDiagramAble.getPrimaryKey(), "ToTreeDiagramable object primary key can't be null!");
        if (toTreeDiagramAble.getPrimaryKey() instanceof String && "".equals(((String) toTreeDiagramAble.getPrimaryKey()).trim())){
            throw new IllegalArgumentException("ToTreeDiagramable object primary key can't be empty!");
        }
        return toTreeDiagramAble;
    }

    private void putTopside(){
        Iterator<ToTreeDiagramAble> iterator = targetDatas.iterator();
        while (iterator.hasNext()){
            ToTreeDiagramAble t = iterator.next();
            if (nonEmpty(t).getParentKey() == null){
                result.add(t);
                iterator.remove();
            }
        }
    }

    @Override
    public List<ToTreeDiagramAble> getResult(ToTreeDiagramAble top) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        result = new ArrayList<>();
        if ((top = beTop(top)) != null) {
            result.add(top);
        } else {
            putTopside();
        }
        return decorateTree(result, targetDatas);
    }

    private  List<ToTreeDiagramAble> decorateTree(List<ToTreeDiagramAble> result, List<ToTreeDiagramAble> target) throws InvocationTargetException, IllegalAccessException {
        for (ToTreeDiagramAble t : result){
            Iterator<ToTreeDiagramAble> iterator = target.iterator();
            List subList = new ArrayList();
            while (iterator.hasNext()){
                ToTreeDiagramAble t1 = iterator.next();
                if (t.getPrimaryKey().equals(t1.getParentKey())){
                    subList.add(t1);
                    iterator.remove();
                }
            }
            if (subList.size() > 0){
                proxy.invoke(DataTreeProxy.SET_SIGN, t, subList);
                decorateTree((List<ToTreeDiagramAble>) proxy.invoke(DataTreeProxy.GET_SIGN, t, null), target);
            }
        }
        return result;
    }

}
