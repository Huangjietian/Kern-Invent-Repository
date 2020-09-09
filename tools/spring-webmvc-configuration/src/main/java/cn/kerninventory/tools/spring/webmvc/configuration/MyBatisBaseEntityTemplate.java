package cn.kerninventory.tools.spring.webmvc.configuration;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * <p>
 *     ORM框架采用Mybatis及其衍生框架时的所有entity类的超类模板
 * </p>
 *
 * @author Kern
 */
public class MyBatisBaseEntityTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    private long count;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
