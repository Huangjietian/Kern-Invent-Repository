package cn.kerninventor.tools.data.structure;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kern
 * @date 2020/5/13 18:03
 * @description
 */
public class Test implements Tree<String, Test> {

    private String id;

    private String pid;

    public Test() {
    }

    public Test(String id, String pid) {
        this.id = id;
        this.pid = pid;
    }

    public String getId() {
        return id;
    }

    public Test setId(String id) {
        this.id = id;
        return this;
    }

    public String getPid() {
        return pid;
    }

    public Test setPid(String pid) {
        this.pid = pid;
        return this;
    }

    @Override
    public String subNode() {
        return id;
    }

    @Override
    public String masterNode() {
        return pid;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id='" + id + '\'' +
                ", pid='" + pid + '\'' +
                '}';
    }

    public static void main(String[] args) {
        List<Test> tests = new ArrayList<>();

        tests.add(new Test("AA", null));
        tests.add(new Test("A1","AA"));
        tests.add(new Test("A2","AA"));
        tests.add(new Test("A3","AA"));
        tests.add(new Test("A11","A1"));
        tests.add(new Test("A12","A1"));

        List<Tree> newTests = new ArrayList<>();
        newTests = DataStructureBuilder.toTree(tests).getResult(new Test("AA",null));
        System.out.println(newTests);

        List branches = newTests.get(0).branches();

        System.out.println(branches);
    }
}
