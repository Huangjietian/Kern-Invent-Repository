package cn.kerninventor.tools.poibox.opensource.data.templated.validation;

import cn.kerninventor.tools.poibox.opensource.data.templated.validation.date.DateDataValid;
import cn.kerninventor.tools.poibox.opensource.data.templated.validation.date.DateDataValidationBuilder;
import cn.kerninventor.tools.poibox.opensource.data.templated.validation.decimal.DecimalDataValid;
import cn.kerninventor.tools.poibox.opensource.data.templated.validation.decimal.DecimalDataValidationBuilder;
import cn.kerninventor.tools.poibox.opensource.data.templated.validation.integer.IntDataValid;
import cn.kerninventor.tools.poibox.opensource.data.templated.validation.integer.IntDataValidationBuilder;
import cn.kerninventor.tools.poibox.opensource.data.templated.validation.costom.FormulaCustomDataValid;
import cn.kerninventor.tools.poibox.opensource.data.templated.validation.costom.FormulaCustomDataValidationBuilder;
import cn.kerninventor.tools.poibox.opensource.data.templated.validation.textLength.TextLengthDataValid;
import cn.kerninventor.tools.poibox.opensource.data.templated.validation.textLength.TextLengthDataValidationBuilder;
import cn.kerninventor.tools.poibox.opensource.data.templated.validation.array.*;

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
        else if (annotation instanceof FormulaListDataValid) {
            return new FormulaListDataValidationBuilder((FormulaListDataValid) annotation);
        }
        else if (annotation instanceof ExplicitListDataValid) {
            return new ExplicitListDataValidationBuilder((ExplicitListDataValid) annotation);
        }
        else if (annotation instanceof FormulaCustomDataValid) {
            return new FormulaCustomDataValidationBuilder((FormulaCustomDataValid) annotation);
        }
        else {
            throw new AnnotationFormatError("not found ExcelValidHandler by this annotation: " + annotation.getClass());
        }
    }

}
