package cn.kerninventor.tools.data.structure.proxy.util;


/**
 * @author Kern
 * @date 2020/3/31 13:34
 * @description TODO
 */
@FunctionalInterface
public interface ProxyStatementTyper {

    String specifiedContent(Object... args);

    String lineSeparator = System.lineSeparator();
    String pathSeparator = ";";
    String classNameAdditional = "$Proxy";
    String
            _package = "package ",
            _import = "import ",
            _public = "public ",
            _final = " final ",
            _class = " class " ,
            _proxyClassNamePrefix = "public final class ",
            _extend = " extends ",
            _implements = " implements ",
            _brace_l = " { ",
            _brace_r = " } ";

    default String type(Class targetClass, Object... args){
        StringBuilder builder = new StringBuilder();

        builder
                .append(_package).append(targetClass.getPackage().getName()).append(pathSeparator)

                .append(lineSeparator)

                .append(_import).append(targetClass.getName()).append(pathSeparator)

                .append(lineSeparator)

                .append(_proxyClassNamePrefix).append(targetClass.getSimpleName()).append(classNameAdditional)
                    .append(_extend).append(targetClass.getSimpleName()).append(_brace_l)

                .append(lineSeparator)

                .append(specifiedContent(args))

                .append(lineSeparator)
                .append(_brace_r);

        return builder.toString();
    }
}
