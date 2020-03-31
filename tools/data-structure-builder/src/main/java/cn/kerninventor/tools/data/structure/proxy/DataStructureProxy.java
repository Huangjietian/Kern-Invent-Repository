package cn.kerninventor.tools.data.structure.proxy;

import cn.kerninventor.tools.data.structure.DataStructure;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author Kern
 * @date 2019/11/11 17:40
 */
public interface DataStructureProxy<T extends DataStructure> {

    String LINE_SEPARATOR = System.lineSeparator();
    String PATH_SEPARATOR = ";";
    String NAME_PROXY_SUFFIX = "$Proxy";
    String
            _package = "package ",
            _import = "import ",
            _public = "public",
            _final = "final",
            _class = " class " ,
            _extend = " extends ",
            _implements = "implements",
            _brace_l = "{",
            _brace_r = "}";

    T newInstance(T targetObj) throws IllegalAccessException, InstantiationException;

    List<T> newInstance(List<T> targetObjs);

    Object invoke(Object sign,T t, Object... parameters) throws InvocationTargetException, IllegalAccessException;


}
