package cn.kerninventor.tools.data.structure;

import java.util.List;

/**
 * @author Kern
 * @date 2019/11/11 11:15
 */
public interface Tree<K, T extends Tree> extends DataStructure {

    K getChildKey();

    K getParentKey();

    /**
     * 在织入代理之后，通过该方法可以获得树状结构的下层
     *
     *
     * @author Kern
     * @date 2020/4/22
     * @description
     * @中文描述
    */
    default List<T> branches() {
        //NOTHING TO DO !
        return null;
    }

    /**
     * 在织入代理之后，通过该方法获得本体对象并进行操作
     *
     *
     * @author Kern
     * @date 2020/4/22
     * @description
     * @中文描述
    */
    default T trunk() {
        return null;
    }

    /**
     * 提供给代理对象对下层进行赋值的方法声明。
     *
     * @author Kern
     * @date 2020/4/22
     * @description
     * @中文描述
    */
    default void setBranches(List<T> list) {
        //NOTHING TO DO !
    }

}
