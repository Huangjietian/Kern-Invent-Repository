package cn.kerninventor.tools.poibox.data.templatedtable.datavalidation;

import cn.kerninventor.tools.poibox.data.templatedtable.initializer.ExcelColumnInitializer;
import cn.kerninventor.tools.poibox.data.templatedtable.initializer.ExcelTabulationInitializer;
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
    void addValidation(ExcelTabulationInitializer tabulationInit, ExcelColumnInitializer columnInit, Sheet sheet);

}
