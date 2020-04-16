package cn.kerninventory.tools.data.bean.convert;

/**
 * @author Kern
 * @date 2020/4/16 11:11
 */
public interface BeanMapping <S, T>{

    S source();

    T target();

}
