package cn.kerninventor.tools.poibox.opensource.data.tabulation.reader;

import cn.kerninventor.tools.poibox.opensource.data.tabulation.translator.ColumnDataTranslator;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;
import java.util.Map;

/**
 * @author Kern
 * @date 2020/3/16 19:27
 */
public interface TabulationReader<T> {

    List<T> readFrom(String sheetName);

    List<T> readFrom(int sheetAt);

    List<T> readFrom(Sheet sheet);

    TabulationReader withBeanValidator(BeanValidator<T, ?>... beanValidators);

    TabulationReader<T> withColumnDataTranslator(String translatorName, ColumnDataTranslator columnDataTranslator);

    TabulationReader<T> withAllColumnDataTranslator(Map<String, ColumnDataTranslator> columnDataTranslatorMap);

}
