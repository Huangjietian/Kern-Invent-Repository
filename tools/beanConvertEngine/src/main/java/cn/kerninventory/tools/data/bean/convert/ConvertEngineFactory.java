package cn.kerninventory.tools.data.bean.convert;

import java.util.Collection;

/**
 * @author Kern
 * @date 2020/4/16 11:09
 */
public class ConvertEngineFactory {

    <S, T> ConvertEngine createEngine(Collection<Reference<S, T>>  referenceCollection) {
        return null;
    }
}
