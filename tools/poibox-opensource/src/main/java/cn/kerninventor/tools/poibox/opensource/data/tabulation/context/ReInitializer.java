package cn.kerninventor.tools.poibox.opensource.data.tabulation.context;

/**
 * @author Kern
 * @date 2020/4/15 10:02
 */
@FunctionalInterface
public interface ReInitializer<T> {

    T reInit(T t);
}
