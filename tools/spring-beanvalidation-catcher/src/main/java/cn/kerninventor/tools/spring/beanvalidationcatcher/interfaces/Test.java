package cn.kerninventor.tools.spring.beanvalidationcatcher.interfaces;

import javax.validation.ConstraintViolation;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Kern
 * @date 2020/3/17 19:37
 * @description TODO
 */
public class Test implements BeanVerifiable {

    @NotNull(message = "age不能为空")
    private Integer age;

    @NotNull(message = "name不能为空")
    private String name;

    @NotNull(message = "id不能为空")
    private Long id;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private List<String> message;

    public List<String> getMessage() {
        return message;
    }

    @Override
    public Object errorMessages(Set<ConstraintViolation<Object>> constraintViolationSet) {
        message = constraintViolationSet.stream().map(e -> e.getMessage()).collect(Collectors.toList());
        return null;
    }
}
