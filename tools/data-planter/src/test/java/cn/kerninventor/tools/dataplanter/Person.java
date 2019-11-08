package cn.kerninventor.tools.dataplanter;

/**
 * @Title: Person
 * @ProjectName tools
 * @Version 1.0.SNAPSHOT
 * @Author Kern
 * @Date 2019/11/8 15:28
 */
public class Person extends InheritedDataTree {

    private String name;

    private String father;

    public Person(String name, String father) {
        this.name = name;
        this.father = father;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }

    @Override
    public String getBranchesName() {
        return "persons";
    }
}
