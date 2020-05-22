package cn.kerninventor.tools.poibox.opensource.data.tabulation.reader;

import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;

/**
 * @author Kern
 * @date 2020/3/16 19:27
 */
public interface TabulationReader<T> {

    List<T> readFrom(String sheetName);

    List<T> readFrom(int sheetAt);

    List<T> readFrom(Sheet sheet);

    TabulationReader addBeanValidator(BeanValidator<T, ?>... beanValidators);

}
