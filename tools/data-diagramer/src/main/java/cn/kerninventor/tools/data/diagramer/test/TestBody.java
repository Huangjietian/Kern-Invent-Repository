package cn.kerninventor.tools.data.diagramer.test;

import cn.kerninventor.tools.data.diagramer.DataDiagramer;
import cn.kerninventor.tools.data.diagramer.ToTreeDiagramAble;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

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
    @Override
    public Integer getPrimaryKey() {
        return id;
    }
    @Override
    public Integer getParentKey() {
        return pid;
    }

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
    }
}
