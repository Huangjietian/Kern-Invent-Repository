package cn.kerninventor.tools.poibox.data.datatable.datavalidation;

import cn.kerninventor.tools.poibox.data.datatable.ExcelcolumnDataAccepter;
import cn.kerninventor.tools.poibox.data.datatable.ExcelTabulationDataProcessor;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.array.ExcelArrayValidHandler;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.array.ExcelValid_ARRAY;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.date.ExcelDateValidHandler;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.date.ExcelValid_DATE;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.decimal.ExcelDecimalValidHandler;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.decimal.ExcelValid_DECIMAL;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.integer.ExcelIntValidHandler;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.integer.ExcelValid_INT;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.textLength.ExcelTextLengthValidHandler;
import cn.kerninventor.tools.poibox.data.datatable.datavalidation.textLength.ExcelValid_TEXTLENGTH;
import cn.kerninventor.tools.poibox.utils.DataTypeGroupUtil;
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

public interface DataValidHandler <T extends Annotation> {

    /**
     * handler main method.
     * @param excelTabulationDataProcessor
     * @param excelcolumnDataAccepter
     * @param sheet
     * @param excelValid
     */
    void addValidation(ExcelTabulationDataProcessor excelTabulationDataProcessor, ExcelcolumnDataAccepter excelcolumnDataAccepter, Sheet sheet, T excelValid);

    /**
     * handler factory.
     * @param annotation
     * @return
     */
    static DataValidHandler getInstance(Annotation annotation){
        if (!annotation.annotationType().isAnnotationPresent(ExcelValid.class)){
            throw new AnnotationFormatError("error format annotation!");
        }
        //date validation
        if (annotation instanceof ExcelValid_DATE){
            return new ExcelDateValidHandler();
        }
        else if (annotation instanceof ExcelValid_DECIMAL){
            return new ExcelDecimalValidHandler();
        }
        else if (annotation instanceof ExcelValid_INT){
            return new ExcelIntValidHandler();
        }
        else if (annotation instanceof ExcelValid_TEXTLENGTH){
            return new ExcelTextLengthValidHandler();
        }
        else if (annotation instanceof ExcelValid_ARRAY){
            return new ExcelArrayValidHandler();
        }
        else {
            throw new AnnotationFormatError("not found ExcelValidHandler by this annotation: " + annotation.getClass());
        }
    }

    static void qualifiedTypeValidHandler(ExcelTabulationDataProcessor processor, ExcelcolumnDataAccepter columnDataAccepter, Sheet sheet) {
        Class fieldType = columnDataAccepter.getFieldType();
        if (DataTypeGroupUtil.isMemberOfIntType(fieldType)) {
            DataValidHandler.getInstance(ExcelQualifiedTypeValidType.getDefInt()).addValidation(processor,columnDataAccepter,sheet,ExcelQualifiedTypeValidType.getDefInt());
        } else if (DataTypeGroupUtil.isMemberOfNumberType(fieldType)){
            DataValidHandler.getInstance(ExcelQualifiedTypeValidType.getDefDecimal()).addValidation(processor, columnDataAccepter, sheet, ExcelQualifiedTypeValidType.getDefDecimal());
        } else if (DataTypeGroupUtil.isMemberOfDateType(fieldType)) {
            DataValidHandler.getInstance(ExcelQualifiedTypeValidType.getDefDate()).addValidation(processor, columnDataAccepter, sheet, ExcelQualifiedTypeValidType.getDefDate());
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
