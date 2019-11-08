package cn.kerninventor.tools.dataplanter;

import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * @Title: MianTest
 * @ProjectName tools
 * @Version 1.0.SNAPSHOT
 * @Author Kern
 * @Date 2019/11/8 15:26
 */
public class MianTest {

    public static void main(String[] args) throws NoSuchMethodException {
        String father = "爷爷";
        String s1 = "爸爸";
        String s2 = "叔叔";
        String s11 = "儿子";
        String s12 = "女儿";
        String s21 = "侄子";
        String s22 = "侄女";
        String s111 = "儿子的儿子";
        String s3 = "爷爷的私生子";

        List<Person> list = Arrays.asList(
                new Person(father,null),
                new Person(s1,  father),
                new Person(s2, father),
                new Person(s11, s1),
                new Person(s12, s1),
                new Person(s21, s2),
                new Person(s22, s2),
                new Person(s111, s11),
                new Person(s3, father)
                );

        list = DataPackerFactory.dataTreePacker().setNode(Person.class, "name", "father", null).toTree(list);
        System.out.println(JSON.toJSONString(list));
    }
}
