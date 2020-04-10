package cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.array.scan;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

/**
 * @author Kern
 * @date 2020/3/16 14:16
 */
@Deprecated
public class DictionaryInterfacesScanConfig {

//    public abstract String scanPackageName();

    public DictionaryInterfacesScanConfig() {
        String basePackages = "cn";
        ConfigurationBuilder configuration = new ConfigurationBuilder();
        configuration.forPackages(basePackages)
                .addScanners(new SubTypesScanner())
//                .addScanners(new Fie·ldAnnotationsScanner())
        ;
        Reflections reflections = new Reflections(configuration);

        Set<Class<? extends Dictionary>> classes = reflections.getSubTypesOf(Dictionary.class);

        classes.forEach(e -> {
            System.out.println(e);
            try {
                e.getDeclaredConstructor().newInstance().obtainDatas();
            } catch (InstantiationException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            } catch (InvocationTargetException ex) {
                ex.printStackTrace();
            } catch (NoSuchMethodException ex) {
                ex.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        DictionaryInterfacesScanConfig config = new DictionaryInterfacesScanConfig();
    }
}
