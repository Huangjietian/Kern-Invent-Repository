package cn.kerninventor.tools.poibox.opensource.data.templated.validation;

import cn.kerninventor.tools.poibox.opensource.data.templated.initializer.EColumnInitiator;
import cn.kerninventor.tools.poibox.opensource.data.templated.initializer.ETabulationInitiator;
import org.apache.poi.ss.usermodel.Sheet;

import java.lang.annotation.Annotation;

/**
 * @author Kern
 * @date 2019/12/13 11:31
 */
@FunctionalInterface
public interface DataValidationBuilder<T extends Annotation> {

    /**
     * handler main method.
     */
    void addValidation(ETabulationInitiator tabulationInit, EColumnInitiator columnInit, Sheet sheet);

}
