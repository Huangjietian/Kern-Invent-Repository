package cn.kerninventor.tools.poibox.opensource.data.tabulation.validation;

import cn.kerninventor.tools.poibox.opensource.data.tabulation.validation.date.DateDataValid;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.validation.date.DateDataValidationBuilder;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.validation.decimal.DecimalDataValid;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.validation.decimal.DecimalDataValidationBuilder;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.validation.integer.IntDataValid;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.validation.integer.IntDataValidationBuilder;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.validation.formula.FormulaCustomDataValid;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.validation.formula.FormulaCustomDataValidationBuilder;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.validation.textlength.TextLengthDataValid;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.validation.textlength.TextLengthDataValidationBuilder;
import cn.kerninventor.tools.poibox.opensource.data.tabulation.validation.array.*;

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
        else if (annotation instanceof EnumExplicitListDataValid) {
            return new EnumExplicitListDataValidationBuilder((EnumExplicitListDataValid) annotation);
        }
        else if (annotation instanceof FormulaCustomDataValid) {
            return new FormulaCustomDataValidationBuilder((FormulaCustomDataValid) annotation);
        }
        else {
            throw new AnnotationFormatError("not found ExcelValidHandler by this annotation: " + annotation.getClass());
        }
    }

}
