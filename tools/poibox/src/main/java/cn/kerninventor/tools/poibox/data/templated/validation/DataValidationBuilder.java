package cn.kerninventor.tools.poibox.data.templated.validation;

import cn.kerninventor.tools.poibox.data.templated.initializer.EColumnInitiator;
import cn.kerninventor.tools.poibox.data.templated.initializer.ETabulationInitiator;
import org.apache.poi.ss.usermodel.Sheet;

import java.lang.annotation.Annotation;

/**
 * @Title: DataValidHandler
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.data.datatable.validation
 * @Author Kern
 * @Date 2019/12/13 11:31
 * @Description: TODO
 */
@FunctionalInterface
public interface DataValidationBuilder<T extends Annotation> {

    /**
     * handler main method.
     */
    void addValidation(ETabulationInitiator tabulationInit, EColumnInitiator columnInit, Sheet sheet);

}
