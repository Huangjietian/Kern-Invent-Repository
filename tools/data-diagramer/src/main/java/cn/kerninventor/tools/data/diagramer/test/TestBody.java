package cn.kerninventor.tools.data.diagramer.test;

import cn.kerninventor.tools.data.diagramer.DataDiagramer;
import cn.kerninventor.tools.data.diagramer.ToTreeDiagramAble;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Title: TestBody
 * @ProjectName tools
 * @Version 1.1.0.RELEASE
 * @Description: TODO
 * @Author Kern
 * @Date 2019/11/11 15:35
 */
public class TestBody implements ToTreeDiagramAble<TestBody, Integer> {

    public TestBody() {
    }

    public TestBody(Integer id, Integer pid) {
        this.id = id;
        this.pid = pid;
    }
    private Integer id;
    private Integer pid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    @Override
    public Integer getPrimaryKey() {
        return id;
    }
    @Override
    public Integer getParentKey() {
        return pid;
    }

    @Override
    public String toString() {
        return "TestBody{" +
                "id=" + id +
                ", pid=" + pid +
                '}';
    }

    public static void main(String[] args) throws NoSuchMethodException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException {
        List<ToTreeDiagramAble> list = new ArrayList<>();
        list.addAll(Arrays.asList(
                new TestBody(1,null),
                new TestBody(2,1),
                new TestBody(3,1),
                new TestBody(4,1),
                new TestBody(5,2),
                new TestBody(6,2),
                new TestBody(7,5),
                new TestBody(8,1),
                new TestBody(9,3),
                new TestBody(10,9),
                new TestBody(11,10),
                new TestBody(12,9)
        ));
        List list0 = DataDiagramer.tree(list).getResult(null);
        List list1 = DataDiagramer.tree(list).getResult(new TestBody(2,1));
        System.out.println("   ===  ");

        /**
         * 完成一个组装层级结构的工具类
         * 要求
         * 1.层级结构的节点新增和删除要便捷，
         * 2.返回的结构要更灵活，
         * 3.支持从某个节点下拉去数据，
         * 4.最终数据展示要符合一般前端需求
         * 5.要能够排序，且能定义排序规则
         * 6.一定程度的结构定制化入口
         */
        /**
         * 实现
         * 1。 继承  劣势：往往业务PO原本都有继承一个公用的超类，这导致需要新建一个信息类似的PO
         * 2.  接口  劣势：实现接口感觉很傻逼
         * 3.  注解  劣势：类型检查
         */
    }
}
