package cn.kerninventor.tools.poibox.data.templated.reader;

/**
 * @author Kern
 * @date 2020/4/19 18:49
 */
public interface BeanValidator<T, R> {

    void validate(T t);

    R getResult();
}
