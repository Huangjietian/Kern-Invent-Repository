package cn.kerninventor.tools.spring.bean.validator.demo;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @author Kern
 * @date 2020/5/11 19:53
 * @description
 */
//@VerifiedBean(callback = ThrowingCallback.class)
public class Humen {

    @NotBlank(message = "人员姓名不能为空")
    private String name;

    @Min(value = 0, message = "年龄不能小于0岁")
    private int age;

    @Min(value = 1, message = "性别错误")
    @Max(value = 2, message = "性别错误")
    private int gender;


    public String getName() {
        return name;
    }

    public Humen setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Humen setAge(int age) {
        this.age = age;
        return this;
    }

    public int getGender() {
        return gender;
    }

    public Humen setGender(int gender) {
        this.gender = gender;
        return this;
    }

    @Override
    public String toString() {
        return "Humen{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
}
