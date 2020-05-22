package cn.kerninventor.tools.poibox.opensource.data.tabulation.writer.chain;

/**
 * @author Kern
 * @date 2020/5/21 12:01
 * @description
 */
@FunctionalInterface
public interface WriteChain<T> {

    void writeTo(T target);
}
