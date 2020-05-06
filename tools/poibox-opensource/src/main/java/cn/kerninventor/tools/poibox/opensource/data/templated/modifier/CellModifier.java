package cn.kerninventor.tools.poibox.opensource.data.templated.modifier;

import cn.kerninventor.tools.poibox.opensource.developer.ReadyToDevelop;
import org.apache.poi.ss.usermodel.Cell;

/**
 * @author Kern
 * @date 2020/4/14 18:25
 * @description  初始化列的时候根据字段类型， 生成对应的cellvalue，
 */
@ReadyToDevelop
public interface CellModifier<T> {

    void set(Cell cell, T t);

    T get(Cell cell);
}
