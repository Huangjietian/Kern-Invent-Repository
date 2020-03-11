package cn.kerninventor.tools.poibox.data.datatable.datavalidation;

import cn.kerninventor.tools.poibox.data.datatable.ExcelcolumnDataAccepter;
import cn.kerninventor.tools.poibox.data.datatable.ExcelTabulationDataProcessor;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.array.ExcelValidArrayBuilder;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.array.ExcelValidArray;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.date.ExcelValidDateBuilder;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.date.ExcelValidDate;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.decimal.ExcelValidDecimalBuilder;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.decimal.ExcelValidDecimal;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.integer.ExcelValidIntBuilder;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.integer.ExcelValidInt;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.textLength.ExcelValidTextLengthBuilder;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.textLength.ExcelValidTextlength;
import org.apache.poi.ss.usermodel.Sheet;

import java.lang.annotation.Annotation;
import java.lang.annotation.AnnotationFormatError;
import java.lang.reflect.Field;

/**
 * @Title: DataValidHandler
 * @ProjectName tools
 * @PackageName cn.kerninventor.tools.poibox.data.datatable.validation
 * @Author Kern
 * @Date 2019/12/13 11:31
 * @Description: TODO
 */
@FunctionalInterface
public interface DataValidBuilder<T extends Annotation> {

    /**
     * handler main method.
     * @param processor
     * @param accepter
     * @param sheet
     */
    void addValidation(ExcelTabulationDataProcessor processor, ExcelcolumnDataAccepter accepter, Sheet sheet);

    /**
     * handler factory.
     * @param annotation
     * @return
     */
    static DataValidBuilder getInstance(Annotation annotation){
        if (!annotation.annotationType().isAnnotationPresent(ExcelValid.class)){
            throw new AnnotationFormatError("error format annotation!");
        }
        //date validation
        if (annotation instanceof ExcelValidDate){
            return new ExcelValidDateBuilder((ExcelValidDate) annotation);
        }
        else if (annotation instanceof ExcelValidDecimal){
            return new ExcelValidDecimalBuilder((ExcelValidDecimal) annotation);
        }
        else if (annotation instanceof ExcelValidInt){
            return new ExcelValidIntBuilder((ExcelValidInt) annotation);
        }
        else if (annotation instanceof ExcelValidTextlength){
            return new ExcelValidTextLengthBuilder((ExcelValidTextlength) annotation);
        }
        else if (annotation instanceof ExcelValidArray){
            return new ExcelValidArrayBuilder((ExcelValidArray) annotation);
        }
        else {
            throw new AnnotationFormatError("not found ExcelValidHandler by this annotation: " + annotation.getClass());
        }
    }

    static Annotation findAnnotationForm(Field f) {
        Annotation[] annotations = f.getDeclaredAnnotations();
        for (Annotation annotation : annotations){
            if (annotation.annotationType().isAnnotationPresent(ExcelValid.class)){
                return annotation;
            }
        }
        return null;
    }


}
