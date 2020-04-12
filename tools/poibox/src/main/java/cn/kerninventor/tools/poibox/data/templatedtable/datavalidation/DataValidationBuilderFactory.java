package cn.kerninventor.tools.poibox.data.templatedtable.datavalidation;

import cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.array.ArrayDataValid;
import cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.array.ArraysDataValidationBuilder;
import cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.date.DateDataValid;
import cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.date.DateDataValidationBuilder;
import cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.decimal.DecimalDataValid;
import cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.decimal.DecimalDataValidationBuilder;
import cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.integer.IntDataValid;
import cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.integer.IntDataValidationBuilder;
import cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.textLength.TextLengthDataValid;
import cn.kerninventor.tools.poibox.data.templatedtable.datavalidation.textLength.TextLengthDataValidationBuilder;

import java.lang.annotation.Annotation;
import java.lang.annotation.AnnotationFormatError;

/**
 * @author Kern
 * @date 2020/4/9 15:44
 * @description
 */
public class DataValidationBuilderFactory {

    /**
     * handler factory.
     * @param annotation
     * @return
     */
    public static DataValidationBuilder getInstance(Annotation annotation){
        if (!annotation.annotationType().isAnnotationPresent(DataValid.class)){
            throw new AnnotationFormatError("error format annotation!");
        }
        //date validation
        if (annotation instanceof DateDataValid){
            return new DateDataValidationBuilder((DateDataValid) annotation);
        }
        else if (annotation instanceof DecimalDataValid){
            return new DecimalDataValidationBuilder((DecimalDataValid) annotation);
        }
        else if (annotation instanceof IntDataValid){
            return new IntDataValidationBuilder((IntDataValid) annotation);
        }
        else if (annotation instanceof TextLengthDataValid){
            return new TextLengthDataValidationBuilder((TextLengthDataValid) annotation);
        }
        else if (annotation instanceof ArrayDataValid){
            return new ArraysDataValidationBuilder((ArrayDataValid) annotation);
        }
        else {
            throw new AnnotationFormatError("not found ExcelValidHandler by this annotation: " + annotation.getClass());
        }
    }

}