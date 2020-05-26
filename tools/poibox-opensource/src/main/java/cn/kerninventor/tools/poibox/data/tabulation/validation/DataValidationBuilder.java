package cn.kerninventor.tools.poibox.data.tabulation.validation;

import cn.kerninventor.tools.poibox.data.tabulation.context.ColumnDefinition;
import cn.kerninventor.tools.poibox.data.tabulation.context.TableContext;
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
    void addValidation(TableContext tabulationInit, ColumnDefinition columnInit, Sheet sheet);

}
