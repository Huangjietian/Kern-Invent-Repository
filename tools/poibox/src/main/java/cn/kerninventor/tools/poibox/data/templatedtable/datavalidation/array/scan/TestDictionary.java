package cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.array.scan;

import java.util.List;

/**
 * @author Kern
 * @date 2020/4/9 14:04
 * @description
 */
public class TestDictionary implements Dictionary {
    @Override
    public List obtainDatas(Object... args) {
        System.out.println("yes!!!");
        return null;
    }
}
