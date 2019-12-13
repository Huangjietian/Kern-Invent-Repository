package cn.kerninventor.tools.poibox.data.datatable.validation;

import cn.kerninventor.tools.poibox.data.datatable.DataColumn;
import cn.kerninventor.tools.poibox.data.datatable.DataTabulation;
import cn.kerninventor.tools.poibox.data.datatable.validation.array.ExcelArrayValidHandler;
import cn.kerninventor.tools.poibox.data.datatable.validation.array.ExcelValid_ARRAY;
import cn.kerninventor.tools.poibox.data.datatable.validation.date.ExcelDateValidHandler;
import cn.kerninventor.tools.poibox.data.datatable.validation.date.ExcelValid_DATE;
import cn.kerninventor.tools.poibox.data.datatable.validation.decimal.ExcelDecimalValidHandler;
import cn.kerninventor.tools.poibox.data.datatable.validation.decimal.ExcelValid_DECIMAL;
import cn.kerninventor.tools.poibox.data.datatable.validation.integer.ExcelIntValidHandler;
import cn.kerninventor.tools.poibox.data.datatable.validation.integer.ExcelValid_INT;
import cn.kerninventor.tools.poibox.data.datatable.validation.textLength.ExcelTextLengthValidHandler;
import cn.kerninventor.tools.poibox.data.datatable.validation.textLength.ExcelValid_TEXTLENGTH;
import org.apache.poi.ss.usermodel.Sheet;

import java.lang.annotation.Annotation;
import java.lang.annotation.AnnotationFormatError;

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
     * @param dataTabulation
     * @param dataColumn
     * @param sheet
     * @param excelValid
     */
    void addValidation(DataTabulation dataTabulation, DataColumn dataColumn, Sheet sheet, T excelValid);

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


}
